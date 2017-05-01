### What is a Accelerometer?
A accelerometer is a small device that measures acceleration force like gravity, which is a static force, or a dynamic force like moving or shaking the sensor. Some accelerometers only measures the general change in velocity, but typical sensors measures the [proper acceleration](https://en.wikipedia.org/wiki/Proper_acceleration) along three axis: X, Y and Z.

![xyz](http://www.werkstattworkshop.com/sites/all/themes/media/projects/acc/xyz.png) 


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
