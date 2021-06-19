package com.example.remotejoystick.model

import android.util.Log
import java.io.OutputStream
import java.lang.Exception
import java.net.Socket
import java.nio.charset.Charset
import java.util.*
import kotlin.concurrent.thread


class FGPlayer {
    private lateinit var client: Socket
    private var _running: Boolean = false
    private lateinit var _ip: String
    private var _port: Int = 0
    private lateinit var _thread: Thread

    // data bind
    var aileron: String = "0.5"
    var elevator: String  = "0.5"
    var rudder: String = "0.5"
    var throttle: String  = "0.5"


    fun play(ip: String, port: Int) {
        _ip = ip
        _port = port
        if (!_running) {
            _running = true
            _thread = Thread(Runnable { run() })
            _thread.start()
        } else if (_port != port && _ip != ip) {
            _thread = Thread(Runnable { run() })
            _thread.start()
        } else return
    }


    private fun run() {
        try {
            client = Socket(_ip, _port)
            Log.d("work","done")
        } catch (e: Exception) {
            _running = false
            Log.d("work",e.toString())
            return
        }
        val writer: OutputStream = client.getOutputStream()
        while (_running) {
            write(writer,"set/controls/flight/aileron $throttle")
            write(writer,"set/controls/flight/elevator $rudder")
            write(writer,"set/controls/flight/rudder $elevator")
            write(writer,"set/controls/flight/current-engine/throttle $aileron")
            Thread.sleep(100);
        }
    }


    private fun write(writer: OutputStream, message: String) {
        writer.write((message + "\r\n").toByteArray(Charset.defaultCharset()))
        writer.flush()
    }

}



