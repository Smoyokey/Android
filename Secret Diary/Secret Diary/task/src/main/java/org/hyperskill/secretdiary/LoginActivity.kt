package org.hyperskill.secretdiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText

const val PIN = "1234"

class LoginActivity : AppCompatActivity() {

    lateinit var etPin: EditText
    lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var pinWrite: String = ""

        etPin = findViewById(R.id.etPin)
        btnLogin = findViewById(R.id.btnLogin)

        etPin.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                pinWrite = p0.toString()
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })
        btnLogin.setOnClickListener {
            if(pinWrite == PIN) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                etPin.setError("Wrong PIN!")
            }
        }
    }
}