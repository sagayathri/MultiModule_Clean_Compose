package com.sagayathri.presentation.components

import android.R.attr.text
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sagayathri.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolbar(
    modifier: Modifier = Modifier,
    title: String,
    showBackButton: Boolean = false,
    onBack: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .absoluteOffset(x = if (showBackButton) (10).dp else 0.dp),
                text = title,
                style = MaterialTheme.typography.headlineSmall
            )
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        contentDescription = stringResource(id = R.string.cont_desc_back_arrow)
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun CustomToolbarWithoutBackPreview() {
    CustomToolbar(
        modifier = Modifier,
        title = "Jokes",
    )
}

@Preview
@Composable
fun CustomToolbarWithBackPreview() {
    CustomToolbar(
        modifier = Modifier,
        title = "Jokes",
        showBackButton = true
    )
}