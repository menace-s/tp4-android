//Le menu des actions possibles.
package com.example.tp4.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

// L'annotation @Dao indique à Room que cette interface est un Data Access Object.
@Dao
interface ContactDao {

    // @Insert dit à Room que cette fonction insère des données.
    // Le mot-clé "suspend" est très important ! Il signifie que cette fonction
    // est une coroutine. C'est une sécurité pour nous forcer à l'appeler
    // en arrière-plan et ne jamais bloquer l'interface utilisateur.
    @Insert
    suspend fun insertContact(contact: Contact)

    // @Query permet d'écrire n'importe quelle requête SQL.
    // On sélectionne tous les contacts de la table "contacts" et on les ordonne par nom.
    @Query("SELECT * FROM contacts ORDER BY name ASC")
    suspend fun getAllContacts(): List<Contact>

    // Une requête pour vider toute la table.
    @Query("DELETE FROM contacts")
    suspend fun deleteAllContacts()

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)
}