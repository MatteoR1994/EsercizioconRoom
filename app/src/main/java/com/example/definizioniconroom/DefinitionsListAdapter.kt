package com.example.definizioniconroom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_element.view.*

class DefinitionsListAdapter(private val context: Context) : RecyclerView.Adapter<DefinitionsListAdapter.DefinitionsViewHolder>() {

    private var definitionsList: List<Definition> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionsViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_element,parent,false)
        return DefinitionsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DefinitionsViewHolder, position: Int) {
        val definition = definitionsList[position]
        holder.setData(definition.word, definition.description, position)
    }

    override fun getItemCount(): Int {
        return definitionsList.size
    }

    fun setDefinitions(def: List<Definition>) {
        definitionsList = def
        notifyDataSetChanged()
    }

    inner class DefinitionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var pos: Int = 0
        fun setData(parola: String, descrizione: String, posizione: Int) {
            itemView.wordText.text = parola
            itemView.descriptionText.text = descrizione
            this.pos = posizione
        }
    }
}