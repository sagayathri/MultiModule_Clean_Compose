package com.sagayathri.presentation.jokeList

import android.R.attr.onClick
import android.R.attr.text
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.sagayathri.data.model.Joke

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
                vertical = 8.dp,
            )
            .fillMaxWidth()
            .height(75.dp)
            .border(width = 4.dp, color = Color.Gray, shape = RoundedCornerShape(16.dp)),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ){
            Text(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .align(Alignment.Center),
                text = joke.setup,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
            )
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
