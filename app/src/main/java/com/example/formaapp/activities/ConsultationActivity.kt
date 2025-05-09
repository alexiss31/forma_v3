package com.example.formaapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.formaapp.R
import com.example.formaapp.adapters.FormationAdapter
import com.example.formaapp.data.models.Formation
import com.example.formaapp.utils.XmlFormationHelper
import java.text.SimpleDateFormat
import java.util.*

class ConsultationActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FormationAdapter
    private lateinit var allFormations: List<Formation>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultation)

        recyclerView = findViewById(R.id.recyclerViewFormations)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val textViewAucuneFormation = findViewById<TextView>(R.id.textViewAucuneFormation)
        val editDateDebut = findViewById<EditText>(R.id.editDateDebut)
        val editDateFin = findViewById<EditText>(R.id.editDateFin)
        val buttonFiltrer = findViewById<Button>(R.id.buttonFiltrer)

        allFormations = XmlFormationHelper.lireFormationsDepuisRes(this)

        if (allFormations.isEmpty()) {
            recyclerView.visibility = RecyclerView.GONE
            textViewAucuneFormation.visibility = TextView.VISIBLE
        } else {
            recyclerView.visibility = RecyclerView.VISIBLE
            textViewAucuneFormation.visibility = TextView.GONE
        }

        adapter = FormationAdapter(allFormations) { formation ->
            val intent = Intent(this, FormationDetailsActivity::class.java)
            intent.putExtra("formation", formation)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        val btnRetour = findViewById<Button>(R.id.buttonRetour)
        btnRetour.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        buttonFiltrer.setOnClickListener {
            val debutStr = editDateDebut.text.toString()
            val finStr = editDateFin.text.toString()

            try {
                val inputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE)
                val xmlFormat = SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE)

                val dateDebut = inputFormat.parse(debutStr)
                val dateFin = inputFormat.parse(finStr)

                if (dateDebut != null && dateFin != null) {
                    val filtered = allFormations.filter { formation ->
                        val dateFormation = xmlFormat.parse(formation.date_debut)
                        dateFormation != null &&
                                !dateFormation.before(dateDebut) &&
                                !dateFormation.after(dateFin)
                    }
                    adapter.updateData(filtered)

                    if (filtered.isEmpty()) {
                        Toast.makeText(this, "Aucune formation trouvée", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Dates invalides", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {
                Toast.makeText(this, "Format des dates incorrect (JJ/MM/AAAA)", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
