<template>
  <div id="app">
    <b-navbar toggleable="md" type="dark" variant="info">
      <b-navbar-toggle target="nav_collapse"></b-navbar-toggle>
      <b-navbar-brand href="#">NavBar</b-navbar-brand>
    </b-navbar>
    <div class="container-fluid">
      <h4>wlan Mode</h4>
      <div class="row">
        <div class="col-4">
          <b-button size="sm" block="true" @click="wlanOff()">
            wlanOff
          </b-button>
        </div>
        <div class="col-4">
          <b-button size="sm" block="true" @click="wlanAp()">
            wlanAp
          </b-button>
        </div>
        <div class="col-4">
          <b-button size="sm" block="true" @click="wlanCl()">
            wlanCl
          </b-button>
        </div>
      </div>

      <h4>cameraClose/Open</h4>
      <div class="row">
        <div class="col-6">
          <b-button size="sm" block="true" @click="cameraClose()">
            CameraOpen
          </b-button>
        </div>
        <div class="col-6">
          <b-button size="sm" block="true" @click="cameraOpen()">
            CameraClose
          </b-button>
        </div>
      </div>

      <h4>Sound</h4>
      <div class="row">
        <div class="col-2">
          <b-button size="sm" block="true" @click="notificationAudioShutter()">
            AudioShutter
          </b-button>
        </div>
        <div class="col-2">
          <b-button size="sm" block="true" @click="notificationAudioOpen()">
            AudioOpen
          </b-button>
        </div>
        <div class="col-2">
          <b-button size="sm" block="true" @click="notificationAudioClose()">
            AudioClose
          </b-button>
        </div>
        <div class="col-2">
          <b-button size="sm" block="true" @click="notificationAudioMovStart()">
            AudioMovStart
          </b-button>
        </div>
        <div class="col-2">
          <b-button size="sm" block="true" @click="notificationAudioMovStop()">
            AudioMovStop
          </b-button>
        </div>
        <div class="col-2">
          <b-button size="sm" block="true" @click="notificationAudioSelf()">
            AudioSelf
          </b-button>
        </div>
        <div class="col-2">
          <b-button size="sm" block="true" @click="notificationAudioWarning()">
            AudioWarning
          </b-button>
        </div>
      </div>

      <h4>notificationLed3Show</h4>
      <b-button-group>
        <b-button variant="danger" @click="led3show('RED')">RED</b-button>
        <b-button variant="success" @click="led3show('GREEN')">GREEN</b-button>
        <b-button variant="primary" @click="led3show('BLUE')">BLUE</b-button>
        <b-button variant="default" @click="led3show('CYAN')">CYAN</b-button>
        <b-button variant="default" @click="led3show('MAGENTA')">MAGENTA</b-button>
        <b-button variant="warning" @click="led3show('YELLOW')">YELLOW</b-button>
        <b-button variant="link" @click="led3show('WHITE')">WHITE</b-button>
      </b-button-group>

      <h4>notificationLedShow</h4>
      <div class="row">
        <div class="col-2">
          <b-button size="sm" block="true" @click="ledon('LED3')">
            LED3
          </b-button>
        </div>
        <div class="col-2">
          <b-button size="sm" block="true" @click="ledon('LED4')">
            LED4
          </b-button>
        </div>
        <div class="col-2">
          <b-button size="sm" block="true" @click="ledon('LED5')">
            LED5
          </b-button>
        </div>
        <div class="col-2">
          <b-button size="sm" block="true" @click="ledon('LED6')">
            LED6
          </b-button>
        </div>
        <div class="col-2">
          <b-button size="sm" block="true" @click="ledon('LED7')">
            LED7
          </b-button>
        </div>
        <div class="col-2">
          <b-button size="sm" block="true" @click="ledon('LED8')">
            LED8
          </b-button>
        </div>
      </div>

      <h4>notificationLedBlink</h4>
      <div class="row">
        <div class="col-2">
          <b-button size="sm" block="true" @click="ledblink('LED3')">
            LED3
          </b-button>
        </div>
        <div class="col-2">
          <b-button size="sm" block="true" @click="ledblink('LED4')">
            LED4
          </b-button>
        </div>
        <div class="col-2">
          <b-button size="sm" block="true" @click="ledblink('LED5')">
            LED5
          </b-button>
        </div>
        <div class="col-2">
          <b-button size="sm" block="true" @click="ledblink('LED6')">
            LED6
          </b-button>
        </div>
        <div class="col-2">
          <b-button size="sm" block="true" @click="ledblink('LED7')">
            LED7
          </b-button>
        </div>
        <div class="col-2">
          <b-button size="sm" block="true" @click="ledblink('LED8')">
            LED8
          </b-button>
        </div>
      </div>

      <h4>notificationLedHide</h4>
      <div class="row">
        <div class="col-2">
          <b-button size="sm" block="true" @click="ledoff('LED3')">
            LED3
          </b-button>
        </div>
        <div class="col-2">
          <b-button size="sm" block="true" @click="ledoff('LED4')">
            LED4
          </b-button>
        </div>
        <div class="col-2">
          <b-button size="sm" block="true" @click="ledoff('LED5')">
            LED5
          </b-button>
        </div>
        <div class="col-2">
          <b-button size="sm" block="true" @click="ledoff('LED6')">
            LED6
          </b-button>
        </div>
        <div class="col-2">
          <b-button size="sm" block="true" @click="ledoff('LED7')">
            LED7
          </b-button>
        </div>
        <div class="col-2">
          <b-button size="sm" block="true" @click="ledoff('LED8')">
            LED8
          </b-button>
        </div>
      </div>

      <h4>Other</h4>
      <div class="row">
        <div class="col-4">
          <b-button size="sm" block="true" @click="update()">
            DB update
          </b-button>
        </div>
        <div class="col-4">
          <b-button size="sm" block="true" @click="success()">
            Plugin Success
          </b-button>
        </div>
        <div class="col-4">
          <b-button size="sm" block="true" @click="error()">
            Plugin Error
          </b-button>
        </div>
      </div>

    </div>
  </div>
</template>

<script>

export default {
  name: 'App',
  components: {
  },
  data: function () {
    return {
        keyDown: false,
        keyUp: false,
        keyLongPress: false
    }
  },
  methods: {
    wlanOff: async function () {
      console.log("wlanOff: ");
      await window.plugins.theta.notificationWlanOff().catch((e) => { console.error(e) });
      console.log("wlanOff end: ");
    },
    wlanAp: async function () {
      console.log("wlanAp: ");
      await window.plugins.theta.notificationWlanAp().catch((e) => { console.error(e) });
      console.log("wlanAp end: ");
    },
    wlanCl: async function () {
      console.log("wlanCl: ");
      await window.plugins.theta.notificationWlanCl().catch((e) => { console.error(e) });
      console.log("wlanCl end: ");
    },
    cameraClose: async function (led) {
      console.log("cameraClose: ");
      await window.plugins.theta.notificationCameraClose().catch((e) => { console.error(e) });
      console.log("cameraClose end: ");
    },
    cameraOpen: async function (led) {
      console.log("cameraOpen: ");
      await window.plugins.theta.notificationCameraOpen().catch((e) => { console.error(e) });
      console.log("cameraOpen end: ");
    },
    notificationAudioShutter: async function (led) {
      await window.plugins.theta.notificationAudioShutter().catch((e) => { console.error(e) });
    },
    notificationAudioOpen: async function (led) {
      await window.plugins.theta.notificationAudioOpen().catch((e) => { console.error(e) });
    },
    notificationAudioClose: async function (led) {
      await window.plugins.theta.notificationAudioClose().catch((e) => { console.error(e) });
    },
    notificationAudioMovStart: async function (led) {
      await window.plugins.theta.notificationAudioMovStart().catch((e) => { console.error(e) });
    },
    notificationAudioMovStop: async function (led) {
      await window.plugins.theta.notificationAudioMovStop().catch((e) => { console.error(e) });
    },
    notificationAudioSelf: async function (led) {
      await window.plugins.theta.notificationAudioSelf().catch((e) => { console.error(e) });
    },
    notificationAudioWarning: async function (led) {
      await window.plugins.theta.notificationAudioWarning().catch((e) => { console.error(e) });
    },
    led3show: async function (color) {
      console.log("ledon: " + color);
      await window.plugins.theta.notificationLed3Show(color).catch((e) => { console.error(e) });
      console.log("ledon end: " + color);
    },
    ledon: async function (led) {
      console.log("ledon: " + led);
      await window.plugins.theta.notificationLedShow(led).catch((e) => { console.error(e) });
      console.log("ledon end: " + led);
    },
    ledblink: async function (led) {
      console.log("ledblink: " + led);
      await window.plugins.theta.notificationLedBlink(led, "RED", 2000).catch((e) => { console.error(e) });
      console.log("ledblink: " + led);
    },
    ledoff: async function (led) {
      console.log("ledoff: " + led);
      await window.plugins.theta.notificationLedHide(led).catch((e) => { console.error(e) });
      console.log("ledoff: " + led);
    },
    update: async function () {
      console.log("ledoff: ");
      await window.plugins.theta.notificationDatabaseUpdate(["/sdcard/DCIM"]).catch((e) => { console.error(e) });
      console.log("ledoff: " + led);
    },
    success: async function () {
      console.log("ledoff: ");
      await window.plugins.theta.notificationSuccess().catch((e) => { console.error(e) });
      console.log("ledoff: ");
    },
    error: async function () {
      console.log("ledoff: ");
      await window.plugins.theta.notificationError("message").catch((e) => { console.error(e) });
      console.log("ledoff: ");
    },
  },
  mounted: async function() {
    try {
      window.plugins.theta.onKeyDownCallback(
        (e) => {
          console.log("onKeyDownCallback", e);
        },
        (e) => {
          console.error("onKeyDownCallback", e);
        }
      );
      window.plugins.theta.onKeyUpCallback(
        (e) => {
          console.log("onKeyUpCallback", e);
        },
        (e) => {
          console.error("onKeyUpCallback", e);
        }
      );
      window.plugins.theta.onKeyLongPressCallback(
        (e) => {
          console.log("onKeyLongPressCallback", e);
        },
        (e) => {
          console.error("onKeyLongPressCallback", e);
        }
      );
      console.log("App mounted end");
    }catch(e){
      console.log("error", e);
    }
  }
}
</script>

<style scoped>
</style>