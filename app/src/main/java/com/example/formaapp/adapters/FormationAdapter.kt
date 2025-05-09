package com.example.formaapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.formaapp.R
import com.example.formaapp.data.models.Formation

class FormationAdapter(
    private var formations: List<Formation>,
    private val onItemClick: (Formation) -> Unit
) : RecyclerView.Adapter<FormationAdapter.FormationViewHolder>() {

    inner class FormationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val libelleTextView: TextView = itemView.findViewById(R.id.textViewLibelle)
        val objectifsTextView: TextView = itemView.findViewById(R.id.textViewObjectifs)
        val dateTextView: TextView = itemView.findViewById(R.id.textViewDate)
        val salleTextView: TextView = itemView.findViewById(R.id.textViewSalle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_formation, parent, false)
        return FormationViewHolder(view)
    }

    override fun onBindViewHolder(holder: FormationViewHolder, position: Int) {
        val formation = formations[position]
        holder.libelleTextView.text = formation.libelle
        holder.objectifsTextView.text = formation.objectifs ?: "Objectifs non précisés"
        holder.dateTextView.text =
            "Du ${formation.date_debut ?: "?"} au ${formation.date_fin ?: "?"}"
        holder.salleTextView.text = "Salle : ${formation.salle ?: "Non définie"}"

        holder.itemView.setOnClickListener {
            onItemClick(formation)
        }
    }

    override fun getItemCount(): Int = formations.size

    fun updateData(newData: List<Formation>) {
        formations = newData
        notifyDataSetChanged()
    }

}

