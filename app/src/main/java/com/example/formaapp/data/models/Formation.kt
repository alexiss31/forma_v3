package com.example.formaapp.data.models

import java.io.Serializable

data class Formation(
    val id_formation: Int,
    val libelle: String,
    val objectifs: String?,
    val date_debut: String?,
    val date_fin: String?,
    val salle: String?,
    val public_vise: String?,
    val intervenants: List<String>
) : Serializable

