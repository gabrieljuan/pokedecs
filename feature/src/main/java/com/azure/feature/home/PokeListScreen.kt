@file:OptIn(ExperimentalMaterial3Api::class)

package com.azure.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.azure.core.designsystem.ui.PokeSearchBar

@Composable
fun PokeListScreen(
    viewModel: PokeListViewModel,
    listState: LazyListState,
    modifier: Modifier = Modifier,
    onPokeListItemClick: (String) -> Unit,
) {
    var query by rememberSaveable { mutableStateOf("") }
    val viewState = viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getPokeList()
    }
    Column(
        modifier = modifier
    ) {
        PokeSearchBar(
            value = query,
            onValueChange = {

            },
            onSearchAction = {

            },
            onClearClick = { query = "" }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            state = listState,
        ) {
            items(viewState.value.pokeList) { poke ->
                if (viewState.value.pokeList.last() == poke) {
                    viewModel.getPokeList()
                }
                PokeListItem(
                    modifier = Modifier.fillMaxWidth(),
                    name = poke.name,
                    onCardClick = { onPokeListItemClick(poke.name) },
                )
            }
        }
    }
}