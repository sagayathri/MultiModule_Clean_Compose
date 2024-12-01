package com.sagayathri.presentation.jokeList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.sagayathri.data.model.Joke
import com.sagayathri.presentation.components.CustomToolbar
import com.sagayathri.presentation.components.LoadingScreen
import com.sagayathri.presentation.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JokeListScreen(
    modifier: Modifier = Modifier,
    viewModel: JokesListViewModel = hiltViewModel(),
    navController: NavController,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier,
        topBar = {
            CustomToolbar(
                title = stringResource(id = R.string.title),
                onBack = {},
            )
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                when {
                    state.isLoading -> {
                        LoadingScreen()
                    }

                    state.items.isNotEmpty() -> {
                        JokeList(
                            list = state.items,
                            onItemClick = { jokeId ->
                                navController.navigate(route = "jokes/$jokeId")
                            },
                        )
                    }

                    else -> {
                        ErrorScreen()
                    }
                }
            }
        }
    )

    LaunchedEffect(state) {
        if (!state.isLoaded) {
            coroutineScope.launch {
                viewModel.fetchJokes(limit = 10)
            }
        }
    }
}

@Composable
fun JokeList(
    list: List<Joke>,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(horizontal = 8.dp),
    ) {
        items(list) { joke ->
            JokeItem(
                joke = joke,
                onItemClick = {
                    onItemClick(joke.id)
                }
            )
        }
    }
}

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = stringResource(R.string.error),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
        )
    }
}

@PreviewLightDark
@Composable
fun JokeListPreview() {
    MaterialTheme {
        val jokes = listOf(
            Joke(
                1,
                "general",
                "What did the fish say when it hit the wall?",
                "Dam.",
            ),
            Joke(
                2,
                "general",
                "How do you make a tissue dance?",
                "You put a little boogie on it.",
            )
        )
        JokeList(
            list = jokes,
            onItemClick = {}
        )
    }
}