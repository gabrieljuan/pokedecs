package com.azure.core.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.azure.core.theme.PokeDecsTheme

@Composable
fun PokeTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    label: String? = null,
    placeholder: String? = null,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    isError: Boolean = false,
    isPassword: Boolean = false,
    onTrailingIconClick: (() -> Unit)? = null,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        singleLine = singleLine,
        isError = isError,
        label = label?.let {
            {
                Text(text = it)
            }
        },
        placeholder = placeholder?.let {
            {
                Text(text = it)
            }
        },
        leadingIcon = leadingIcon?.let {
            {
                Icon(
                    leadingIcon,
                    contentDescription = null,
                )
            }
        },
        trailingIcon = trailingIcon?.let {
            {
                IconButton(
                    onClick = { onTrailingIconClick?.invoke() }
                ) {
                    Icon(
                        trailingIcon,
                        contentDescription = null,
                    )
                }
            }
        },
        shape = MaterialTheme.shapes.medium,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.primary,

            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,

            cursorColor = MaterialTheme.colorScheme.primary,

            errorBorderColor = MaterialTheme.colorScheme.error,
            errorLabelColor = MaterialTheme.colorScheme.error
        ),
        visualTransformation = if (isPassword) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }
    )
}

@Composable
fun PokePasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    isError: Boolean = false,
) {
    var isPasswordVisible by rememberSaveable {
        mutableStateOf(false)
    }

    PokeTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = label,
        placeholder = placeholder,
        enabled = enabled,
        singleLine = singleLine,
        isError = isError,
        leadingIcon = Icons.Outlined.Lock,
        trailingIcon = if (isPasswordVisible)
            Icons.Default.Visibility
        else
            Icons.Default.VisibilityOff,
        isPassword = !isPasswordVisible,
        onTrailingIconClick = { isPasswordVisible = !isPasswordVisible }
    )
}

@Preview(showBackground = true)
@Composable
fun PokeTextFieldPreview() {
    PokeDecsTheme {
        PokeTextField(
            modifier = Modifier.padding(8.dp),
            value = "azureus",
            onValueChange = {},
            label = "Username",
            leadingIcon = Icons.Outlined.PersonOutline
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PokeTextFieldPasswordPreview() {
    PokeDecsTheme {
        PokePasswordTextField(
            modifier = Modifier.padding(8.dp),
            value = "azureus",
            onValueChange = {},
            label = "Password",
        )
    }
}