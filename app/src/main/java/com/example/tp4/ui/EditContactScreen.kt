package com.example.tp4.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tp4.data.Contact

@Composable
fun EditContactScreen(
    contact: Contact,
    onUpdateContact: (Contact) -> Unit
) {
    // On initialise les états avec les valeurs du contact existant
    var nom by remember { mutableStateOf(contact.nom) }
    var telephone by remember { mutableStateOf(contact.numTelephone) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = nom,
            onValueChange = { nom = it },
            label = { Text("Nom") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = telephone,
            onValueChange = { telephone = it },
            label = { Text("Numéro de téléphone") }
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = {
            // On crée un nouvel objet Contact avec l'id original et les nouvelles valeurs
            val updatedContact = contact.copy(nom = nom, numTelephone = telephone)
            onUpdateContact(updatedContact)
        }) {
            Text("Modifier")
        }
    }
}