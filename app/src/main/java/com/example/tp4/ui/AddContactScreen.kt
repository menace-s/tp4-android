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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactScreen(
    onAddContact: (nom: String, telephone: String) -> Unit,
    onNavigateUp: () -> Unit // Pour gérer le clic sur la flèche de retour
) {
    var nom by remember { mutableStateOf("") }
    var telephone by remember { mutableStateOf("") }
    var isNomError by remember { mutableStateOf(false) }
    var isTelephoneError by remember { mutableStateOf(false) }

    // --- MODIFICATION : On utilise un Scaffold pour la structure de l'écran ---
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ajouter un contact") },
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
                .padding(innerPadding) // Padding fourni par le Scaffold
                .padding(16.dp), // Notre padding personnel
            verticalArrangement = Arrangement.spacedBy(16.dp) // Espace vertical entre les éléments
        ) {
            // --- MODIFICATION : Ajout d'une icône et du type de clavier ---
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

            // --- MODIFICATION : Ajout d'une icône et du type de clavier ---
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

            Spacer(modifier = Modifier.weight(1f)) // Pousse les boutons vers le bas

            // --- MODIFICATION : Styles de boutons différenciés ---
            Button(
                onClick = {
                    isNomError = nom.isBlank()
                    isTelephoneError = telephone.isBlank() || !telephone.all { it.isDigit() }

                    if (!isNomError && !isTelephoneError) {
                        onAddContact(nom, telephone)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ajouter")
            }

            // Bouton secondaire avec un style moins prononcé
            OutlinedButton(
                onClick = {
                    nom = ""
                    telephone = ""
                    isNomError = false
                    isTelephoneError = false
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Réinitialiser")
            }
        }
    }
}