package com.example.formaapp.utils

import android.content.Context
import com.example.formaapp.R
import com.example.formaapp.data.models.Stagiaire
import org.xmlpull.v1.XmlPullParser

object XmlStagiairesHelper {
    fun lireStagiairesDepuisRes(context: Context): List<Stagiaire> {
        val stagiaires = mutableListOf<Stagiaire>()

        try {
            val parser = context.resources.getXml(R.xml.stagiaires)
            var eventType = parser.eventType

            var id = 0
            var nom = ""
            var prenom = ""
            var email = ""
            var mdp = ""
            var cp = 0
            var ville = ""
            var fonction = ""
            var id_public = 0

            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tag = parser.name

                when (eventType) {
                    XmlPullParser.START_TAG -> {
                        when (tag) {
                            "stagiaire" -> id = parser.getAttributeValue(null, "id")?.toIntOrNull() ?: 0
                            "nom" -> nom = parser.nextText()
                            "prenom" -> prenom = parser.nextText()
                            "email" -> email = parser.nextText()
                            "mdp" -> mdp = parser.nextText()
                            "cp" -> cp = parser.nextText().toIntOrNull() ?: 0
                            "ville" -> ville = parser.nextText()
                            "fonction" -> fonction = parser.nextText()
                            "id_public" -> id_public = parser.nextText().toIntOrNull() ?: 0
                        }
                    }

                    XmlPullParser.END_TAG -> {
                        if (tag == "stagiaire") {
                            stagiaires.add(
                                Stagiaire(id, nom, prenom, email, mdp, cp, ville, fonction, id_public)
                            )
                        }
                    }
                }

                eventType = parser.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return stagiaires
    }

}