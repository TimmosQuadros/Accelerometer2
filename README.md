### What is an Accelerometer?
An accelerometer is a small device that measures acceleration. Some accelerometers only measures the general change in velocity, but typical sensors measures the [proper acceleration](https://en.wikipedia.org/wiki/Proper_acceleration) along three axis: X, Y and Z.



### Accelerometers
Accelerometers are used in bla bla bla...

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
