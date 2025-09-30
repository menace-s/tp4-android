package com.example.tp4.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tp4.data.Contact

@Composable
fun ContactListScreen(contacts: List<Contact>, onContactClick: (Contact) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Vos Contacts :",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn {
            items(contacts) { contact ->
                Text(
                    text = "${contact.nom} - ${contact.numTelephone}",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                        .fillMaxWidth()
                        .clickable { onContactClick(contact) } // Rend le texte cliquable
                        .padding(vertical = 8.dp)
                )
            }
        }
    }
}