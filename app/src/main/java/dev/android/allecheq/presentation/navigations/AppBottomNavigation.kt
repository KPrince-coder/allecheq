package dev.android.allecheq.presentation.navigations

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.android.allecheq.presentation.utils.VALUE_16
import dev.android.allecheq.presentation.utils.VALUE_20
import dev.android.allecheq.presentation.utils.VALUE_24
import dev.android.allecheq.ui.theme.FontFam

@Composable
fun AppBottomNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val bottomScreens = remember {
        listOf(
            BottomNavigationRoutes.Scan,
            BottomNavigationRoutes.Emergency,
            BottomNavigationRoutes.Profile
        )
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    // val currentDestination = navBackStackEntry?.destination
    val currentDestination by remember(navBackStackEntry) {
        derivedStateOf {
            navBackStackEntry?.destination?.route
        }
    }


    NavigationBar {
        bottomScreens.forEach { screen ->
            val isSelected = currentDestination == screen.route.screen.route
            NavigationBarItem(
                screen = screen,
                isSelected = isSelected,
                navController = navController
            )
        }
    }
}

@Composable
private fun RowScope.NavigationBarItem(
    screen: BottomNavigationRoutes<out Destinations>,
    isSelected: Boolean,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBarItem(
        modifier = modifier,
        selected = isSelected,
        onClick = {
            navController.navigate(screen.route.screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = screen.name,
                modifier = Modifier
                    .size(if (isSelected) VALUE_24.dp else VALUE_20.dp),
            )
        },
        label = {
            Text(
                text = screen.name,
                style = TextStyle(
                    fontWeight = if (isSelected) FontWeight.W600 else FontWeight.Normal,
                    fontSize = VALUE_16.sp,
                    fontFamily = FontFam.Inter.fontFamily
                )
            )
        },
        colors = NavigationBarItemDefaults.colors().copy(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = Color.Gray,
            selectedIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            unselectedTextColor = Color.Gray
        )
    )
}

