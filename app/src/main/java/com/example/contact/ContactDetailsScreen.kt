package com.example.contact

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.contact.ui.theme.ContactTheme

@Composable
fun ContactDetails(contact: Contact, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        PhotoOrInitials(contact = contact)
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = nameWithFavorite(contact),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))
        InfoRow(label = stringResource(R.string.phone), value = if (contact.isFavorite) contact.phone else "...")
        InfoRow(label = stringResource(R.string.address), value = contact.address)
        if (contact.email != null) {
            InfoRow(label = stringResource(R.string.email), value = contact.email)
        }
    }
}

@Composable
private fun PhotoOrInitials(contact: Contact) {
    val size = 128.dp
    if (contact.isFavorite) {
        Box(
            modifier = Modifier.size(size),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.circle),
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = initials(contact),
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }
    } else if (contact.imageRes != null) {
        Image(
            painter = painterResource(contact.imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
        )
    } else {
        Box(
            modifier = Modifier.size(size),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.circle),
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = initials(contact),
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 12.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

private fun fullName(contact: Contact): String {
    return listOfNotNull(contact.name, contact.surname, contact.familyName).joinToString(" ")
}

private fun nameWithFavorite(contact: Contact): AnnotatedString {
    val name = fullName(contact)
    if (!contact.isFavorite) return AnnotatedString(name)
    return buildAnnotatedString {
        append(name)
        append(" ")
        withStyle(SpanStyle(color = Color(0xFFFFC107))) {
            append("★")
        }
    }
}

private fun initials(contact: Contact): String {
    return (contact.name.take(1) + contact.familyName.take(1)).uppercase()
}

@Preview(showBackground = true)
@Composable
private fun FavoriteContactWithoutPhotoPreview() {
    ContactTheme {
        ContactDetails(
            contact = Contact(
                name = "Александр",
                surname = "Иванович",
                familyName = "Петров",
                isFavorite = true,
                phone = "+7 495 495 95 95",
                address = "г. Москва, ул. Примерная, 1",
                email = "petrov@example.com"
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NotFavoriteContactWithPhotoPreview() {
   ContactTheme {
        ContactDetails(
            contact = Contact(
                name = "Иван",
                familyName = "Сидоров",
                imageRes = R.drawable.photo_contact,
                phone = "+7 495 123 45 67",
                address = "г. Москва, ул. Другая, 2"
            )
        )
    }
}
