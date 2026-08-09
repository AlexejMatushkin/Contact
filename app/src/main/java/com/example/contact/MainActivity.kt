package com.example.contact

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.contact.ui.theme.ContactTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ContactDetails(
                        contact = testContact,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

private val testContact = Contact(
    name = "Александр",
    surname = "Иванович",
    familyName = "Петров",
    isFavorite = true,
    imageRes = R.drawable.photo_contact,
    phone = "+7 495 495 95 95",
    address = "г. Москва, ул. Примерная, 1",
    email = "petrov@example.com"
)
