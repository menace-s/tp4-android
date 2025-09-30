package com.example.tp4.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddContactScreen(
    onAddContact: (nom: String, telephone: String) -> Unit
) {
    var nom by remember { mutableStateOf("") }
    var telephone by remember { mutableStateOf("") }

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
            modifier = Modifier.fillMaxWidth(),
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
            modifier = Modifier.fillMaxWidth(),
            // --- AJOUT : Gestion de l'affichage de l'erreur ---
            isError = isTelephoneError,
            supportingText = {
                if (isTelephoneError) {
                    Text("Le numéro de téléphone est invalide.")
                }
            }
        )
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    // --- AJOUT : Logique de validation ---
                    isNomError = nom.isBlank()
                    // Un numéro est invalide s'il est vide ou ne contient pas que des chiffres
                    isTelephoneError = telephone.isBlank() || !telephone.all { it.isDigit() }

                    // Si aucune erreur n'est détectée, on ajoute le contact
                    if (!isNomError && !isTelephoneError) {
                        onAddContact(nom, telephone)
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Ajouter")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    nom = ""
                    telephone = ""
                    isNomError = false // On enlève aussi les erreurs
                    isTelephoneError = false
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Réinitialiser")
            }
        }
    }
}