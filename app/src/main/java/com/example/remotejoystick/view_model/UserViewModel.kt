package com.example.remotejoystick.view_model

import androidx.lifecycle.ViewModel
import com.example.remotejoystick.model.FGPlayer


class UserViewModel : ViewModel() {
    private var player: FGPlayer = FGPlayer()

    fun play(ip: String, port: Int) {
        player.play(ip, port)
    }


    // test
    fun update(param: String, value: String) {
        if( param == "aileron") {
            player.aileron = value
        }
        if ( param == "elevator") {
            player.elevator = value
        }
        if ( param == "rudder") {
            player.rudder = value
        }
        if ( param == "throttle") {
            player.throttle = value
        }
    }

}