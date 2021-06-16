package com.example.remotejoystick.view_model

import androidx.lifecycle.ViewModel
import com.example.remotejoystick.model.FGPlayer


class UserViewModel : ViewModel() {
    private var player: FGPlayer = FGPlayer()
}