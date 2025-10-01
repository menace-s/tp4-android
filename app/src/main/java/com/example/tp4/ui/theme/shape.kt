package com.example.tp4.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

// On augmente la taille des arrondis
val Shapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp), // Parfait pour les boutons et les cartes
    large = RoundedCornerShape(24.dp)
)