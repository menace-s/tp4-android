package com.example.tp4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tp4.data.ContactDao
import com.example.tp4.ui.AddContactScreen
import com.example.tp4.ui.ContactListScreen
import com.example.tp4.ui.EditContactScreen
import com.example.tp4.ui.MainScreen
import com.example.tp4.ui.MainViewModel
import com.example.tp4.ui.theme.Tp4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tp4Theme {
                val dao = (application as ContactsApplication).database.contactDao()
                val viewModelFactory = MainViewModelFactory(dao)
                val viewModel: MainViewModel = viewModel(factory = viewModelFactory)
                val contactsState by viewModel.contacts.collectAsState()

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "main") {
                    composable("main") {
                        MainScreen(
                            onInitializeClick = viewModel::initializeDatabase,
                            onShowContactsClick = {
                                viewModel.loadContacts()
                                navController.navigate("contact_list")
                            },
                            onAddContactClick = {
                                navController.navigate("add_contact")
                            },
                            // Quand on clique sur "Modifier", on va aussi à la liste
                            onModifyContactClick = {
                                viewModel.loadContacts()
                                navController.navigate("contact_list")
                            }
                        )
                    }

                    // MODIFICATION : On rend la liste cliquable
                    composable("contact_list") {
                        ContactListScreen(
                            contacts = contactsState,
                            onContactClick = { contact ->
                                // Au clic, on navigue vers l'écran d'édition
                                // en passant l'ID du contact dans la route
                                navController.navigate("edit_contact/${contact.id}")
                            }
                        )
                    }

                    // AJOUT : La nouvelle route pour l'écran de modification
                    composable(
                        route = "edit_contact/{contactId}",
                        arguments = listOf(navArgument("contactId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val contactId = backStackEntry.arguments?.getInt("contactId")
                        val contactToEdit = contactsState.find { it.id == contactId }

                        if (contactToEdit != null) {
                            EditContactScreen(
                                contact = contactToEdit,
                                onUpdateContact = { updatedContact ->
                                    viewModel.updateContact(updatedContact)
                                    navController.popBackStack() // Revenir en arrière
                                }
                            )
                        }
                    }

                    composable("add_contact") {
                        AddContactScreen(
                            onAddContact = { nom, telephone ->
                                viewModel.addContact(nom, telephone)
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}

// La factory ne change pas
class MainViewModelFactory(private val dao: ContactDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}