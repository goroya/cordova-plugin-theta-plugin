/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
import "babel-polyfill";

function sleep(ms){
    return new Promise(function (resolve) {
        setTimeout(resolve, ms)
    });
}

var app = {
    // Application Constructor
    initialize: function() {
        document.addEventListener('deviceready', this.onDeviceReady.bind(this), false);
    },

    // deviceready Event Handler
    //
    // Bind any cordova events here. Common events are:
    // 'pause', 'resume', etc.
    onDeviceReady: function() {
        this.receivedEvent('deviceready');
    },

    // Update DOM on a Received Event
    receivedEvent: async (id) => {
        console.log('Received Event: ' + id);
        try{
            console.log("Start:");
            await window.plugins.theta.notificationWlanOff().catch((e) => { console.error(e) });
            await sleep(2000);
            await window.plugins.theta.notificationWlanAp().catch((e) => { console.error(e) });
            await sleep(2000);
            await window.plugins.theta.notificationWlanCl().catch((e) => { console.error(e) });
            await sleep(2000);
            await window.plugins.theta.notificationCameraClose().catch((e) => { console.error(e) });
            await sleep(2000);
            await window.plugins.theta.notificationCameraOpen().catch((e) => { console.error(e) });
            await sleep(2000);
            await window.plugins.theta.notificationAudioShutter().catch((e) => { console.error(e) });
            await sleep(2000);
            await window.plugins.theta.notificationAudioOpen().catch((e) => { console.error(e) });
            await sleep(2000);
            await window.plugins.theta.notificationAudioClose().catch((e) => { console.error(e) });
            await sleep(2000);
            await window.plugins.theta.notificationAudioMovStart().catch((e) => { console.error(e) });
            await sleep(2000);
            await window.plugins.theta.notificationAudioMovStop().catch((e) => { console.error(e) });
            await sleep(2000);
            await window.plugins.theta.notificationAudioSelf().catch((e) => { console.error(e) });
            await sleep(2000);
            await window.plugins.theta.notificationAudioWarning().catch((e) => { console.error(e) });
            await sleep(2000);
            await window.plugins.theta.notificationLed3Show("RED").catch((e) => { console.error(e) });
            await sleep(2000);
            await window.plugins.theta.notificationLedShow("LED3").catch((e) => { console.error(e) });
            await sleep(2000);
            await window.plugins.theta.notificationLedBlink("LED3", "RED", 2000).catch((e) => { console.error(e) });
            await sleep(2000);
            await window.plugins.theta.notificationLedHide("LED3").catch((e) => { console.error(e) });
            await sleep(2000);
            await window.plugins.theta.notificationDatabaseUpdate(["/sdcard/DCIM"]).catch((e) => { console.error(e) });
            await sleep(2000);
            // await window.plugins.theta.notificationSuccess().catch((e) => { console.error(e) });
            // await window.plugins.theta.notificationError("message").catch((e) => { console.error(e) });
            console.log("END:");
        }catch (e) {
            console.error("Received Event:", e);
        }
    }
};

app.initialize();