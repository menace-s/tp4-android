package com.example.tp4

import android.app.Application
import androidx.room.Room
import com.example.tp4.data.ContactDatabase

class ContactsApplication : Application() {
    // On utilise "lazy" pour que la base de données ne soit créée
    // que la première fois qu'on en a besoin.
    val database: ContactDatabase by lazy {
        Room.databaseBuilder(
            this,
            ContactDatabase::class.java,
            "contacts-db" // Nom du fichier de la base de données
        ).build()
    }
}