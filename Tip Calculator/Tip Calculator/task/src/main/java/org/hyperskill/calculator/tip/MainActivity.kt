package org.hyperskill.calculator.tip

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider
import org.hyperskill.calculator.tip.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView: TextView = findViewById(R.id.text_view)
        val editText: EditText = findViewById(R.id.edit_text)
        val slider: Slider = findViewById(R.id.slider)

        var value1: Double = 0.0
        var persentage: Int = 0

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                value1 = p0.toString().toDouble()
                if (value1 == 0.0) {
                    textView.setText("")
                } else {
                    textView.setText(
                        "Tip amount: ${
                            String.format(
                                "%.2f",
                                rachet(value1, persentage)
                            )
                        }$"
                    )
                }

            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        slider.addOnChangeListener { _, value, _ ->
            persentage = value.toInt()
            if (value1 != 0.0) {
                textView.setText(
                    "Tip amount: ${
                        String.format(
                            "%.2f",
                            rachet(value1, persentage)
                        )
                    }$"
                )
            }
        }
        val x = 0
        val test: TextView = findViewById(R.id.test)
        val rez = resources.getQuantityString(R.plurals.seats, x, x)
        test.setText(rez)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.test2.setText("test binding work?")

    }
}

fun rachet(value: Double, persentage: Int): Double {
    return value * persentage / 100
}