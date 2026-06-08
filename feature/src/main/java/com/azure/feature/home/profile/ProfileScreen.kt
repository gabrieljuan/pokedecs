package com.azure.feature.home.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.azure.core.designsystem.theme.PokeDecsTheme
import com.azure.feature.R

@Composable
fun ProfileScreen(
    username: String,
    modifier: Modifier = Modifier,
) {
    val viewModel = hiltViewModel<ProfileViewModel>()
    val viewState = viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getProfile(username)
    }

    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.size(108.dp),
            imageVector = ImageVector.vectorResource(R.drawable.vector_avatar_placeholder),
            contentDescription = "avatar"
        )
        Text(
            text = viewState.value.user.username,
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = viewState.value.user.about,
            style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(modifier = Modifier.height(16.dp))
        ProfileInfo(
            modifier = Modifier.fillMaxWidth(),
            text = viewState.value.user.email,
            icon = Icons.Outlined.Mail,
        )
        HorizontalDivider(modifier = Modifier.fillMaxWidth())
        ProfileInfo(
            modifier = Modifier.fillMaxWidth(),
            text = viewState.value.user.phone,
            icon = Icons.Outlined.Phone,
        )
    }
}
@Composable
fun ProfileInfo(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = icon,
            contentDescription = "Icon"
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileInfoPreview(){
    PokeDecsTheme {
        ProfileInfo(
            modifier = Modifier.fillMaxWidth(),
            icon = Icons.Outlined.Mail,
            text = "john@doe.com"
        )
    }
}