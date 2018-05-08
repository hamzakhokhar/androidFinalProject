package com.example.hamzakhokhar.finalproject

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Vibrator
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.create_event.*

/**
 * Created by hamzakhokhar on 4/25/18.
 */class CreateEvent: AppCompatActivity(), SensorEventListener{

    var sensor: Sensor? = null
    var sensorManager: SensorManager? = null
    var xold = 0.0
    var yold = 0.0
    var zold = 0.0
    var threadShould = 3000.0
    var oldtime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_event)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        val event = EventActivity()

        edittext_name.onChange {
            event.name = it
        }
        edittext_type.onChange{
            event.type = it
        }
        edittext_startdate.onChange{
            event.startdate = it
        }
        edittext_enddate.onChange{
            event.enddate = it
        }
        edittext_starttime.onChange{
            event.starttime = it
        }
        edittext_endtime.onChange{
            event.endtime = it
        }
        address_layout.onChange{
            event.address = it
        }
        edittext_state.onChange{
            event.state = it
        }
        edittext_city.onChange{
            event.city = it
        }
        edittext_zip.onChange{
            event.zip = it
        }

        button_save.setOnClickListener {
            EventService.save(event);
            Toast.makeText(this, "Sucessfully Saved", Toast.LENGTH_SHORT).show()

        }

    }

    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {
        var x = event!!.values[0]
        var y = event!!.values[1]
        var z = event!!.values[2]
        var currentTime = System.currentTimeMillis()
        if((currentTime - oldtime) > 100) {
            var timeDiff = currentTime - oldtime
            oldtime = currentTime
            var speed = Math.abs(x+y+z-xold-yold-zold)/timeDiff*10000
            if(speed>threadShould) {
                var v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                v.vibrate(500)
                Toast.makeText(this, "Form deleted!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this,CreateEvent::class.java)
                startActivity(intent)

            }
        }
    }
}

fun EditText.onChange(cb: (String) -> Unit) {
    this.addTextChangedListener(object: TextWatcher {
        override fun afterTextChanged(s: Editable?) { cb(s.toString()) }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}