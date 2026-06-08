package com.azure.feature.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.azure.core.designsystem.theme.BottomTabBar
import com.azure.core.designsystem.ui.AppBar

@Composable
fun HomeRoute(
    onPokeItemClick: (String) -> Unit,
) {
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }
    val pokeListViewModel = hiltViewModel<PokeListViewModel>()
    val pokeLazyListState = rememberLazyListState()
    Scaffold(
        topBar = {
            AppBar(title = "PokeDecs")
        },
        bottomBar = {
            BottomTabBar(
                selectedTab = selectedTab,
                onFirstTabClick = { selectedTab = 0 },
                onSecondTabClick = { selectedTab = 1},
            )
        }
    ) { paddingValues ->
        if (selectedTab == 0) {
            PokeListScreen(
                modifier = Modifier.padding(paddingValues),
                viewModel = pokeListViewModel,
                listState = pokeLazyListState,
                onPokeListItemClick = onPokeItemClick
            )
        }
    }
}