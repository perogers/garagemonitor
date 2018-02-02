**Garage Monitor**

[![CircleCI](https://circleci.com/gh/perogers/garagemonitor.svg?style=svg)](https://circleci.com/gh/perogers/garagemonitor)

This is a Java / Raspberry Pi Application that will return a still image from a camera and provide the temperature.
The application uses Spring Boot.

The following API calls are available:

* GET `/`  or  `/garage/temperature` 
    * Returns temperature reading from DS18S20 sensor as JSON data
* GET `/door/image`
    * Returns static image from either internal JPEG file or from file using command line parameter --door.image
* GET `/system`
    * Returns system information as JSON

The Pi camera code usings Process calls to *raspistill* and retrieves the image data from that programs's standard output.

See:
[Python Flask Version](https://github.com/perogers/garage_monitor "Garage Monitor Python Flask")
