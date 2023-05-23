package org.hyperskill.calculator

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import org.hyperskill.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var str: String = ""
    var par1: Double = 0.0
    var par2: Double = 0.0
    var does: String = ""
    var hint: String = "0"
    var unarMinus: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.displayEditText.inputType = InputType.TYPE_NULL
        binding.displayEditText.setText(str)

        binding.button0.setOnClickListener {
            str += "0"
            binding.displayEditText.setText(str)
        }
        binding.button1.setOnClickListener {
            str += "1"
            binding.displayEditText.setText(str)
        }
        binding.button2.setOnClickListener {
            str += "2"
            binding.displayEditText.setText(str)
        }
        binding.button3.setOnClickListener {
            str += "3"
            binding.displayEditText.setText(str)
        }
        binding.button4.setOnClickListener {
            str += "4"
            binding.displayEditText.setText(str)
        }
        binding.button5.setOnClickListener {
            str += "5"
            binding.displayEditText.setText(str)
        }
        binding.button6.setOnClickListener {
            str += "6"
            binding.displayEditText.setText(str)
        }
        binding.button7.setOnClickListener {
            str += "7"
            binding.displayEditText.setText(str)
        }
        binding.button8.setOnClickListener {
            str += "8"
            binding.displayEditText.setText(str)
        }
        binding.button9.setOnClickListener {
            str += "9"
            binding.displayEditText.setText(str)
        }
        binding.dotButton.setOnClickListener {
            if(str.isEmpty()) {
                str += "0."
                binding.displayEditText.setText(str)
            }
            if(str.indexOf('.') == -1) {
                str += "."
                binding.displayEditText.setText(str)
            }
        }
        binding.clearButton.setOnClickListener {
            str = ""
            binding.displayEditText.setText(str)
            hint = "0"
            binding.displayEditText.setHint(hint)
        }

        binding.addButton.setOnClickListener {
            par1 = if(str.isEmpty()) hint.toDouble() else str.toDouble()
            str = ""
            does = "+"
            hint = par1.toString()
            binding.displayEditText.setHint(hint)
            binding.displayEditText.setText(str)
        }

        binding.subtractButton.setOnClickListener {
            if(str.isEmpty() && hint == "0") {
                str = "-"
                binding.displayEditText.setText(str)
            } else if(does == "-") {
                str = "-"
                binding.displayEditText.setText(str)
            } else {
                par1 = if(str.isEmpty()) hint.toDouble() else str.toDouble()
                str = ""
                does = "-"
                hint = par1.toString()
                binding.displayEditText.setHint(hint)
                binding.displayEditText.setText(str)
            }
        }

        binding.divideButton.setOnClickListener {
            par1 = if(str.isEmpty()) hint.toDouble() else str.toDouble()
            str = ""
            does = "/"
            hint = par1.toString()
            binding.displayEditText.setHint(hint)
            binding.displayEditText.setText(str)
        }

        binding.multiplyButton.setOnClickListener {
            par1 = if(str.isEmpty()) hint.toDouble() else str.toDouble()
            str = ""
            does = "*"
            hint = par1.toString()
            binding.displayEditText.setHint(hint)
            binding.displayEditText.setText(str)
        }

        binding.equalButton.setOnClickListener {
            par2 = if(str.isEmpty()) par2 else str.toDouble()
            par2 = if(par2 == 0.0) par1 else par2
            str = (when(does) {
                "+" -> "${par1 + par2}"
                "-" -> "${par1 - par2}"
                "/" -> "${par1 / par2}"
                "*" -> "${par1 * par2}"
                else -> str
            })
            par1 = str.toDouble()
            hint = str
            str = ""
            binding.displayEditText.setText(str)
            binding.displayEditText.setHint(hint)
            //does = ""
        }
    }
}