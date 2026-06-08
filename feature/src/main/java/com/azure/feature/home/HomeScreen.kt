package com.azure.feature.home

import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.azure.core.designsystem.theme.BottomTabBar
import com.azure.core.designsystem.ui.AppBar
import com.azure.feature.home.list.PokeListScreen
import com.azure.feature.home.profile.ProfileScreen
import kotlinx.coroutines.launch

@Composable
fun HomeRoute(
    username: String,
    onPokeItemClick: (String) -> Unit,
) {
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }
    val pokeLazyListState = rememberLazyListState()
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            AppBar(title = "PokeDecs")
        },
        snackbarHost = {
            SnackbarHost(
                modifier = Modifier.imePadding(),
                hostState = snackBarHostState
            )
        },
        bottomBar = {
            BottomTabBar(
                selectedTab = selectedTab,
                onFirstTabClick = { selectedTab = 0 },
                onSecondTabClick = { selectedTab = 1 },
            )
        }
    ) { paddingValues ->
        if (selectedTab == 0) {
            PokeListScreen(
                modifier = Modifier.padding(paddingValues),
                listState = pokeLazyListState,
                onPokeListItemClick = onPokeItemClick,
                onErrorMessage = {
                    coroutineScope.launch { snackBarHostState.showSnackbar(it) }
                },
            )
        } else {
            ProfileScreen(
                modifier = Modifier.padding(paddingValues),
                username = username,
                onErrorMessage = {
                    coroutineScope.launch { snackBarHostState.showSnackbar(it) }
                }
            )
        }
    }
}