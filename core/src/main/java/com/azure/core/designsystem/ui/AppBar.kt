package com.azure.core.designsystem.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.azure.core.designsystem.theme.PokeDecsTheme
import com.azure.core.designsystem.theme.TextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    navigationAction: ImageVector? = null,
    onBackClick: (() -> Unit)? = null,
    onActionClick: (() -> Unit)? = null,
    backGroundColor: Color = MaterialTheme.colorScheme.primary
) {
    val radiusShadow = 8.dp
    val spreadShadow = 0.dp
    val offsetShadow = DpOffset(x = 0.dp, y = 2.dp)
    val alphaShadow = 0.15f

    TopAppBar(
        modifier = modifier.dropShadow(
            shape = RectangleShape,
            shadow = Shadow(
                radius = radiusShadow,
                spread = spreadShadow,
                offset = offsetShadow,
                color = TextSecondary,
                alpha = alphaShadow,
                blendMode = BlendMode.SrcOver
            )
        ),
        title = {
            title?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
        },
        navigationIcon = {
            onBackClick?.let {
                IconButton(onClick = it) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
        },
        actions = {
            navigationAction?.let {
                IconButton(onClick = { onActionClick?.invoke() }) {
                    Icon(
                        imageVector = it,
                        contentDescription = "Action",
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = backGroundColor
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun AppBarPreview() {
    PokeDecsTheme {
        AppBar(
            title = "PokeDecs",
            navigationAction = Icons.Default.Edit,
            onActionClick = {},
            onBackClick = {},
        )
    }
}