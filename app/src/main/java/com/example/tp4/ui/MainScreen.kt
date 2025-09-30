package com.example.tp4.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
fun MainScreen(
    onInitializeClick: () -> Unit,
    onAddContactClick: () -> Unit,
    onShowContactsClick: () -> Unit, // Ajout pour la navigation
    onModifyContactClick: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Voulez vous ?",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        MenuItem("Afficher tous vos contacts") { onShowContactsClick() } // Appel de la navigation
        MenuItem("Ajouter un contact") { onAddContactClick() }
        MenuItem("Modifier un contact") { onModifyContactClick()}
        MenuItem("Initialisation de la base !") { onInitializeClick() }
    }
}
@Composable
fun MenuItem(text: String, onClick: () -> Unit) {
    // On utilise une Column qui est rendue cliquable sur toute sa surface.
    Column(modifier = Modifier.clickable(onClick = onClick)) {
        // Le texte de l'option
        Text(
            text = text,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth() // Prend toute la largeur
                .padding(vertical = 16.dp) // Ajoute de l'espace en haut et en bas
        )
        // La ligne de séparation
        HorizontalDivider(color = Color.LightGray)
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(onInitializeClick = {}, onShowContactsClick = {},onAddContactClick = {},onModifyContactClick={})
}