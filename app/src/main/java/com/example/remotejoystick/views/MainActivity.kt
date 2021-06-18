package com.example.remotejoystick.views

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.remotejoystick.R
import com.example.remotejoystick.databinding.ActivityMainBinding
import ninja.eigenein.joypad.JoypadView

class MainActivity : AppCompatActivity(), JoypadView.Listener {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button7).setOnClickListener {
            doneClicked(it)
        }

        findViewById<JoypadView>(R.id.joypad_view).setListener(this)

    }


    fun doneClicked(view: View) {
        val ip_text = findViewById<EditText>(R.id.IPText)
        var text: String = ip_text.text.toString();
        Log.d("ip:", "$text")
    }

    override fun onUp() {
    }

    override fun onMove(p0: Float, p1: Float, p2: Float) {
        Log.d("test", "$p1,$p2,$p0")
    }
}


