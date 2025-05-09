package com.example.formaapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.formaapp.R
import com.example.formaapp.data.models.Formation

class FormationDetailsActivity : AppCompatActivity() {

    private var formation: Formation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formation_details)

        formation = intent.getSerializableExtra("formation") as? Formation

        findViewById<TextView>(R.id.textViewLibelleDetail).text = formation?.libelle
        findViewById<TextView>(R.id.textViewObjectifsDetail).text = formation?.objectifs
        findViewById<TextView>(R.id.textViewDateDetail).text =
            "Du ${formation?.date_debut ?: "?"} au ${formation?.date_fin ?: "?"}"
        findViewById<TextView>(R.id.textViewSalleDetail).text = "Salle : ${formation?.salle ?: "non définie"}"
        findViewById<TextView>(R.id.textViewPublicDetail).text = "Public visé : ${formation?.public_vise ?: "non précisé"}"
        findViewById<TextView>(R.id.textViewIntervenantsDetail).text =
            "Intervenants : ${formation?.intervenants?.joinToString(", ") ?: "non spécifiés"}"

        findViewById<Button>(R.id.buttonRetour).setOnClickListener {
            startActivity(Intent(this, ConsultationActivity::class.java))
            finish()
        }

        findViewById<Button>(R.id.buttonDeconnexion).setOnClickListener {
            // Effacer les données sauvegardées
            val sharedPref = getSharedPreferences("user_data", MODE_PRIVATE)
            sharedPref.edit().clear().apply()

            // Redirection vers l'écran de connexion
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

    }
}

