package org.hyperskill.secretdiary

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import org.hyperskill.secretdiary.databinding.ActivityMainBinding
import java.time.Clock
import java.time.LocalDateTime

const val PREFERENCES_NAME = "PREF_DIARY"
const val PREFERENCES_KEY = "KEY_DIARY_TEXT"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var str = ""
    var notify: MutableList<String> = mutableListOf()
    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        notify = getNotify(sharedPreferences)
        binding.tvDiary.setText(notify.joinToString("\n\n"))

        binding.etNewWriting.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                str = p0.toString()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
        binding.btnSave.setOnClickListener {
            if(str.trim().isNotEmpty()) {
                val time: LocalDateTime = LocalDateTime.now()
                val rezTime = "${time.year}-${time.monthValue}-${time.dayOfMonth} ${time.hour}:${time.minute}:${time.second}"
                val rez = "$rezTime\n$str"
                notify.add(0, rez)
                binding.tvDiary.setText(notify.joinToString("\n\n"))
                str = ""
                binding.etNewWriting.setText("")
                editor.putString(PREFERENCES_KEY, notify.joinToString("\n\n")).apply()
            } else {
                val toast = "Empty or blank input cannot be saved"
                Toast.makeText(this, toast, Toast.LENGTH_SHORT).show()
                binding.etNewWriting.setText("")
            }
        }

        binding.btnUndo.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle(R.string.title)
                .setMessage(R.string.message)
                .setPositiveButton(R.string.yes) {_, _ ->
                    if(notify.isNotEmpty()) {
                        notify.removeAt(0)
                        binding.tvDiary.setText(notify.joinToString("\n\n"))
                        editor.putString(PREFERENCES_KEY, notify.joinToString("\n\n")).apply()
                    }
                }
                .setNegativeButton(R.string.no, null)
                .show()
        }
    }

    fun getNotify(sharedPreferences: SharedPreferences): MutableList<String> {
        val a = sharedPreferences.getString(PREFERENCES_KEY, "")
        val b = a.toString().split("\n\n").toMutableList()
        return b
    }

}