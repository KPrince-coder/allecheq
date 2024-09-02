package dev.android.allecheq.presentation.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.android.allecheq.R
import dev.android.allecheq.data.EmergencyContact
import dev.android.allecheq.data.datastore.EmergencyContactDataStore
import dev.android.allecheq.data.datastore.dataStore
import dev.android.allecheq.data.repository.EmergencyContactRepositoryImpl
import dev.android.allecheq.presentation.utils.VALUE_12
import dev.android.allecheq.presentation.utils.VALUE_16
import dev.android.allecheq.presentation.utils.VALUE_20
import dev.android.allecheq.presentation.utils.VALUE_4
import dev.android.allecheq.presentation.utils.VALUE_40
import dev.android.allecheq.presentation.utils.VALUE_8
import dev.android.allecheq.presentation.viewmodel.EmergencyContactViewModel
import dev.android.allecheq.presentation.viewmodel.viewmodelfactory.EmergencyContactViewModelFactory
import dev.android.allecheq.ui.theme.FontFam
import java.util.Locale




@Composable
fun EmergencyScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    val emergencyContactViewModel: EmergencyContactViewModel = viewModel(
        factory = EmergencyContactViewModelFactory(
            EmergencyContactRepositoryImpl(EmergencyContactDataStore(LocalContext.current.dataStore))
        )
    )

    val contactList by emergencyContactViewModel.contactList.collectAsStateWithLifecycle()
    val l = listOf(
        EmergencyContact("prince", "father", "1234")
    )

    EmergencyScreenContent(
        modifier = modifier.padding(paddingValues),
        contactList = contactList,
        onDeleteClick = { contact ->
            emergencyContactViewModel.deleteContact(contact)
        },
        onAddContactClick = { contact ->
            emergencyContactViewModel.saveContact(
                contact.name,
                contact.relationship,
                contact.phoneNumber
            )
        }
    )
}


@Composable
private fun EmergencyScreenContent(
    contactList: List<EmergencyContact>,
    onDeleteClick: (contact: EmergencyContact) -> Unit,
    modifier: Modifier = Modifier,
    onAddContactClick: (EmergencyContact) -> Unit // Add this parameter
) {
    var showAddContactDialog by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = VALUE_16.dp, vertical = VALUE_12.dp)
        ) {
            Title()
            Contacts(contactList = contactList, onDeleteClick = onDeleteClick)
            AddContactButton(onClick = { showAddContactDialog = true }) // Update to handle click
        }
    }

    if (showAddContactDialog) {
        AddContactDialog(
            onAddContact = {
                onAddContactClick(it)
                showAddContactDialog = false
            },
            onDismiss = { showAddContactDialog = false }
        )
    }
}


@Composable
private fun Title(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(VALUE_8))
            .background(Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.emergency),
            style = TextStyle(
                fontFamily = FontFam.Inter.fontFamily,
                fontSize = VALUE_40.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.surface,
                shadow = Shadow(MaterialTheme.colorScheme.outline, blurRadius = 2.5F)

            )
        )
    }
}

@Composable
fun Contacts(
    contactList: List<EmergencyContact>,
    onDeleteClick: (contact: EmergencyContact) -> Unit,
    modifier: Modifier = Modifier
) {

    LazyColumn(modifier = modifier.padding(top = VALUE_40.dp)) {
        item {
            Text(
                text = stringResource(R.string.contacts),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = VALUE_20.sp,
                    color = MaterialTheme.colorScheme.outline
                )
            )
        }
        items(contactList) { contact ->
            Contact(
                contact = contact,
                modifier = Modifier.padding(top = VALUE_12.dp),
                onDeleteClick = onDeleteClick
            )
        }
    }
}


@Composable
private fun Contact(
    contact: EmergencyContact,
    modifier: Modifier = Modifier,
    onDeleteClick: (contact: EmergencyContact) -> Unit
) {
    var showDialog by remember {
        mutableStateOf(false)
    }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val initials = contact.name.take(1).uppercase()
        Box(
            modifier = Modifier
                .size(VALUE_40.dp)
                .clip(CircleShape)
                .background(color = MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = initials,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = TextStyle(
                    fontSize = VALUE_16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Spacer(modifier = Modifier.width(VALUE_16.dp))
        Column {
            Text(
                text = "${
                    contact.name.replaceFirstChar {
                        if (it.isLowerCase()) it
                            .titlecase(Locale.getDefault())
                        else it.toString()
                    }
                } (${contact.relationship.lowercase()})",
                style = TextStyle(
                    fontSize = VALUE_16.sp,
                    fontWeight = FontWeight.W600,
                    color = MaterialTheme.colorScheme.outline
                )
            )
            Spacer(modifier = Modifier.height(VALUE_4.dp))
            Text(
                text = contact.phoneNumber,
                style = TextStyle(
                    fontSize = VALUE_12.sp,
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colorScheme.outline
                )
            )
        }
        IconButton(
            onClick = {
                showDialog = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.CenterEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete contact",
                tint = MaterialTheme.colorScheme.error
            )
        }
    }
    if (showDialog) {
        ConfirmDeleteDialog(onConfirm = { onDeleteClick(contact) }) {
            showDialog = false

        }
    }
}


@Composable
private fun AddContactButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.BottomEnd)
            .padding(bottom = VALUE_12.dp)
            .clip(CircleShape),

        shadowElevation = VALUE_20.dp,
        tonalElevation = VALUE_4.dp,
        color = MaterialTheme.colorScheme.tertiaryContainer
    ) {

        IconButton(
            onClick = onClick,
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.add_contact_button),
                contentDescription = stringResource(R.string.add_contact_button_desc),
                tint = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(VALUE_4.dp)
            )

        }
    }

}

@Composable
fun ConfirmDeleteDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Delete")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        title = { Text("Delete Contact") },
        text = { Text("Are you sure you want to delete this contact?") }
    )
}


@Composable
fun AddContactDialog(
    onAddContact: (EmergencyContact) -> Unit,
    onDismiss: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var relationship by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = {
                    onAddContact(
                        EmergencyContact(
                            name = name,
                            relationship = relationship,
                            phoneNumber = phoneNumber
                        )
                    )
                }
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        title = { Text("Add Contact") },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") }
                )
                OutlinedTextField(
                    value = relationship,
                    onValueChange = { relationship = it },
                    label = { Text("Relationship") }
                )
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = { Text("Phone Number") }
                )
            }
        }
    )
}


