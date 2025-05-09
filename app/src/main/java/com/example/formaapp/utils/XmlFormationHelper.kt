package com.example.formaapp.utils

import android.content.Context
import com.example.formaapp.R
import com.example.formaapp.data.models.Formation
import org.xmlpull.v1.XmlPullParser

object XmlFormationHelper {

    fun lireFormationsDepuisRes(context: Context): List<Formation> {
        val formations = mutableListOf<Formation>()

        try {
            val parser = context.resources.getXml(R.xml.formation)
            var eventType = parser.eventType

            var id = 0
            var libelle = ""
            var objectifs: String? = null
            var dateDebut: String? = null
            var dateFin: String? = null
            var salle: String? = null
            var publicVise: String? = null
            var intervenants = mutableListOf<String>()

            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tag = parser.name

                when (eventType) {
                    XmlPullParser.START_TAG -> {
                        when (tag) {
                            "formation" -> {
                                id = parser.getAttributeValue(null, "id")?.toIntOrNull() ?: 0
                                intervenants = mutableListOf()
                            }
                            "libelle" -> libelle = parser.nextText()
                            "objectifs" -> objectifs = parser.nextText()
                            "date_debut" -> dateDebut = parser.nextText()
                            "date_fin" -> dateFin = parser.nextText()
                            "salle" -> salle = parser.nextText()
                            "public_vise" -> publicVise = parser.nextText()
                            "intervenant" -> intervenants.add(parser.nextText())
                        }
                    }
                    XmlPullParser.END_TAG -> {
                        if (tag == "formation") {
                            formations.add(
                                Formation(
                                    id_formation = id,
                                    libelle = libelle,
                                    objectifs = objectifs,
                                    date_debut = dateDebut,
                                    date_fin = dateFin,
                                    salle = salle,
                                    public_vise = publicVise,
                                    intervenants = intervenants.toList()
                                )
                            )
                        }
                    }
                }

                eventType = parser.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return formations
    }
}
