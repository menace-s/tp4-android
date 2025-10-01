package com.example.tp4.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp4.data.Contact
import com.example.tp4.data.ContactDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Notre ViewModel a besoin d'accéder au DAO pour interagir avec la base de données.
class MainViewModel(private val dao: ContactDao) : ViewModel() {
    // On crée un "StateFlow". C'est comme un "tableau d'affichage" qui contient
    // la liste actuelle des contacts. L'interface va regarder ce tableau.
    private val _contacts = MutableStateFlow<List<Contact>>(emptyList())
    val contacts: StateFlow<List<Contact>> = _contacts.asStateFlow()

    // Le bloc "init" est appelé automatiquement à la création du ViewModel.
    // On charge les contacts une première fois au démarrage.
    init {
        loadContacts()
    }

    // Fonction pour charger (ou recharger) les contacts depuis la base.
    fun loadContacts() {
        viewModelScope.launch {
            _contacts.value = dao.getAllContacts()
        }
    }

    // Cette fonction sera appelée quand on cliquera sur le bouton "Initialisation".
    fun initializeDatabase() {
        // On lance une coroutine pour effectuer les opérations de base de données
        // en arrière-plan, grâce à viewModelScope.
        viewModelScope.launch {
            // 1. On vide la table
            dao.deleteAllContacts()

            // 2. On insère les 4 contacts de base du TP
            dao.insertContact(Contact(nom = "Jo", numTelephone = "9100000000"))
            dao.insertContact(Contact(nom = "Jack", numTelephone = "9199999999"))
            dao.insertContact(Contact(nom = "William", numTelephone = "9522222222"))
            dao.insertContact(Contact(nom = "Averell", numTelephone = "9533333333"))

            Log.d("MainViewModel", "4 contacts ont été insérés dans la base.")

            loadContacts()
        }
    }

    fun addContact(nom: String, telephone: String) {
        // On vérifie que les champs ne sont pas vides avant d'insérer
        if (nom.isBlank() || telephone.isBlank()) {
            return
        }
        viewModelScope.launch {
            val nouveauContact = Contact(nom = nom, numTelephone = telephone)
            dao.insertContact(nouveauContact)
        }
    }

    fun updateContact(contact: Contact) {
        viewModelScope.launch {
            dao.updateContact(contact)
            loadContacts()
        }
    }
    fun deleteContact(contact: Contact) {
        viewModelScope.launch {
            dao.deleteContact(contact)
            // On recharge la liste pour que la suppression soit visible
            loadContacts()
        }
    }
}