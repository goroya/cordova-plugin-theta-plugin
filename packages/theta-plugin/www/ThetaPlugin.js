function ThetaPlugin() {
}

ThetaPlugin.prototype.onKeyDownCallback = function (successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, "ThetaPlugin", "onKeyDownCallback", [{}]);
};

ThetaPlugin.prototype.onKeyUpCallback = function (successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, "ThetaPlugin", "onKeyUpCallback", [{}]);
};

ThetaPlugin.prototype.onKeyLongPressCallback = function (successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, "ThetaPlugin", "onKeyLongPressCallback", [{}]);
};
ThetaPlugin.prototype.notificationCameraOpen = function () {
    return new Promise(function (resolve, reject) {
        cordova.exec(resolve, reject, "ThetaPlugin", "notificationCameraOpen", [{}]);
    });
};
ThetaPlugin.prototype.notificationCameraClose = function () {
    return new Promise(function (resolve, reject) {
        cordova.exec(resolve, reject, "ThetaPlugin", "notificationCameraClose", [{}]);
    });
};
ThetaPlugin.prototype.notificationAudioShutter = function () {
    return new Promise(function (resolve, reject) {
        cordova.exec(
            resolve, reject, "ThetaPlugin", "notificationAudioShutter", [{}]
        );
    });
};
ThetaPlugin.prototype.notificationAudioOpen = function () {
    return new Promise(function (resolve, reject) {
        cordova.exec(
            resolve, reject, "ThetaPlugin", "notificationAudioOpen", [{}]
        );
    });
};
ThetaPlugin.prototype.notificationAudioClose = function () {
    return new Promise(function (resolve, reject) {
        cordova.exec(resolve, reject, "ThetaPlugin", "notificationAudioClose", [{}]);
    });
};

ThetaPlugin.prototype.notificationAudioMovStart = function () {
    return new Promise(function (resolve, reject) {
        cordova.exec(resolve, reject, "ThetaPlugin", "notificationAudioMovStart", [{}]);
    });
};
ThetaPlugin.prototype.notificationAudioMovStop = function () {
    return new Promise(function (resolve, reject) {
        cordova.exec(resolve, reject, "ThetaPlugin", "notificationAudioMovStop", [{}]);
    });
};
ThetaPlugin.prototype.notificationAudioSelf = function () {
    return new Promise(function (resolve, reject) {
        cordova.exec(resolve, reject, "ThetaPlugin", "notificationAudioSelf", [{}]);
    });
};
ThetaPlugin.prototype.notificationAudioWarning = function () {
    return new Promise(function (resolve, reject) {
        cordova.exec(resolve, reject, "ThetaPlugin", "notificationAudioWarning", [{}]);
    });
};
ThetaPlugin.prototype.notificationLed3Show = function (ledColor) {
    return new Promise(function (resolve, reject) {
        cordova.exec(resolve, reject, "ThetaPlugin", "notificationLed3Show", [
            {ledColor: ledColor}
        ]);
    });
};
ThetaPlugin.prototype.notificationLedShow = function (ledName) {
    return new Promise(function (resolve, reject) {
        cordova.exec(resolve, reject, "ThetaPlugin", "notificationLedShow", [
            {ledName: ledName}
        ]);
    });
};
ThetaPlugin.prototype.notificationLedBlink = function (ledName, ledColor, period) {
    return new Promise(function (resolve, reject) {
        cordova.exec(resolve, reject, "ThetaPlugin", "notificationLedBlink", [
            {ledName: ledName, ledColor: ledColor, period: period}
        ]);
    });
};

ThetaPlugin.prototype.notificationLedHide = function (ledName) {
    return new Promise(function (resolve, reject) {
        cordova.exec(resolve, reject, "ThetaPlugin", "notificationLedHide", [
            {ledName: ledName}
        ]);
    });
};

ThetaPlugin.prototype.notificationWlanOff = function () {
    return new Promise(function (resolve, reject) {
        cordova.exec(resolve, reject, "ThetaPlugin", "notificationWlanOff", [{}]);
    });
};

ThetaPlugin.prototype.notificationWlanAp = function () {
    return new Promise(function (resolve, reject) {
        cordova.exec(resolve, reject, "ThetaPlugin", "notificationWlanAp", [{}]);
    });
};

ThetaPlugin.prototype.notificationWlanCl = function () {
    return new Promise(function (resolve, reject) {
        cordova.exec(resolve, reject, "ThetaPlugin", "notificationWlanCl", [{}]);
    });
};

ThetaPlugin.prototype.notificationDatabaseUpdate = function (targets) {
    return new Promise(function (resolve, reject) {
        cordova.exec(resolve, reject, "ThetaPlugin", "notificationDatabaseUpdate", [{
            targets: targets}
        ]);
    });
};

ThetaPlugin.prototype.notificationSuccess = function () {
    return new Promise(function (resolve, reject) {
        cordova.exec(resolve, reject, "ThetaPlugin", "notificationSuccess", [{}]);
    });
};

ThetaPlugin.prototype.notificationError = function (message) {
    return new Promise(function (resolve, reject) {
        cordova.exec(resolve, reject, "ThetaPlugin", "notificationError", [
            {message: message}
        ]);
    });
};

ThetaPlugin.prototype.notificationErrorOccured = function () {
    return new Promise(function (resolve, reject) {
        cordova.exec(resolve, reject, "ThetaPlugin", "notificationErrorOccured", [{}]);
    });
};

ThetaPlugin.prototype.setAudioBformat = function () {
    return new Promise(function (resolve, reject) {
        cordova.exec(resolve, reject, "ThetaPlugin", "setAudioBformat", [{}]);
    });
};

ThetaPlugin.prototype.setAudioMonaural = function () {
    return new Promise(function (resolve, reject) {
        cordova.exec(resolve, reject, "ThetaPlugin", "setAudioMonaural", [{}]);
    });
};

ThetaPlugin.prototype.setMicSelectAuto = function () {
    return new Promise(function (resolve, reject) {
        cordova.exec(resolve, reject, "ThetaPlugin", "setMicSelectAuto", [{}]);
    });
};

ThetaPlugin.prototype.setMicSelectInternal = function () {
    return new Promise(function (resolve, reject) {
        cordova.exec(resolve, reject, "ThetaPlugin", "setMicSelectInternal", [{}]);
    });
};

ThetaPlugin.prototype.setMicSelectExternal = function () {
    return new Promise(function (resolve, reject) {
        cordova.exec(resolve, reject, "ThetaPlugin", "setMicSelectExternal", [{}]);
    });
};

ThetaPlugin.prototype.setMicSurroundVolumeLevelNormal = function () {
    return new Promise(function (resolve, reject) {
        cordova.exec(resolve, reject, "ThetaPlugin", "setMicSurroundVolumeLevelNormal", [{}]);
    });
};

ThetaPlugin.prototype.setMicSurroundVolumeLevelLarge = function () {
    return new Promise(function (resolve, reject) {
        cordova.exec(resolve, reject, "ThetaPlugin", "setMicSurroundVolumeLevelLarge", [{}]);
    });
};

ThetaPlugin.prototype.setShootingMode = function (param) {
    return new Promise(function (resolve, reject) {
        cordova.exec(resolve, reject, "ThetaPlugin", "setShootingMode", [{param: param}]);
    });
};

ThetaPlugin.prototype.setStitching = function (param) {
    return new Promise(function (resolve, reject) {
        cordova.exec(resolve, reject, "ThetaPlugin", "setStitching", [{param: param}]);
    });
};

ThetaPlugin.prototype.setExposureMode = function (param) {
    return new Promise(function (resolve, reject) {
        cordova.exec(resolve, reject, "ThetaPlugin", "setExposureMode", [{param: param}]);
    });
};

ThetaPlugin.install = function () {
    if (!window.plugins) {
        window.plugins = {};
    }
    window.plugins.theta = new ThetaPlugin();
    return window.plugins.theta;
};

cordova.addConstructor(ThetaPlugin.install);
