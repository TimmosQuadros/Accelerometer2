package com.example.accelerometer

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat

class MainActivity(internal var accelerometer: Sensor,internal var sm: SensorManager,internal var acceleration: TextView) : AppCompatActivity(), SensorEventListener {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sm = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)

        acceleration = findViewById(R.id.acceleration) as TextView
        decimalFormat.applyPattern("#0.##")
    }
    var counter = 0
    var sum_x = 0f
    var sum_y = 0f
    var sum_z = 0f
    var value = 1000000
    val decimalFormat = DecimalFormat()
    override fun onSensorChanged(event: SensorEvent) {

        if(counter!=value){
            sum_x += event.values[0]
            sum_y += event.values[1]
            sum_z += event.values[2]
            counter++;
        }else {
            acceleration.text = "X: " + decimalFormat.format(sum_x/value) + " Y: " + decimalFormat.format(sum_y/value) + " Z: " + decimalFormat.format(sum_z/value)
            counter = 0
            sum_x = 0f
            sum_y = 0f
            sum_z = 0f
        }

    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {

    }
}
