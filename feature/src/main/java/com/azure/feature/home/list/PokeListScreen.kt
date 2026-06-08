@file:OptIn(ExperimentalMaterial3Api::class)

package com.azure.feature.home.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.azure.core.designsystem.ui.PokeSearchBar

@Composable
fun PokeListScreen(
    listState: LazyListState,
    modifier: Modifier = Modifier,
    onPokeListItemClick: (String) -> Unit,
) {
    val viewModel = hiltViewModel<PokeListViewModel>()
    val viewState = viewModel.uiState.collectAsStateWithLifecycle()
    Column(
        modifier = modifier
    ) {
        PokeSearchBar(
            value = viewState.value.query,
            onValueChange = viewModel::onQueryChanged,
            onClearClick = {
                viewModel.onQueryChanged("")
            }
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            state = listState,
        ) {
            items(viewState.value.pokeList) { poke ->
                if (viewState.value.pokeList.last() == poke && viewState.value.pokeList.size > 1) {
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