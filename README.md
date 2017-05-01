### What is a Accelerometer?
A accelerometer is a small device that measures acceleration force like gravity, which is a static force, or a dynamic force like moving or shaking the sensor. Some accelerometers only measures the general change in velocity, but typical sensors measures the [proper acceleration](https://en.wikipedia.org/wiki/Proper_acceleration) along three axis: X, Y and Z.

![xyz](http://www.werkstattworkshop.com/sites/all/themes/media/projects/acc/xyz.png) 

### How does a Accelerometer work?

There are several different ways a accelerometer is built, but one of the more commen (and cheap) ways is using an piezoelectic meter. 

Basically imagine a small box. Inside this box you have a ball. Whenever a movement occurs, the ball well hit on ore more of the walls, creating small piezo electric currents [1](https://diyhacking.com/arduino-mpu-6050-imu-sensor-tutorial/). 

![acce](https://301o583r8shhildde3s0vcnh-wpengine.netdna-ssl.com/wp-content/uploads/2014/11/acc.png)

### Accelerometers
Accelerometers are used in many devices that measures speed, acceleration, force or orientation. So for an example they come in quite hande in fighterjets, drones, GPS's, phones. When using a three axis accelerometer, or 3 single instances pointed in x,y,z direction, you can actually measure more than just acceleration. A drone and a fighter jet, needs to be kept stabil and level during flight. Here accelerometers & gyroscopes are used to compute engine thrust in the correct direction so the aircraft will remain level. The pilots job is only to chose direction and navigate.

### How do you use the accelerometers in an app?

First you need a few improts
```
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
```
Your kotlin class must take in a few aruments like so:
```
class MainActivity(
    internal var accelerometer: Sensor,
    internal var sm: SensorManager,
    internal var acceleration: TextView
) : AppCompatActivity(), SensorEventListener {
```

## Accelerometer

In kotlin there are some build in objects/classes that you can use to get acces to all the sensors of the smartphone. These classes are:

1. Sensor
2. SensorManager
```java
import android.hardware.Sensor;
import android.hardware.SensorManager;
```
Also we need to extend from AppCompatActivity() and we need to implement the SensorEventListener:

3. AppCompatActivity()
4. SensorEventListener

## Sensor

Class representing a sensor. Use getSensorList(int) to get the list of available Sensors.

```kotlin
sm.getSensorList(Sensor.TYPE_ALL)
```

## SesnorManager

SensorManager lets you access the device's sensors. Get an instance of this class by calling Context.getSystemService() with the argument SENSOR_SERVICE.

```kotlin
sm = getSystemService(Context.SENSOR_SERVICE) as SensorManager
accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
```
## AppCompatActivity()

When extending from this class we get a lot of functions among others the getSystemService which can be used to retreive SensorManager.

```kotlin
sm = getSystemService(Context.SENSOR_SERVICE) as SensorManager
```

## SensorEventListener

When implementing this interface we get two event functions that listens for any change in the selected Sensor type.

```kotlin
class MainActivity() : SensorEventListener{

  override fun onSensorChanged(event: SensorEvent) {

  }

  override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {

  }

}
```

In order for these events to work corectly and fire whenever a sensor changes its state we need to register the listener:

```kotlin
sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
```

Since the class itself is the SensorEventListener we give this as first argument for the listener. The seccond argument is the Sensor itself which in our case is the accelerometer. The last argument specifies how often we need to fire an event (i think).

## The app

```kotlin
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
```
