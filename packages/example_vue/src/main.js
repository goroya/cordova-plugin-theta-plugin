import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';

import "babel-polyfill";
import 'webrtc-adapter/out/adapter';
import Vue from 'vue';
import BootstrapVue from 'bootstrap-vue';

import App from './App';


Vue.config.productionTip = false;
Vue.use(BootstrapVue);

const app = {
    // Application Constructor
    initialize: function() {
        console.log("initialize");
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

        new Vue({
            render: h => h(App)
        }).$mount('#app');
        console.log('Received Event End: ' + id);
    }
};

app.initialize();
console.log("start");
