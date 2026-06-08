package com.azure.core.designsystem.theme

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun BottomTabBar(
    modifier: Modifier = Modifier,
    selectedTab: Int = 0,
    onFirstTabClick: () -> Unit,
    onSecondTabClick: () -> Unit,
) {
    val textStyle = MaterialTheme.typography.labelMedium
    val selectedColor = MaterialTheme.colorScheme.primary
    val unselectedColor = Color.Unspecified
    NavigationBar(
        modifier = modifier.fillMaxWidth()
    ) {
        Tab(
            modifier = Modifier.weight(1f),
            selected = selectedTab == 0,
            text = {
                Text(
                    text = "Poke List",
                    style = textStyle,
                )
            },
            selectedContentColor = selectedColor,
            unselectedContentColor = unselectedColor,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Home,
                    contentDescription = "Poke List",
                )
            },
            onClick = onFirstTabClick
        )
        Tab(
            modifier = Modifier.weight(1f),
            selected = selectedTab == 1,
            text = {
                Text(
                    text = "Profile",
                    style = textStyle,
                )
            },
            selectedContentColor = selectedColor,
            unselectedContentColor = unselectedColor,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "Profile",
                )
            },
            onClick = onSecondTabClick
        )
    }
}