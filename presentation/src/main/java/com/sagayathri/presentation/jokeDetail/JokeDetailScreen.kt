package com.sagayathri.presentation.jokeDetail

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.sagayathri.data.model.Joke
import com.sagayathri.presentation.components.CustomToolbar
import kotlinx.coroutines.launch
import java.util.Locale
import com.sagayathri.presentation.R

@Composable
fun JokeDetailScreen(
    jokeId: Int,
    viewModel: JokeDetailsViewModel = hiltViewModel(),
    navController: NavHostController,
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier,
        topBar = {
            CustomToolbar(
                title = if (state.item != null) state.item!!.type.uppercase(Locale.UK) else "JOKE",
                showBackButton = true,
                onBack = {
                    navController.popBackStack()
                }
            )
        },
        content = { paddingValues ->
            Box(modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .paint(
                    painterResource(R.drawable.smiley),
                    contentScale = ContentScale.FillBounds
                ),
                contentAlignment = Alignment.Center,
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(start = 30.dp, bottom = 50.dp, end = 30.dp)
                        .border(
                            width = 2.dp,
                            color = Gray,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .shadow(
                            elevation = 4.dp,
                            shape = RoundedCornerShape(16.dp),
                            ambientColor = Gray,
                        ),
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        if (state.item != null) {
                            val joke = state.item!!
                            DetailsContent(joke)
                        } else {
                            ErrorContent()
                        }
                    }
                }
            }
        }
    )

    LaunchedEffect(state) {
        if (!state.isLoaded) {
            coroutineScope.launch {
                viewModel.fetchJokeByID(jokeId = jokeId)
            }
        }
    }
}

@Composable
fun DetailsContent(
    joke: Joke,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .padding(horizontal = 16.dp),
    ) {
        Text(
            text = joke.setup,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 4.dp),
        )
        Spacer(
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = joke.punchline,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 4.dp),
        )
    }
}

@Composable
fun ErrorContent(
    modifier: Modifier = Modifier,
){
    Text(
        modifier = modifier.padding(horizontal = 16.dp),
        text = stringResource(R.string.error),
        style = MaterialTheme.typography.bodyLarge,
    )
}

@PreviewLightDark
@Composable
fun JokeDetailPreview() {
    val joke = Joke(
        id = 10,
        type = "general",
        setup = "What cheese can never be yours?",
        punchline = "Nacho cheese.",
    )
    MaterialTheme {
        Column {
            DetailsContent(joke = joke)
            Spacer(modifier = Modifier.padding())
            ErrorContent()
        }
    }
}
