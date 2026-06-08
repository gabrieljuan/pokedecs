package com.azure.core.designsystem.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun PokeSearchBar(
    value: String,
    modifier: Modifier = Modifier,
    placeholderText: String = "",
    onClearClick: () -> Unit,
    onValueChange: (String) -> Unit,
    onSearchAction: ((String) -> Unit)? = null,
) {
    val textStyle = MaterialTheme.typography.bodyMedium
    val backgroundColor = Color.Transparent
    val searchBarShape = MaterialTheme.shapes.large
    val focusedBorderColor = MaterialTheme.colorScheme.primary
    val unfocusedBorderColor = MaterialTheme.colorScheme.outline

    val interactionSource = remember { MutableInteractionSource() }
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .defaultMinSize(
                minHeight = 44.dp
            ),
        singleLine = true,
        textStyle = textStyle,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchAction?.invoke(value)
            }
        ),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        interactionSource = interactionSource,
        decorationBox = { innerTextField ->
            val isFocused = interactionSource.collectIsFocusedAsState().value
            val borderColor = if (isFocused) {
                focusedBorderColor
            } else {
                unfocusedBorderColor
            }
            Row(
                modifier = Modifier
                    .background(backgroundColor, searchBarShape)
                    .border(1.dp, borderColor, searchBarShape)
                    .clip(searchBarShape),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(Modifier.width(12.dp))
                LeadingIcon()
                Spacer(Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                ) {
                    if (value.isEmpty()) {
                        PlaceHolder(placeholderText)
                    }
                    innerTextField()
                }
                Spacer(Modifier.width(12.dp))
                if (value.isNotEmpty()) {
                    Spacer(Modifier.width(8.dp))
                    TrailingIcon {
                        onClearClick()
                    }
                    Spacer(Modifier.width(12.dp))
                }
            }
        }
    )
}

@Composable
private fun PlaceHolder(placeholderText: String) {
    Text(
        text = placeholderText,
        style = MaterialTheme.typography.labelMedium
    )
}

@Composable
private fun LeadingIcon() {
    Icon(
        imageVector = Icons.Default.Search,
        contentDescription = null,
        tint = Color.Unspecified
    )
}

@Composable
private fun TrailingIcon(
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(
            16.dp
        )
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = null,
            tint = Color.Unspecified
        )
    }
}