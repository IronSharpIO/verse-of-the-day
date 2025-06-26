package pro.fullstackdevs.verse_of_the_day

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import pro.fullstackdevs.verse_of_the_day.navigation.AppNavigation
import pro.fullstackdevs.verse_of_the_day.ui.theme.VerseofthedayTheme

class MainActivity : ComponentActivity() {

    private val viewModel: VerseViewModel by viewModels {
        VerseViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val today = "2025-06-25"
        viewModel.insertVerse(
            Verse(
                reference = "Matthew 5:5",
                text = "Blessed are the meek: for they shall inherit the earth.",
                dateShown = today,
                read = false
            )
        )

        setContent {
            VerseofthedayTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    AppNavigation(navController = navController, viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun ProfileScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "\uD83D\uDC64 Profile", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onBack) {
            Text("Go Back")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerseListScreen(
    viewModel: VerseViewModel,
    onProfileClick: () -> Unit
) {
    val verses by viewModel.allVerses.collectAsState(initial = emptyList())

    Column {
        TopAppBar(
            title = { Text("Verse of the Day") },
            actions = {
                IconButton(onClick = onProfileClick) {
                    Icon(Icons.Default.Person, contentDescription = "Profile")
                }
            }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            items(verses) { verse ->
                VerseCard(verse = verse) {
                    if (!verse.read) {
                        val updatedVerse = verse.copy(read = true)
                        viewModel.updateVerse(updatedVerse)
                    }
                }
            }
        }
    }
}

@Composable
fun VerseCard(verse: Verse, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = verse.text, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = verse.reference, style = MaterialTheme.typography.bodySmall)
            Text(
                text = if (verse.read) "✅ Read" else "🕗 Not Read",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VerseListPreview() {
    val sampleVerses = listOf(
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
            text = "For God so loved the world...",
            dateShown = "2025-06-24",
            read = false
        )
    )

    VerseofthedayTheme {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            items(sampleVerses) { verse ->
                VerseCard(verse = verse) { /* no-op for preview */ }
            }
        }
    }
}
