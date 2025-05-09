package com.example.formaapp.data.models

data class Stagiaire(
    val id_stagiaire: Int,
    val nom: String,
    val prenom: String,
    val email: String,
    val mdp: String,
    val cp: Int,
    val ville: String,
    val fonction: String,
    val id_public: Int
)
