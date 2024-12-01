package com.sagayathri.presentation.jokeList

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.sagayathri.data.model.Joke
import com.sagayathri.presentation.R

@Composable
fun JokeItem(
    joke: Joke,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(
                horizontal = 8.dp,
                vertical = 4.dp,
            )
            .border(
                width = 1.dp,
                color = Gray,
                shape = RoundedCornerShape(8.dp)
            )
            .fillMaxSize(),
    ) {
        Row(
            modifier = Modifier
                .clickable(onClick = onItemClick)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Column(
                Modifier.weight(1f),
            ) {
                Text(
                    modifier = Modifier.padding(all = 8.dp),
                    text = joke.setup,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Start,
                )
            }
            Column(
                Modifier.weight(0.1f)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowForward,
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    contentDescription = stringResource(id = R.string.cont_desc_show_answer)
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun JokeItemPreview() {
    val joke = Joke(
        id = 10,
        type = "general",
        setup = "What cheese can never be yours?",
        punchline = "Nacho cheese.",
    )
    MaterialTheme {
        JokeItem(joke = joke, onItemClick = {})
    }
}
