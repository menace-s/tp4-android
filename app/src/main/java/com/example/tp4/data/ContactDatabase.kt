//La base de données qui assemble tout.
package com.example.tp4.data

import androidx.room.Database
import androidx.room.RoomDatabase

// @Database déclare que cette classe est une base de données Room.
// entities = [...] liste toutes les tables. Pour l'instant, on n'a que Contact.
// version = 1 est la première version de notre base de données.
@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {

    // Cette fonction permet à Room de savoir quel DAO est associé à cette base.
    // Room générera le code nécessaire pour nous fournir une instance de ContactDao.
    abstract fun contactDao(): ContactDao

}