# cordova-plugin-theta-plugin

## Installation

    cordova plugin add cordova-plugin-theta-plugin

## API

I wrapped the theta-plugin-sdk(https://github.com/ricohapi/theta-plugin-sdk) with cordova.

# API Reference

## theta.onKeyDownCallback(successCallback, errorCallback)
- successCallback(keyCode)  
  keyCode: 27: Shutter Button, 130: Mode Button, 284: Wireless Button can be specified

## theta.onKeyUpCallback(successCallback, errorCallback)
- successCallback(keyCode)  
  keyCode: 27: Shutter Button, 130: Mode Button, 284: Wireless Button can be specified

## theta.onKeyLongPressCallback(successCallback, errorCallback)
- successCallback(keyCode)  
  keyCode: 27: Shutter Button, 130: Mode Button, 284: Wireless Button can be specified

## theta.notificationCameraOpen() : return Promise

## theta.notificationCameraClose() : return Promise

## theta.notificationAudioShutter() : return Promise

## theta.notificationAudioOpen() : return Promise

## theta.notificationAudioClose() : return Promise

## theta.notificationAudioMovStart() : return Promise

## theta.notificationAudioMovStop() : return Promise

## theta.notificationAudioSelf() : return Promise

## theta.notificationAudioWarning() : return Promise

## theta.notificationLed3Show(ledColor: string) : return Promise
- ledColor: "RED", "GREEN",  "BLUE", "CYAN", "MAGENTA", "YELLOW", "WHITE"  

## theta.notificationLedShow(ledName: string, ledColor: string, period: number) : return Promise
- ledName: "LED3", "LED4", "LED5", "LED6", "LED7", "LED8"  
- ledColor: "RED", "GREEN",  "BLUE", "CYAN", "MAGENTA", "YELLOW", "WHITE"
- period: 250-2000 (msec)

## theta.notificationLedBlink(ledName: string) : return Promise
- ledName: "LED3", "LED4", "LED5", "LED6", "LED7", "LED8"  

## theta.notificationLedHide(ledName: string) : return Promise
- ledName: "LED3", "LED4", "LED5", "LED6", "LED7", "LED8"  

## theta.notificationWlanOff() : return Promise

## theta.notificationWlanAp() : return Promise

## theta.notificationWlanCl() : return Promise

## theta.notificationDatabaseUpdate(targets: string[]) : return Promise

## theta.notificationSuccess() : return Promise

## theta.notificationError(message: string) : return Promise
- message: set error message

## theta.notificationErrorOccured() : return Promise

# Under development API (It is still under development & debugging)

These APIs are still being tested.  
So I will not recommend using it.

## theta.setAudioBformat() : return Promise

## theta.setAudioMonaural() : return Promise

## theta.setMicSelectAuto() : return Promise

## theta.setMicSelectInternal() : return Promise

## theta.setMicSelectExternal() : return Promise

## theta.setMicSurroundVolumeLevelNormal() : return Promise

## theta.setMicSurroundVolumeLevelLarge() : return Promise

## theta.setShootingMode() : return Promise

## theta.setStitching() : return Promise

## theta.setExposureMode() : return Promise

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
           await window.plugins.theta.notificationLedShow("LED1").catch((e) => { console.error(e) });
           await window.plugins.theta.notificationLedBlink("LED2", "RED", 2000).catch((e) => { console.error(e) });
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

[example_vue](/packages/theta-plugin.md/README.md)