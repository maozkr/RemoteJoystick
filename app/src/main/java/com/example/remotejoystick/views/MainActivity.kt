package com.example.remotejoystick.views

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.example.remotejoystick.R
import com.example.remotejoystick.view_model.UserViewModel
import ninja.eigenein.joypad.JoypadView


class MainActivity : AppCompatActivity(), JoypadView.Listener {

    private lateinit var viewModel : UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = UserViewModel()
        findViewById<Button>(R.id.button7).setOnClickListener { doneClicked(it) }
        findViewById<SeekBar>(R.id.seekBar).setOnSeekBarChangeListener(object :
            OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val throttle : Double = progress.div(100.0)
                viewModel.update("throttle", throttle.toString())
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        findViewById<SeekBar>(R.id.seekBar6).setOnSeekBarChangeListener(object :
            OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val rudder : Double = progress.minus(50.0).div(50.0)
                viewModel.update("rudder", rudder.toString())
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        findViewById<JoypadView>(R.id.joypad_view).setListener(this)

    }


    fun doneClicked(view: View) {
        val ipText = findViewById<EditText>(R.id.IPText)
        val portText = findViewById<EditText>(R.id.PortText)
        val ip: String = ipText.text.toString()
        val port: String = portText.text.toString()
        viewModel.play(ip,port.toInt())
    }

    override fun onUp() {
    }

    override fun onMove(p0: Float, p1: Float, p2: Float) {
        viewModel.update("aileron", p1.toString())
        viewModel.update("elevator", p2.toString())
    }


}


