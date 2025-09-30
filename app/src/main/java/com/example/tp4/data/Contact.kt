
//Le plan de notre table.
package com.example.tp4.data

// On importe les annotations dont on a besoin depuis la bibliothèque Room
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// @Entity indique à Room que cette classe représente une table.
// On lui donne le nom de la table, comme dans le TP. [cite: 18]
@Entity(tableName = "contacts")
data class Contact(

    // @PrimaryKey indique que 'id' est la clé primaire.
    // autoGenerate = true veut dire que la base de données va générer un ID unique
    // pour chaque nouveau contact, on n'a pas à s'en soucier. [cite: 20, 25]
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // On met une valeur par défaut

    // @ColumnInfo permet de donner un nom spécifique à la colonne dans la base de données.
    // C'est plus propre que d'utiliser des constantes partout. [cite: 21]
    @ColumnInfo(name = "name")
    val nom: String,

    // On fait de même pour le numéro de téléphone. [cite: 22]
    @ColumnInfo(name = "phone_number")
    val numTelephone: String
)