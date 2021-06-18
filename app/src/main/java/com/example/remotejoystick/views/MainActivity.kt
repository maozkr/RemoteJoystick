package com.example.remotejoystick.views

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
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
    }

    override fun onUp() {
    }

    override fun onMove(p0: Float, p1: Float, p2: Float) {
        Log.d("test", "$p1,$p2,$p0")
    }
}


