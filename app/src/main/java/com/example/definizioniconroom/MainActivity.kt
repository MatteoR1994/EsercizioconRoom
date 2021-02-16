package com.example.definizioniconroom

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var definitionViewModel: DefinitionViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val definitionListAdapter = DefinitionsListAdapter(this)
        definitionsList.adapter = definitionListAdapter
        definitionsList.layoutManager = LinearLayoutManager(this)

        newDefBtn.setOnClickListener {
            val intent = Intent(this, NewDefinitionActivity::class.java)
            startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE)
        }

        definitionViewModel = ViewModelProviders.of(this).get(DefinitionViewModel::class.java)

        definitionViewModel.definitions.observe(this, { definitions ->
            definitions?.let {
                definitionListAdapter.setDefinitions(definitions)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val id = UUID.randomUUID().toString()
            val parolaTesto = data?.getStringExtra(NewDefinitionActivity.NEW_WORD)
            val descrizioneTesto = data?.getStringExtra(NewDefinitionActivity.NEW_DESCRIPTION)

            val defi = Definition(id, parolaTesto!!, descrizioneTesto!!)

            definitionViewModel.add(defi)

            Toast.makeText(this, "Salvato con successo.", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Salvataggio non avvenuto.", Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        private const val NEW_NOTE_ACTIVITY_REQUEST_CODE = 1
    }
}