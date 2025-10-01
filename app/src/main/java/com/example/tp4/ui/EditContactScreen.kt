package com.example.tp4.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.tp4.data.Contact

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditContactScreen(
    contact: Contact,
    onUpdateContact: (Contact) -> Unit,
    onNavigateUp: () -> Unit, // Pour gérer le clic sur la flèche de retour
    onDeleteContact: () -> Unit
) {
    var nom by remember { mutableStateOf(contact.nom) }
    var telephone by remember { mutableStateOf(contact.numTelephone) }
    var isNomError by remember { mutableStateOf(false) }
    var isTelephoneError by remember { mutableStateOf(false) }

    // --- On utilise un Scaffold pour la structure de l'écran ---
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Modifier le contact") },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Retour"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // --- Ajout d'une icône et du type de clavier ---
            OutlinedTextField(
                value = nom,
                onValueChange = {
                    nom = it
                    isNomError = false
                },
                label = { Text("Nom") },
                modifier = Modifier.fillMaxWidth(),
                isError = isNomError,
                leadingIcon = {
                    Icon(Icons.Outlined.Person, contentDescription = "Nom")
                },
                supportingText = {
                    if (isNomError) {
                        Text("Le nom ne peut pas être vide.")
                    }
                },
                singleLine = true
            )

            // --- Ajout d'une icône et du type de clavier ---
            OutlinedTextField(
                value = telephone,
                onValueChange = {
                    telephone = it
                    isTelephoneError = false
                },
                label = { Text("Numéro de téléphone") },
                modifier = Modifier.fillMaxWidth(),
                isError = isTelephoneError,
                leadingIcon = {
                    Icon(Icons.Outlined.Phone, contentDescription = "Téléphone")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                supportingText = {
                    if (isTelephoneError) {
                        Text("Le numéro de téléphone est invalide.")
                    }
                },
                singleLine = true
            )

            Spacer(modifier = Modifier.weight(1f)) // Pousse le bouton vers le bas

            Button(
                onClick = {
                    isNomError = nom.isBlank()
                    isTelephoneError = telephone.isBlank() || !telephone.all { it.isDigit() }

                    if (!isNomError && !isTelephoneError) {
                        val updatedContact = contact.copy(nom = nom, numTelephone = telephone)
                        onUpdateContact(updatedContact)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Modifier ce contact")
            }
            // --- AJOUT : Le bouton pour supprimer ---
            TextButton(
                onClick = onDeleteContact,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Supprimer le contact", color = MaterialTheme.colorScheme.error)
            }

        }
    }
}