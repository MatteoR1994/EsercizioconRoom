package com.example.definizioniconroom

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_new_definition.*

class NewDefinitionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_definition)

        addDefBtn.setOnClickListener {
            val result = Intent()

            if(TextUtils.isEmpty(newWordInput.text) || TextUtils.isEmpty(newDescriptionInput.text)) {
                setResult(Activity.RESULT_CANCELED, result)
            } else {
                val parola = newWordInput.text.toString()
                val descrizione = newDescriptionInput.text.toString()

                result.putExtra(NEW_WORD, parola)
                result.putExtra(NEW_DESCRIPTION, descrizione)
                setResult(Activity.RESULT_OK, result)
            }
            finish()
        }
    }

    companion object {
        const val NEW_WORD = "nuova_parola"
        const val NEW_DESCRIPTION = "nuova_descrizione"
    }
}