package com.example.tp4.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tp4.ui.theme.Tp4Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onInitializeClick: () -> Unit,
    onAddContactClick: () -> Unit,
    onShowContactsClick: () -> Unit,
    onModifyContactClick: () -> Unit
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Un en-tête plus accueillant
            Text(
                text = "Bienvenue !",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Que souhaitez-vous faire aujourd'hui ?",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            // --- NOUVELLE DISPOSITION EN GRILLE 2x2 ---
            // Première rangée
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                // Chaque MenuItem prend la moitié de l'espace grâce à weight(1f)
                MenuItem(
                    text = "Afficher les contacts",
                    icon = Icons.Outlined.List,
                    onClick = onShowContactsClick,
                    modifier = Modifier.weight(1f)
                )
                MenuItem(
                    text = "Ajouter un contact",
                    icon = Icons.Outlined.Add,
                    onClick = onAddContactClick,
                    modifier = Modifier.weight(1f)
                )
            }
            // Deuxième rangée
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                MenuItem(
                    text = "Modifier un contact",
                    icon = Icons.Outlined.Edit,
                    onClick = onModifyContactClick,
                    modifier = Modifier.weight(1f)
                )
                MenuItem(
                    text = "Initialiser la base",
                    icon = Icons.Outlined.Refresh,
                    onClick = onInitializeClick,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

// --- Le MenuItem a été repensé pour la grille ---
@Composable
fun MenuItem(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .aspectRatio(1f) // Force la carte à être carrée
            .clickable(onClick = onClick),
        shape = MaterialTheme.shapes.large, // Coins encore plus arrondis
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        // On utilise une Column pour mettre l'icône AU-DESSUS du texte
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(45.dp), // Icône plus grande
                tint = MaterialTheme.colorScheme.primary // Couleur visible
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Tp4Theme {
        MainScreen(
            onInitializeClick = {},
            onShowContactsClick = {},
            onAddContactClick = {},
            onModifyContactClick = {}
        )
    }
}