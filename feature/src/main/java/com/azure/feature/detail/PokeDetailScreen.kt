package com.azure.feature.detail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.azure.core.designsystem.theme.PokeDecsTheme
import com.azure.core.designsystem.ui.AppBar
import com.azure.core.designsystem.ui.LoadingState
import com.azure.domain.model.Ability
import com.azure.domain.model.PokeDetail

@Composable
fun PokeDetailRoute(
    pokeName: String,
    onBackClick: () -> Unit,
) {
    val viewModel: PokeDetailViewModel = hiltViewModel()
    val viewState = viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getPokeDetail(pokeName)
    }
    PokeDetailScreen(
        pokeDetail = viewState.value.pokeDetail,
        isLoading = viewState.value.isLoading,
        onBackClick = onBackClick,
        onErrorShown = viewModel::onErrorShown
    )
    BackHandler(onBack = onBackClick)
}

@Composable
fun PokeDetailScreen(
    pokeDetail: PokeDetail,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    onBackClick: () -> Unit,
    onErrorShown: () -> Unit,
) {
    val snackBarHostState = remember { SnackbarHostState() }
    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            snackBarHostState.showSnackbar(it)
            onErrorShown()
        }
    }
    Scaffold(
        topBar = {
            AppBar(onBackClick = onBackClick)
        },
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { paddingValues ->
        if (isLoading) {
            LoadingState(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            )
        } else {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(64.dp))
                PokeDetailHeader(
                    imageUrl = pokeDetail.spriteUrl,
                    name = pokeDetail.name,
                )
                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Spacer(modifier = Modifier.height(8.dp))
                PokeDetailBasicInfo(
                    type = pokeDetail.element,
                    weight = "${pokeDetail.weight}lbs",
                    height = "${pokeDetail.height}ft",
                )
                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                PokeDetailAbilityList(
                    modifier = Modifier.fillMaxWidth(),
                    abilityList = pokeDetail.abilities,
                )
            }
        }
    }
}

@Composable
private fun PokeDetailHeader(
    imageUrl: String,
    name: String,
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = name,
        modifier = Modifier
            .size(160.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Fit
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = name,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
    )
}

@Composable
private fun PokeDetailBasicInfo(
    type: String,
    weight: String,
    height: String,
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        text = "Basic Info",
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.primary,
    )
    Spacer(modifier = Modifier.height(12.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = "Type",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            modifier = Modifier.weight(1f),
            text = type,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
    Spacer(modifier = Modifier.height(12.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = "Height",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            modifier = Modifier.weight(1f),
            text = height,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
    Spacer(modifier = Modifier.height(12.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = "Weight",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            modifier = Modifier.weight(1f),
            text = weight,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun PokeDetailAbilityList(
    abilityList: List<Ability>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                text = "Abilities",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
            )
        }
        items(abilityList) { ability ->
            PokeAbilityListItem(
                modifier = Modifier.fillMaxWidth(),
                ability = ability.name,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PokeDetailHeaderPreview() {
    PokeDecsTheme {
        Column {
            PokeDetailHeader(
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
                name = "Pikachu"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PokeDetailBasicInfoPreview() {
    PokeDecsTheme {
        Column {
            PokeDetailBasicInfo(
                type = "electric",
                weight = "60lbs",
                height = "4ft"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PokeDetailAbilityListPreview() {
    PokeDecsTheme {
        PokeDetailAbilityList(
            modifier = Modifier.fillMaxWidth(),
            abilityList = listOf(Ability("Static"), Ability("Lightning Rod")),
        )
    }
}