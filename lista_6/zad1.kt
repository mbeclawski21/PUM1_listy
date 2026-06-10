package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WebsiteList()
        }
    }
}

@Composable
fun WebsiteList() {
    // 1. Definicja listy danych
    val websites = listOf(
        "https://www.google.com",
        "https://developer.android.com",
        "https://kotlinlang.org"
    )

    // Uzyskanie Contextu potrzebnego do uruchomienia Intenta
    val context = LocalContext.current

    // 2. Wykorzystanie LazyColumn do wyświetlenia listy
    LazyColumn {
        items(websites) { url ->
            Text(
                text = url,
                Modifier
                    .fillMaxWidth()
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse(url)
                        }
                        context.startActivity(intent)
                    }
                    .padding(16.dp) // padding wewnątrz obszaru klikalnego
            )
        }
    }
}