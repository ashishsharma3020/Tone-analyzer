package com.example.machine


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chaquo.python.PyException
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import kotlinx.coroutines.*
import android.app.AlertDialog
import android.content.Context
import android.view.inputmethod.InputMethodManager


class MainActivity : AppCompatActivity() {
    //@SuppressLint("CutPasteId")
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }
        val py = Python.getInstance()
        val module = py.getModule("plot")
        val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
       // if you want user to wait for some process to finish,

        builder.setView(R.layout.progress)
        val dialog: AlertDialog = builder.create()

        findViewById<Button>(R.id.button).setOnClickListener {

            currentFocus?.let {
                (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                    .hideSoftInputFromWindow(it.windowToken, 0)
            }
            dialog.show()
            try {


                val text = findViewById<EditText>(R.id.et).text.toString()
                if (text == "") {
                    dialog.dismiss()
                    findViewById<EditText>(R.id.et).error = "empty credential"
                    return@setOnClickListener
                }
                val songjson = module.callAttr("plot", text).toJava(String::class.java)
                Log.d("TAG", songjson)
                val intent = Intent(this@MainActivity, SongActivity::class.java)
                intent.putExtra("name", songjson)
                dialog.dismiss()
                startActivity(intent)

            } catch (e: PyException) {
                dialog.dismiss()
                Log.d("TAG", e.message.toString())
                Toast.makeText(this@MainActivity, "POOR CONNECTION", Toast.LENGTH_LONG).show()
            }

        }


    }

}