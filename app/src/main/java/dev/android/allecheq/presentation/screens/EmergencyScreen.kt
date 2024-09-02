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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

            if (contactList.isEmpty()) {
                NoContact(showAddContactDialog = { showAddContactDialog = true })

            }
            Contacts(contactList = contactList, onDeleteClick = onDeleteClick, onClick = {
                showAddContactDialog = true
            })
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
    onClick: () -> Unit,
    onDeleteClick: (contact: EmergencyContact) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = VALUE_40.dp)
        ) {
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

        AddContactButton(onClick = onClick)

    }
}

@Composable
private fun NoContact(showAddContactDialog: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = stringResource(R.string.no_contacts_available),
            style = TextStyle(
                fontSize = VALUE_20.sp,
                fontWeight = FontWeight.W500,
                fontFamily = FontFam.Inter.fontFamily,
                color = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.wrapContentSize(Alignment.Center)
        )

        AddContactButton(
            onClick = showAddContactDialog,
        )
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
                    fontSize = VALUE_20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFam.Inter.fontFamily
                )
            )
        }
        Spacer(modifier = Modifier.width(VALUE_16.dp))
        Column {
            Text(
                text = "${
                    contact.name.replaceFirstChar {
                        if (it.isLowerCase())
                            it.titlecase(Locale.getDefault())
                        else it.toString()
                    }
                } (${contact.relationship.lowercase()})",
                style = TextStyle(
                    fontSize = VALUE_16.sp,
                    fontWeight = FontWeight.W600,
                    color = MaterialTheme.colorScheme.outline,
                    fontFamily = FontFam.Inter.fontFamily
                )
            )
            Spacer(modifier = Modifier.height(VALUE_4.dp))
            Text(
                text = contact.phoneNumber,
                style = TextStyle(
                    fontSize = VALUE_12.sp,
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colorScheme.outline,
                    fontFamily = FontFam.Inter.fontFamily

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
                Text(
                    text = stringResource(R.string.delete),
                    style = TextStyle(
                        fontFamily = FontFam.Inter.fontFamily
                    )
                )
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(
                    text = stringResource(R.string.cancel),
                    style = TextStyle(
                        fontFamily = FontFam.Inter.fontFamily
                    )
                )
            }
        },
        title = {
            Text(
                text = stringResource(R.string.delete_contact),
                style = TextStyle(
                    fontFamily = FontFam.Inter.fontFamily
                )
            )
        },
        text = {
            Text(
                text = stringResource(R.string.delete_contact_text),
                style = TextStyle(
                    fontFamily = FontFam.Inter.fontFamily
                )
            )
        }
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

    val focusManager = LocalFocusManager.current
    val (
        focusRequester1,
        focusRequester2,
        focusRequester3
    ) = createRefs()

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
                Text(
                    text = stringResource(R.string.add),
                    style = TextStyle(
                        fontFamily = FontFam.Inter.fontFamily,
                        fontWeight = FontWeight.W600
                    )
                )
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(
                    text = stringResource(id = R.string.cancel),
                    style = TextStyle(
                        fontFamily = FontFam.Inter.fontFamily,
                        fontWeight = FontWeight.W600
                    )
                )
            }
        },
        title = {
            Text(
                text = stringResource(R.string.add_contact),
                style = TextStyle(
                    fontFamily = FontFam.Inter.fontFamily,
                    fontWeight = FontWeight.W600,
                    fontSize = VALUE_20.sp
                )
            )
        },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(stringResource(R.string.name)) },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    modifier = Modifier
                        .focusRequester(focusRequester1)
                )


                OutlinedTextField(
                    value = relationship,
                    onValueChange = { relationship = it },
                    label = {
                        Text(
                            stringResource(R.string.relationship),
                            fontFamily = FontFam.Inter.fontFamily
                        )
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    modifier = Modifier
                        .focusRequester(focusRequester2)
                )


                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = {
                        Text(
                            stringResource(R.string.phone_number),
                            fontFamily = FontFam.Inter.fontFamily
                        )
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            onAddContact(
                                EmergencyContact(
                                    name = name,
                                    relationship = relationship,
                                    phoneNumber = phoneNumber
                                )
                            )

                        }
                    ),
                    modifier = Modifier
                        .focusRequester(focusRequester3)
                )
            }
        }
    )
}

