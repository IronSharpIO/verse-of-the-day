package pro.fullstackdevs.verse_of_the_day

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pro.fullstackdevs.verse_of_the_day.ui.theme.VerseofthedayTheme

import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastCbrt
import androidx.core.text.buildSpannedString
import pro.fullstackdevs.verse_of_the_day.ui.theme.VerseofthedayTheme


class MainActivity : ComponentActivity() {

    private val viewModel: VerseViewModel by viewModels {
        VerseViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VerseofthedayTheme {
              Surface (
                  modifier = Modifier.fillMaxSize(),
                  color = MaterialTheme.colorScheme.background
              ) {
                  VerseListScreen(viewModel)
              }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Composable
fun GreetingPreview() {
    VerseofthedayTheme {
        Greeting("Android")
    }
}



@Composable
fun VerseListScreen(viewModel: VerseViewModel) {
    val verses by viewModel.allVerses.collectAsState(initial = emptyList())

    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ){
        items(verses) { verse ->
            VerseCard(verse)
        }
    }
}


@Composable
fun VerseCard(verse: Verse) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation()
    ) {
        Text(text = verse.text, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = verse.reference, style = MaterialTheme.typography.bodySmall)
        Text(text = "Read: ${verse.read}", style = MaterialTheme.typography.labelSmall)
    }
}

@Preview(showBackground = true)
@Composable
fun VerseListPreview() {
    val sampleVerse = listOf(
        Verse(
            id = 1,
            reference = "Matthew 5:5",
            text = "Blessed are the meek: for they shall inherit the earth.",
            dateShown = "2025-06-24",
            read = false
        ),
        Verse(
            id = 2,
            reference = "John 3:16",
            text= "For God so loved the world...",
            dateShown = "2025-6-24",
            read = false
        )
    )

    VerseofthedayTheme {
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            items(sampleVerse) { verse ->
                VerseCard(verse)
            }
        }
    }
}