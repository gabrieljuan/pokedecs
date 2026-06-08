package com.azure.feature.register

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Star
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
import com.azure.core.designsystem.ui.PokeButton
import com.azure.core.designsystem.ui.PokePasswordTextField
import com.azure.core.designsystem.ui.PokeTextField

@Composable
fun RegisterRoute(
    onBackClick: () -> Unit,
    onRegisterSuccess: (username: String) -> Unit,
) {
    val viewModel = hiltViewModel<RegisterViewModel>()
    val viewState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewState.value.isRegisterSuccess) {
        onRegisterSuccess(viewState.value.username)
    }
    RegisterScreen(
        isLoading = viewState.value.isLoading,
        errorMessage = viewState.value.errorMessage,
        onSignUpClick = { username, password, email, phone, about ->
            viewModel.register(
                username = username,
                password = password,
                email = email,
                phone = phone,
                about = about,
            )
        },
        onBackClick = onBackClick,
        onErrorShown = viewModel::onErrorShown
    )
}

@Composable
fun RegisterScreen(
    isLoading: Boolean,
    errorMessage: String?,
    modifier: Modifier = Modifier,
    onSignUpClick: (
        username: String,
        password: String,
        email: String,
        phone: String,
        about: String,
    ) -> Unit,
    onBackClick: () -> Unit,
    onErrorShown: () -> Unit,
) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var about by rememberSaveable { mutableStateOf("") }
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
                text = "Sign Up",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
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
            Spacer(modifier = Modifier.height(8.dp))
            PokeTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                label = "Email",
                placeholder = "your@email.com",
                onValueChange = { email = it },
                leadingIcon = Icons.Outlined.Mail
            )
            Spacer(modifier = Modifier.height(8.dp))
            PokeTextField(
                modifier = Modifier.fillMaxWidth(),
                value = phone,
                label = "Phone",
                placeholder = "08xxxxxxxxx",
                onValueChange = { phone = it },
                leadingIcon = Icons.Outlined.Phone,
                isNumber = true,
            )
            Spacer(modifier = Modifier.height(8.dp))
            PokeTextField(
                modifier = Modifier.fillMaxWidth(),
                value = about,
                label = "About Me",
                placeholder = "Your Catchphrase",
                onValueChange = { about = it },
                leadingIcon = Icons.Outlined.Star
            )
            Spacer(modifier = Modifier.height(24.dp))
            PokeButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Sign Up",
                isLoading = isLoading,
            ) {
                onSignUpClick(
                    username, password, email, phone, about
                )
            }
        }
    }
    BackHandler(onBack = onBackClick)
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    PokeDecsTheme {
        RegisterScreen(
            isLoading = false,
            errorMessage = null,
            onSignUpClick = { _, _, _, _, _ ->

            },
            onBackClick = {},
            onErrorShown = {},
        )
    }
}