package com.example.remotejoystick.model

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
    lateinit var aileron: String
    lateinit var elevator: String
    lateinit var rudder: String
    lateinit var throttle: String


    fun play(ip: String, port: Int) {
        _ip = ip
        _port = port
        if (!_running) {
            _running = true
            _thread = Thread(Runnable { run() })
            _thread.run()
        } else if (_port != port && _ip != ip) {
            _thread = Thread(Runnable { run() })
            _thread.run()
        } else return
    }


    private fun run() {
        try {
            client = Socket(_ip, _port)
        } catch (e: Exception) {
            return
        }
        val writer: OutputStream = client.getOutputStream()
        while (_running) {
            write(writer,"set /controls/flight/aileron $throttle")
            write(writer,"set /controls/flight/elevator $rudder")
            write(writer,"set /controls/flight/rudder $elevator")
            write(writer,"set /controls/flight/current-engine/throttle $aileron")
            Thread.sleep(100);
        }
    }


    private fun write(writer: OutputStream, message: String) {
        writer.write((message + "\r\n").toByteArray(Charset.defaultCharset()))
        writer.flush()
    }

}



