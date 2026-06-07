package com.azure.feature.signup

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.azure.core.designsystem.theme.PokeDecsTheme
import com.azure.core.designsystem.ui.PokeButton
import com.azure.core.designsystem.ui.PokePasswordTextField
import com.azure.core.designsystem.ui.PokeTextField

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding() + 8.dp,
                    bottom = paddingValues.calculateBottomPadding() + 8.dp,
                    start = paddingValues.calculateStartPadding(LayoutDirection.Ltr) + 16.dp,
                    end = paddingValues.calculateEndPadding(LayoutDirection.Ltr) + 16.dp,
                ),
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
            Spacer(modifier = Modifier.height(24.dp))
            PokeButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Sign Up"
            ) { }
        }
    }
    BackHandler(onBack = onBackClick)
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    PokeDecsTheme {
        SignupScreen() { }
    }
}