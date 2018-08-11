# cordova-plugin-theta-plugin

## Installation

    cordova plugin add cordova-plugin-theta-plugin

## API

I wrapped the theta-plugin-sdk(https://github.com/ricohapi/theta-plugin-sdk) with cordova.

# API Reference

## window.plugins.theta.onKeyDownCallback(successCallback, errorCallback)
- successCallback(keyCode)  
  keyCode: 27: Shutter Button, 130: Mode Button, 284: Wireless Button can be specified

## window.plugins.theta.onKeyUpCallback(successCallback, errorCallback)
- successCallback(keyCode)  
  keyCode: 27: Shutter Button, 130: Mode Button, 284: Wireless Button can be specified

## window.plugins.theta.onKeyLongPressCallback(successCallback, errorCallback)
- successCallback(keyCode)  
  keyCode: 27: Shutter Button, 130: Mode Button, 284: Wireless Button can be specified

## window.plugins.theta.notificationCameraOpen() : return Promise

## window.plugins.theta.notificationCameraClose() : return Promise

## window.plugins.theta.notificationAudioShutter() : return Promise

## window.plugins.theta.notificationAudioOpen() : return Promise

## window.plugins.theta.notificationAudioClose() : return Promise

## window.plugins.theta.notificationAudioMovStart() : return Promise

## window.plugins.theta.notificationAudioMovStop() : return Promise

## window.plugins.theta.notificationAudioSelf() : return Promise

## window.plugins.theta.notificationAudioWarning() : return Promise

## window.plugins.theta.notificationLed3Show(ledColor: string) : return Promise
- ledColor: "RED", "GREEN",  "BLUE", "CYAN", "MAGENTA", "YELLOW", "WHITE"  

## window.plugins.theta.notificationLedShow(ledName: string, ledColor: string, period: number) : return Promise
- ledName: "LED3", "LED4", "LED5", "LED6", "LED7", "LED8"  
- ledColor: "RED", "GREEN",  "BLUE", "CYAN", "MAGENTA", "YELLOW", "WHITE"
- period: 250-2000 (msec)

## window.plugins.theta.notificationLedBlink(ledName: string) : return Promise
- ledName: "LED3", "LED4", "LED5", "LED6", "LED7", "LED8"  

## window.plugins.theta.notificationLedHide(ledName: string) : return Promise
- ledName: "LED3", "LED4", "LED5", "LED6", "LED7", "LED8"  

## window.plugins.theta.notificationWlanOff() : return Promise

## window.plugins.theta.notificationWlanAp() : return Promise

## window.plugins.theta.notificationWlanCl() : return Promise

## window.plugins.theta.notificationDatabaseUpdate(targets: string[]) : return Promise

## window.plugins.theta.notificationSuccess() : return Promise

## window.plugins.theta.notificationError(message: string) : return Promise
- message: set error message

## window.plugins.theta.notificationErrorOccured() : return Promise

# Under development API (It is still under development & debugging)

These APIs are still being tested.  
So I will not recommend using it.

## window.plugins.theta.setAudioBformat() : return Promise

## window.plugins.theta.setAudioMonaural() : return Promise

## window.plugins.theta.setMicSelectAuto() : return Promise

## window.plugins.theta.setMicSelectInternal() : return Promise

## window.plugins.theta.setMicSelectExternal() : return Promise

## window.plugins.theta.setMicSurroundVolumeLevelNormal() : return Promise

## window.plugins.theta.setMicSurroundVolumeLevelLarge() : return Promise

## window.plugins.theta.setShootingMode() : return Promise

## window.plugins.theta.setStitching() : return Promise

## window.plugins.theta.setExposureMode() : return Promise

# Example Code

```JavaScript
const app = {
    initialize: function() {
        console.log("initialize");
        document.addEventListener('deviceready', this.onDeviceReady.bind(this), false);
    },
    onDeviceReady: function() {
        this.receivedEvent('deviceready');
    },
    // Update DOM on a Received Event
    receivedEvent: async (id) => {
        console.log('Received Event: ' + id);
        try{
            // Call Theta Plugin API
           await window.plugins.theta.notificationWlanOff().catch((e) => { console.error(e) });
           await window.plugins.theta.notificationWlanAp().catch((e) => { console.error(e) });
           await window.plugins.theta.notificationWlanCl().catch((e) => { console.error(e) });
           await window.plugins.theta.notificationCameraClose().catch((e) => { console.error(e) });
           await window.plugins.theta.notificationCameraOpen().catch((e) => { console.error(e) });
           await window.plugins.theta.notificationAudioShutter().catch((e) => { console.error(e) });
           await window.plugins.theta.notificationAudioOpen().catch((e) => { console.error(e) });
           await window.plugins.theta.notificationAudioClose().catch((e) => { console.error(e) });
           await window.plugins.theta.notificationAudioMovStart().catch((e) => { console.error(e) });
           await window.plugins.theta.notificationAudioMovStop().catch((e) => { console.error(e) });
           await window.plugins.theta.notificationAudioSelf().catch((e) => { console.error(e) });
           await window.plugins.theta.notificationAudioWarning().catch((e) => { console.error(e) });
           await window.plugins.theta.notificationLed3Show("RED").catch((e) => { console.error(e) });
           await window.plugins.theta.notificationLedShow("LED3").catch((e) => { console.error(e) });
           await window.plugins.theta.notificationLedBlink("LED3", "RED", 2000).catch((e) => { console.error(e) });
           await window.plugins.theta.notificationLedHide("LED3").catch((e) => { console.error(e) });
           await window.plugins.theta.notificationDatabaseUpdate(["/sdcard/DCIM"]).catch((e) => { console.error(e) });
           await window.plugins.theta.notificationSuccess().catch((e) => { console.error(e) });
           await window.plugins.theta.notificationError("message").catch((e) => { console.error(e) });
        }catch (e) {
            console.error("Received Event:", e);
        }
    }
};
app.initialize();

```

# Example Cordova Project

## Vue.js Example

[simple_example](/packages/simple_example/README.md)

[example_vue](/packages/theta-plugin.md/README.md)

