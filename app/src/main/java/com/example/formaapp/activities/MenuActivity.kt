package com.example.formaapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.formaapp.R

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // Récupération depuis SharedPreferences
        val sharedPref = getSharedPreferences("user_data", MODE_PRIVATE)
        val nom = sharedPref.getString("nom", "") ?: ""
        val prenom = sharedPref.getString("prenom", "") ?: ""

        val messageBienvenue = "Bienvenue $prenom $nom"
        findViewById<TextView>(R.id.textViewBienvenue).text = messageBienvenue

        val btnConsulter = findViewById<Button>(R.id.buttonConsulter)
        btnConsulter.setOnClickListener {
            val intent = Intent(this, ConsultationActivity::class.java)
            startActivity(intent)
        }

        val btnDeconnexion = findViewById<Button>(R.id.buttonDeconnexion)
        btnDeconnexion.setOnClickListener {
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
