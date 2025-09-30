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
    var nom by remember { mutableStateOf(contact.nom) }
    var telephone by remember { mutableStateOf(contact.numTelephone) }

    // --- AJOUT : On crée des états pour suivre les erreurs ---
    var isNomError by remember { mutableStateOf(false) }
    var isTelephoneError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = nom,
            onValueChange = {
                nom = it
                isNomError = false // On enlève l'erreur dès que l'utilisateur tape
            },
            label = { Text("Nom") },
            // --- AJOUT : Gestion de l'affichage de l'erreur ---
            isError = isNomError,
            supportingText = {
                if (isNomError) {
                    Text("Le nom ne peut pas être vide.")
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = telephone,
            onValueChange = {
                telephone = it
                isTelephoneError = false // On enlève l'erreur dès que l'utilisateur tape
            },
            label = { Text("Numéro de téléphone") },
            // --- AJOUT : Gestion de l'affichage de l'erreur ---
            isError = isTelephoneError,
            supportingText = {
                if (isTelephoneError) {
                    Text("Le numéro de téléphone est invalide.")
                }
            }
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = {
            // --- AJOUT : Logique de validation ---
            isNomError = nom.isBlank()
            isTelephoneError = telephone.isBlank() || !telephone.all { it.isDigit() }

            // Si aucune erreur n'est détectée, on met à jour le contact
            if (!isNomError && !isTelephoneError) {
                val updatedContact = contact.copy(nom = nom, numTelephone = telephone)
                onUpdateContact(updatedContact)
            }
        }) {
            Text("Modifier ce contact")
        }
    }
}