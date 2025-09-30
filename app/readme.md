# Application de Gestion de Contacts (TP SQLite Modernisé)

## 📝 Description du Projet

Ce projet est une application Android simple de gestion de contacts (un nom et un numéro de téléphone). Il s'agit d'une modernisation complète d'un TP initialement prévu avec Java et SQLite natif.

Cette version a été reconstruite en utilisant les technologies et les architectures modernes recommandées par Google pour le développement Android.

**Technologies utilisées :**
* **Langage :** Kotlin
* **Interface Utilisateur :** Jetpack Compose
* **Base de données :** Room (une surcouche à SQLite)
* **Architecture :** MVVM (Model-View-ViewModel)
* **Asynchronisme :** Coroutines Kotlin
* **Navigation :** Navigation Compose

---

## 🏛️ Architecture

L'application suit une architecture **MVVM (Model-View-ViewModel)**, qui sépare clairement les responsabilités pour un code plus propre, plus testable et plus facile à maintenir.

Le flux de données est le suivant :

**View (Écrans Compose) <--> ViewModel <--> DAO (Room) <--> Base de données SQLite**

* La **View** (nos écrans) observe les données du ViewModel et lui envoie les actions de l'utilisateur.
* Le **ViewModel** contient la logique, prépare les données pour la View et interagit avec la couche de données (DAO).
* Le **DAO (Data Access Object)** est une interface qui définit comment on accède à la base de données. C'est Room qui implémente le code pour nous.

---

## 📂 Structure des Fichiers

Voici les fichiers clés que nous avons créés et leur rôle dans l'application :

### /data (La couche de données)
* **`Contact.kt`** : C'est une `data class` qui représente notre **Entité**. C'est le plan de notre table "contacts" dans la base de données.
* **`ContactDao.kt`** : C'est notre **DAO (Data Access Object)**. Une interface qui définit toutes les opérations possibles sur la table des contacts (`@Insert`, `@Update`, `@Query`, etc.). C'est le "menu d'actions" de notre base de données.
* **`ContactDatabase.kt`** : La classe qui représente la base de données elle-même. Elle lie l'entité `Contact` et le `ContactDao` ensemble.

### /ui (La couche de présentation)
* **`MainViewModel.kt`** : Le **ViewModel** de notre application. C'est le cerveau qui contient toute la logique métier : initialiser la base, ajouter un contact, mettre à jour, charger la liste, etc.
* **`MainScreen.kt`** : Le Composable pour l'écran du **menu principal**. Il affiche les différentes options (Afficher, Ajouter, Modifier, Initialiser).
* **`ContactListScreen.kt`** : Le Composable qui affiche la **liste des contacts**. Il est réutilisable et prend une liste de contacts en paramètre.
* **`AddContactScreen.kt`** : Le Composable pour le **formulaire d'ajout** de contact, avec sa propre logique de validation.
* **`EditContactScreen.kt`** : Le Composable pour le **formulaire de modification**, qui vient pré-rempli avec les données d'un contact existant.

### / (Racine de l'application)
* **`MainActivity.kt`** : L'unique `Activity` de notre application. Elle sert de conteneur et met en place le **graphe de navigation (`NavHost`)** pour gérer le passage entre les différents écrans Composable.
* **`ContactsApplication.kt`** : Une classe qui hérite de `Application` pour gérer la création d'une **instance unique (singleton)** de notre base de données pour toute l'application.

---

## 🔄 Exemple de Flux : "Ajouter un contact"

Pour bien comprendre comment les fichiers sont liés, voici le déroulement d'une action, du clic de l'utilisateur jusqu'à la mise à jour de la base de données :

1.  **`MainScreen` (View)** : L'utilisateur clique sur le `MenuItem` "Ajouter un contact".
2.  **`MainActivity` (Navigation)** : Le `NavHost` reçoit l'action et navigue vers la route `"add_contact"`.
3.  **`AddContactScreen` (View)** : Le formulaire s'affiche. L'utilisateur remplit les champs et clique sur le bouton "Ajouter ce contact".
4.  **`MainActivity` (Navigation)** : L'action `onClick` du bouton est interceptée par le `composable("add_contact")`. Il appelle la fonction correspondante du ViewModel : `viewModel.addContact(nom, telephone)`.
5.  **`MainViewModel` (ViewModel)** : La fonction `addContact` reçoit le nom et le téléphone, crée un objet `Contact`, et lance une coroutine pour appeler le DAO.
6.  **`ContactDao` (DAO)** : Le ViewModel appelle `dao.insertContact(nouveauContact)`.
7.  **Room / SQLite (Model)** : Room prend le relais et exécute la requête SQL d'insertion dans la base de données de manière asynchrone.
8.  **`MainActivity` (Navigation)** : Après l'appel au ViewModel, `navController.popBackStack()` est exécuté, ce qui ramène l'utilisateur à l'écran précédent (le menu).

Quand l'utilisateur naviguera de nouveau vers la liste, `viewModel.loadContacts()` sera appelé, lira les nouvelles données, mettra à jour l'état, et l'interface affichera le contact fraîchement ajouté.