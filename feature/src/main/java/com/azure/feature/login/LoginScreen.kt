package com.azure.feature.login

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.azure.core.designsystem.theme.PokeDecsTheme
import com.azure.core.designsystem.theme.TextSecondary
import com.azure.core.designsystem.ui.PokeButton
import com.azure.core.designsystem.ui.PokePasswordTextField
import com.azure.core.designsystem.ui.PokeTextButton
import com.azure.core.designsystem.ui.PokeTextField

@Composable
fun LoginRoute(
    onBackClick: () -> Unit,
    onLoginSuccess: (String) -> Unit,
    onSignUpClick: () -> Unit,
) {
    val viewModel = hiltViewModel<LoginViewModel>()
    val viewState = viewModel.uiState.collectAsStateWithLifecycle()
    if (viewState.value.isLoginSuccess) {
        onLoginSuccess(viewState.value.username)
    }

    LoginScreen(
        isLoading = viewState.value.isLoading,
        errorMessage = viewState.value.errorMessage,
        onBackClick = onBackClick,
        onLoginClick = { username, password ->
            viewModel.login(username, password)
        },
        onSignUpClick = onSignUpClick,
        onErrorShown = viewModel::onErrorShown,
    )
}

@Composable
fun LoginScreen(
    isLoading: Boolean,
    errorMessage: String?,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onLoginClick: (username: String, password: String) -> Unit,
    onSignUpClick: () -> Unit,
    onErrorShown: () -> Unit,
) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val snackBarHostState = remember { SnackbarHostState() }
    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            snackBarHostState.showSnackbar(it)
            onErrorShown()
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(
                modifier = Modifier.imePadding(),
                hostState = snackBarHostState
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .imePadding()
                .verticalScroll(scrollState)
                .padding(vertical = 8.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "PokeDecs",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Explore, Learn, Be A Master",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(modifier = Modifier.height(24.dp))
            PokeTextField(
                modifier = Modifier.fillMaxWidth(),
                value = username,
                label = "Username",
                placeholder = "Enter Username",
                onValueChange = { username = it },
                leadingIcon = Icons.Outlined.PersonOutline
            )
            Spacer(modifier = Modifier.height(8.dp))
            PokePasswordTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = { password = it },
                label = "Password",
            )
            Spacer(modifier = Modifier.height(24.dp))
            PokeButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Log In",
                isLoading = isLoading,
                onClick = {
                    onLoginClick(username, password)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            PokeTextButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Forgot Password?"
            ) { }
            Spacer(modifier = Modifier.height(48.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don't have an account?",
                    style = MaterialTheme.typography.labelMedium,
                    color = TextSecondary,
                )
                Spacer(modifier = Modifier.width(4.dp))
                PokeTextButton(
                    text = "Sign Up",
                    onClick = onSignUpClick
                )
            }
        }
    }
    BackHandler(onBack = onBackClick)
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    PokeDecsTheme {
        LoginScreen(
            isLoading = false,
            errorMessage = null,
            onLoginClick = { _, _ ->

            },
            onSignUpClick = {},
            onBackClick = {},
            onErrorShown = {},
        )
    }
}