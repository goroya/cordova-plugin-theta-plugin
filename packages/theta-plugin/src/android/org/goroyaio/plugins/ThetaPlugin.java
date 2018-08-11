package org.goroyaio.plugins;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Camera;
import android.media.AudioManager;
import android.util.Log;
import android.support.annotation.NonNull;
import android.view.KeyEvent;

import com.theta360.pluginlibrary.UncaughtException;
import com.theta360.pluginlibrary.callback.KeyCallback;
import com.theta360.pluginlibrary.receiver.KeyReceiver;
import com.theta360.pluginlibrary.values.ExitStatus;
import com.theta360.pluginlibrary.values.LedColor;
import com.theta360.pluginlibrary.values.LedTarget;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.theta360.pluginlibrary.receiver.KeyReceiver.KEYCODE_MEDIA_RECORD;

public class ThetaPlugin extends CordovaPlugin {
    private static String TAG = "CordovaThetaPlugin";
    private static final String ACTION_MAIN_CAMERA_CLOSE = "com.theta360.plugin.ACTION_MAIN_CAMERA_CLOSE";
    private static final String ACTION_MAIN_CAMERA_OPEN = "com.theta360.plugin.ACTION_MAIN_CAMERA_OPEN";
    private static final String ACTION_FINISH_PLUGIN = "com.theta360.plugin.ACTION_FINISH_PLUGIN";
    private static final String ACTION_ERROR_OCCURED = "com.theta360.plugin.ACTION_ERROR_OCCURED";
    private static final String PACKAGE_NAME = "packageName";
    private static final String EXIT_STATUS = "exitStatus";
    private static final String MESSAGE = "message";

    private static final String ACTION_LED_SHOW = "com.theta360.plugin.ACTION_LED_SHOW";
    private static final String ACTION_LED_BLINK = "com.theta360.plugin.ACTION_LED_BLINK";
    private static final String ACTION_LED_HIDE = "com.theta360.plugin.ACTION_LED_HIDE";
    private static final String TARGET = "target";
    private static final String COLOR = "color";
    private static final String PERIOD = "period";

    private static final String ACTION_AUDIO_SHUTTER = "com.theta360.plugin.ACTION_AUDIO_SHUTTER";
    private static final String ACTION_AUDIO_SH_OPEN = "com.theta360.plugin.ACTION_AUDIO_SH_OPEN";
    private static final String ACTION_AUDIO_SH_CLOSE = "com.theta360.plugin.ACTION_AUDIO_SH_CLOSE";
    private static final String ACTION_AUDIO_MOVSTART = "com.theta360.plugin.ACTION_AUDIO_MOVSTART";
    private static final String ACTION_AUDIO_MOVSTOP = "com.theta360.plugin.ACTION_AUDIO_MOVSTOP";
    private static final String ACTION_AUDIO_SELF = "com.theta360.plugin.ACTION_AUDIO_SELF";
    private static final String ACTION_AUDIO_WARNING = "com.theta360.plugin.ACTION_AUDIO_WARNING";

    private static final String ACTION_WLAN_OFF = "com.theta360.plugin.ACTION_WLAN_OFF";
    private static final String ACTION_WLAN_AP = "com.theta360.plugin.ACTION_WLAN_AP";
    private static final String ACTION_WLAN_CL = "com.theta360.plugin.ACTION_WLAN_CL";

    private static final String ACTION_DATABASE_UPDATE = "com.theta360.plugin.ACTION_DATABASE_UPDATE";
    private static final String TARGETS = "targets";

    private boolean isCamera = false;
    private KeyCallback mKeyCallback;
    private KeyReceiver mKeyReceiver;

    private CallbackContext keyDownCallbackContext = null;
    private CallbackContext keyUpCallbackContext = null;
    private CallbackContext keyLongPressCallbackContext = null;

    private Camera mCamera = null;
    private Camera.ErrorCallback mErrorCallback = new Camera.ErrorCallback() {
        @Override
        public void onError(int error, Camera camera) {
            String cameraError = String.format("Camera error. error=%d", error);
            Log.d(TAG, "Camera onError " + cameraError);
            throw new RuntimeException(cameraError);
        }
    };

    private AudioManager mAudioManager = null;

    @Override
    protected void pluginInitialize() {
        super.pluginInitialize();
        Log.d(TAG, "pluginInitialize");

        UncaughtException uncaughtException = new UncaughtException(this.cordova.getContext(),
                (String message) -> {
                    notificationError(message);
                    cordova.getActivity().finish();
                });
        Thread.setDefaultUncaughtExceptionHandler(uncaughtException);
        super.onStart();

        setKeyCallback(new KeyCallback() {
            @Override
            public void onKeyDown(int keyCode, KeyEvent event) {
                if(keyDownCallbackContext != null){
                    PluginResult result = new PluginResult(PluginResult.Status.OK, keyCode);
                    result.setKeepCallback(true);
                    keyDownCallbackContext.sendPluginResult(result);
                }
            }
            @Override
            public void onKeyUp(int keyCode, KeyEvent event) {
                if(keyUpCallbackContext != null){
                    PluginResult result = new PluginResult(PluginResult.Status.OK, keyCode);
                    result.setKeepCallback(true);
                    keyUpCallbackContext.sendPluginResult(result);
                }
            }
            @Override
            public void onKeyLongPress(int keyCode, KeyEvent event) {
                if(keyLongPressCallbackContext != null){
                    PluginResult result = new PluginResult(PluginResult.Status.OK, keyCode);
                    result.setKeepCallback(true);
                    keyLongPressCallbackContext.sendPluginResult(result);
                }
            }
        });

        setKeyReceiver();
    }

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        Log.d(TAG, action);
        final JSONObject options = args.getJSONObject(0);
        switch (action) {
            case "onKeyDownCallback":
                keyDownCallbackContext = callbackContext;
                PluginResult resultKeyDown = new PluginResult(PluginResult.Status.OK, "onKeyUpCallback");
                resultKeyDown.setKeepCallback(true);
                keyDownCallbackContext.sendPluginResult(resultKeyDown);
                break;
            case "onKeyUpCallback":
                keyUpCallbackContext = callbackContext;
                PluginResult resultKeyUp = new PluginResult(PluginResult.Status.OK, "onKeyUpCallback");
                resultKeyUp.setKeepCallback(true);
                keyUpCallbackContext.sendPluginResult(resultKeyUp);
                break;
            case "onKeyLongPressCallback":
                keyLongPressCallbackContext = callbackContext;
                PluginResult resultKeyLongPress = new PluginResult(PluginResult.Status.OK, "onKeyUpCallback");
                resultKeyLongPress.setKeepCallback(true);
                keyLongPressCallbackContext.sendPluginResult(resultKeyLongPress);
                break;
            case "notificationCameraOpen":
                notificationCameraOpen();
                callbackContext.success();
                break;
            case "notificationCameraClose":
                notificationCameraClose();
                callbackContext.success();
                break;
            case "notificationAudioShutter":
                Log.d(TAG, "notificationAudioShutter");
                notificationAudioShutter();
                callbackContext.success();
                break;
            case "notificationAudioOpen":
                notificationAudioOpen();
                callbackContext.success();
                break;
            case "notificationAudioClose":
                notificationAudioClose();
                callbackContext.success();
                break;
            case "notificationAudioMovStart":
                notificationAudioMovStart();
                callbackContext.success();
                break;
            case "notificationAudioMovStop":
                notificationAudioMovStop();
                callbackContext.success();
                break;
            case "notificationAudioSelf":
                notificationAudioSelf();
                callbackContext.success();
                break;
            case "notificationAudioWarning":
                notificationAudioWarning();
                callbackContext.success();
                break;
            case "notificationLed3Show":
                String notificationLedColor = options.getString("ledColor");
                LedColor notificationLedColorEnum = string2LedColor(notificationLedColor);
                if (notificationLedColorEnum == null){
                    callbackContext.error("ledColor is invalid");
                    return false;
                }
                notificationLed3Show(notificationLedColorEnum);
                callbackContext.success();
                break;
            case "notificationLedShow":
                String showLedName = options.getString("ledName");
                LedTarget showLedTarget = string2LedTarget(showLedName);
                if (showLedTarget == null){
                    callbackContext.error("ledName is invalid");
                    return false;
                }
                notificationLedShow(showLedTarget);
                callbackContext.success();
                break;
            case "notificationLedBlink":
                String blinkLedName = options.getString("ledName");
                LedTarget blinkLedTarget = string2LedTarget(blinkLedName);
                if (blinkLedTarget == null){
                    callbackContext.error("ledName is invalid");
                    return false;
                }
                String blinkLedColor = options.getString("ledColor");
                LedColor blinkLedColorEnum = string2LedColor(blinkLedColor);
                if (blinkLedColorEnum == null){
                    callbackContext.error("ledColor is invalid");
                    return false;
                }
                int period = options.getInt("period");
                notificationLedBlink(blinkLedTarget, blinkLedColorEnum, period);
                callbackContext.success();
                break;
            case "notificationLedHide":
                String hideLedName = options.getString("ledName");
                LedTarget hideLedTarget = string2LedTarget(hideLedName);
                if (hideLedTarget == null){
                    callbackContext.error("ledName is invalid");
                    return false;
                }
                notificationLedHide(hideLedTarget);
                callbackContext.success();
                break;
            case "notificationWlanOff":
                notificationWlanOff();
                callbackContext.success();
                break;
            case "notificationWlanAp":
                notificationWlanAp();
                callbackContext.success();
                break;
            case "notificationWlanCl":
                notificationWlanCl();
                callbackContext.success();
                break;
            case "notificationDatabaseUpdate":
                JSONArray jsonTargets = options.getJSONArray("targets");
                List<String> list = new ArrayList<String>();
                for(int i = 0; i < jsonTargets.length(); i++){
                    list.add(jsonTargets.getString(i));
                }
                notificationDatabaseUpdate(list.toArray(new String[list.size()]));
                callbackContext.success();
                break;
            case "notificationSuccess":
                notificationSuccess();
                callbackContext.success();
                break;
            case "notificationError":
                final String msg = options.getString("message");
                notificationError(msg);
                callbackContext.success();
                break;
            case "notificationErrorOccured":
                notificationErrorOccured();
                callbackContext.success();
                break;
            case "setAudioBformat":
                setAudioBformat();
                callbackContext.success();
                break;
            case "setAudioMonaural":
                setAudioMonaural();
                callbackContext.success();
                break;
            case "setMicSelectAuto":
                setMicSelectAuto();
                callbackContext.success();
                break;
            case "setMicSelectInternal":
                setMicSelectInternal();
                callbackContext.success();
                break;
            case "setMicSelectExternal":
                setMicSelectExternal();
                callbackContext.success();
                break;
            case "setMicSurroundVolumeLevelNormal":
                setMicSurroundVolumeLevelNormal();
                callbackContext.success();
                break;
            case "setMicSurroundVolumeLevelLarge":
                setMicSurroundVolumeLevelLarge();
                callbackContext.success();
                break;
            case "setShootingMode":
                final String paramShootingMode = options.getString("param");
                setShootingMode(paramShootingMode);
                callbackContext.success();
                break;
            case "setStitching":
                final String paramStitching = options.getString("param");
                setStitching(paramStitching);
                callbackContext.success();
                break;
            case "setExposureMode":
                final String paramExposureMode = options.getString("param");
                setExposureMode(paramExposureMode);
                callbackContext.success();
                break;
            case "setManualExposureTime":
                final int paramFrontTime = options.getInt("paramFront");
                final int paramRearTime = options.getInt("paramRear");
                setManualExposureTime(paramFrontTime, paramRearTime);
                callbackContext.success();
                break;
            case "setManualExposureISO":
                final int paramFrontISO = options.getInt("paramFront");
                final int paramRearISO = options.getInt("paramRear");
                setManualExposureISO(paramFrontISO, paramRearISO);
                callbackContext.success();
                break;
            case "setWBMode":
                final String paramWBMode = options.getString("param");
                setCaptureBreak(paramWBMode);
                callbackContext.success();
                break;
            case "setWBTemperature":
                final int paramWBTemperature = options.getInt("param");
                setWBTemperature(paramWBTemperature);
                callbackContext.success();
                break;
            case "setExposureCompensation":
                final int paramExposureCompensation = options.getInt("param");
                setExposureCompensation(paramExposureCompensation);
                callbackContext.success();
                break;
            case "setCaptureBreak":
                final String paramCaptureBreak = options.getString("param");
                setCaptureBreak(paramCaptureBreak);
                callbackContext.success();
                break;
        }
        return true;
    }


    @Override
    public void onStart() {
        Log.d(TAG, "onStart");
    }

    private void setKeyReceiver(){
        mKeyReceiver = new KeyReceiver(onKeyReceiver);
        IntentFilter keyFilter = new IntentFilter();
        keyFilter.addAction(KeyReceiver.ACTION_KEY_DOWN);
        keyFilter.addAction(KeyReceiver.ACTION_KEY_UP);
        this.cordova.getActivity().registerReceiver(mKeyReceiver, keyFilter);
    };
    @Override
    public void onResume(boolean multitasking) {
        Log.d(TAG, "onResume");
        super.onResume(multitasking);

        setKeyReceiver();
    }

    @Override
    public void onPause(boolean multitasking) {
        this.cordova.getActivity().unregisterReceiver(mKeyReceiver);
        Log.d(TAG, "onPause");
        super.onPause(multitasking);
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
        // set B-format
        setAudioBformat();
    }

    @Override
    public void onNewIntent(Intent intent) {
        Log.d(TAG, "onNewIntent");
        super.onNewIntent(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
        mCamera.release();
        mCamera = null;
    }

    private AudioManager getAudioManagerInstance(){
        if(mAudioManager == null){
            mAudioManager = (AudioManager) this.cordova.getContext().getSystemService(Context.AUDIO_SERVICE);
        }
        return mAudioManager;
    }

    private void setAudioBformat(){
        AudioManager mAudioManager = getAudioManagerInstance();
        mAudioManager.setParameters("RicUseBFormat=true");
    }
    private void setAudioMonaural(){
        AudioManager mAudioManager = getAudioManagerInstance();
        mAudioManager.setParameters("RicUseBFormat=false");
    }
    private void setMicSelectAuto(){
        AudioManager mAudioManager = getAudioManagerInstance();
        mAudioManager.setParameters("RicMicSelect=RicMicSelectAuto");
    }
    private void setMicSelectInternal(){
        AudioManager mAudioManager = getAudioManagerInstance();
        mAudioManager.setParameters("RicMicSelect=RicMicSelectInternal");
    }
    private void setMicSelectExternal(){
        AudioManager mAudioManager = getAudioManagerInstance();
        mAudioManager.setParameters("RicMicSelect=RicMicSelectExternal");
    }
    private void setMicSurroundVolumeLevelNormal(){
        AudioManager mAudioManager = getAudioManagerInstance();
        mAudioManager.setParameters("RicMicSurrondVolumeLevel=RicMicSurroundVolumeLevelNormal");
    }
    private void setMicSurroundVolumeLevelLarge(){
        AudioManager mAudioManager = getAudioManagerInstance();
        mAudioManager.setParameters("RicMicSurrondVolumeLevel=RicMicSurroundVolumeLevelLarge");
    }
    private Camera getCameraInstance(){
        if (mCamera == null) {
            int numberOfCameras = Camera.getNumberOfCameras();
            Log.d(TAG, "numberOfCameras: " + numberOfCameras);
            for (int i = 0; i < numberOfCameras; i++) {
                Camera.CameraInfo info = new Camera.CameraInfo();
                Camera.getCameraInfo(i, info);
                int mCameraId = -1;
                if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                    mCameraId = i;
                }
                try {
                    mCamera = Camera.open(mCameraId);
                } catch (Exception e) {
                    throw e;
                }
                Camera.Parameters mParameters;
                mParameters = mCamera.getParameters();
                mParameters.set("recording-hint", "false");
                mCamera.setParameters(mParameters);
                mCamera.setDisplayOrientation(0);
            }
        }
        return mCamera;
    }
    // Shooting Mode
    private void setShootingMode(String param){
        Camera camera = getCameraInstance();
        Camera.Parameters mParameters = mCamera.getParameters();
        mParameters.set("RIC_SHOOTING_MODE", param);
        mCamera.setParameters(mParameters);
    }
    // Stitching
    private void setStitching(String param){
        Camera camera = getCameraInstance();
        Camera.Parameters mParameters = mCamera.getParameters();
        mParameters.set("RIC_PROC_STITCHING", param);
        mCamera.setParameters(mParameters);
    }
    // Exposure Program
    private void setExposureMode(String param){
        Camera camera = getCameraInstance();
        Camera.Parameters mParameters = mCamera.getParameters();
        mParameters.set("RIC_EXPOSURE_MODE", param);
        mCamera.setParameters(mParameters);
    }
    // Shutter Speed
    private void setManualExposureTime(int param_front, int param_rear){
        Camera camera = getCameraInstance();
        Camera.Parameters mParameters = mCamera.getParameters();
        mParameters.set("RIC_MANUAL-EXPOSURE-TIME_FRONT", param_front);
        mParameters.set("RIC_MANUAL_EXPOSURE_TIME_REAR", param_rear);
        mCamera.setParameters(mParameters);
    }
    // ISO Sensitivity
    private void setManualExposureISO(int param_front, int param_rear){
        Camera camera = getCameraInstance();
        Camera.Parameters mParameters = mCamera.getParameters();
        mParameters.set("RIC_MANUAL_EXPOSURE_ISO_FRONT", param_front);
        mParameters.set("RIC_MANUAL_EXPOSURE_ISO_REAR", param_rear);
        mCamera.setParameters(mParameters);
    }
    // White Balance
    private void setWBMode(String param){
        Camera camera = getCameraInstance();
        Camera.Parameters mParameters = mCamera.getParameters();
        mParameters.set("RIC_WB_MODE", param);
        mCamera.setParameters(mParameters);
    }
    // Color Temperature
    private void setWBTemperature(int param){
        Camera camera = getCameraInstance();
        Camera.Parameters mParameters = mCamera.getParameters();
        mParameters.set("RIC_WB-TEMPERATURE", param);
        mCamera.setParameters(mParameters);
    }
    // Exposure Compensation
    private void setExposureCompensation(int param){
        Camera camera = getCameraInstance();
        Camera.Parameters mParameters = mCamera.getParameters();
        mParameters.set("exposure-compensation-step", param);
        mCamera.setParameters(mParameters);
    }
    // Canceling Long Exposure
    private void setCaptureBreak(String param){
        Camera camera = getCameraInstance();
        Camera.Parameters mParameters = mCamera.getParameters();
        mParameters.set("RIC_CAPTURE_BREAK", param);
        mCamera.setParameters(mParameters);
    }
    private void sendBroadcast(Intent intent){
        this.cordova.getActivity().sendBroadcast(intent);
    }
    private LedColor string2LedColor(String ledColorStr){
        LedColor ledColorEnum;
        switch (ledColorStr){
            case "RED":
                ledColorEnum = LedColor.RED;
                break;
            case "GREEN":
                ledColorEnum = LedColor.GREEN;
                break;
            case "BLUE":
                ledColorEnum = LedColor.BLUE;
                break;
            case "CYAN":
                ledColorEnum = LedColor.CYAN;
                break;
            case "MAGENTA":
                ledColorEnum = LedColor.MAGENTA;
                break;
            case "YELLOW":
                ledColorEnum = LedColor.YELLOW;
                break;
            case "WHITE":
                ledColorEnum = LedColor.WHITE;
                break;
            default:
                return null;
        }
        return ledColorEnum;
    }
    private LedTarget string2LedTarget(String ledStr){
        LedTarget ledTarget;
        switch (ledStr){
            case "LED3":
                ledTarget = LedTarget.LED3;
                break;
            case "LED4":
                ledTarget = LedTarget.LED4;
                break;
            case "LED5":
                ledTarget = LedTarget.LED5;
                break;
            case "LED6":
                ledTarget = LedTarget.LED6;
                break;
            case "LED7":
                ledTarget = LedTarget.LED7;
                break;
            case "LED8":
                ledTarget = LedTarget.LED8;
                break;
            default:
                return null;
        }
        return ledTarget;
    }

    private KeyReceiver.Callback onKeyReceiver = new KeyReceiver.Callback() {
        @Override
        public void onKeyDownCallback(int keyCode, KeyEvent event) {
            if (event.getKeyCode() == KEYCODE_MEDIA_RECORD
                    && event.isLongPress()) {
                if (mKeyCallback != null) {
                    mKeyCallback.onKeyLongPress(keyCode, event);
                }
                if (isCamera) {
                    notificationCameraOpen();
                }
                notificationSuccess();
            } else {
                if (mKeyCallback != null) {
                    if (event.getRepeatCount() == 0) {
                        mKeyCallback.onKeyDown(keyCode, event);
                    } else if (event.isLongPress()) {
                        mKeyCallback.onKeyLongPress(keyCode, event);
                    }
                }
            }
        }

        @Override
        public void onKeyUpCallback(int keyCode, KeyEvent event) {
            if (mKeyCallback != null) {
                mKeyCallback.onKeyUp(keyCode, event);
            }
        }
    };

    void setKeyCallback(KeyCallback keyCallback) {
        mKeyCallback = keyCallback;
    }

    void notificationCameraOpen() {
        isCamera = false;
        sendBroadcast(new Intent(ACTION_MAIN_CAMERA_OPEN));
    }

    void notificationCameraClose() {
        isCamera = true;
        sendBroadcast(new Intent(ACTION_MAIN_CAMERA_CLOSE));
    }

    /**
     * Sound of normal capture
     */
    void notificationAudioShutter() {
        sendBroadcast(new Intent(ACTION_AUDIO_SHUTTER));
    }

    /**
     * Sound of starting long exposure capture
     */
    void notificationAudioOpen() {
        sendBroadcast(new Intent(ACTION_AUDIO_SH_OPEN));
    }

    /**
     * Sound of ending long exposure capture
     */
    void notificationAudioClose() {
        sendBroadcast(new Intent(ACTION_AUDIO_SH_CLOSE));
    }

    /**
     * Sound of starting movie recording
     */
    void notificationAudioMovStart() {
        sendBroadcast(new Intent(ACTION_AUDIO_MOVSTART));
    }

    /**
     * Sound of stopping movie recording
     */
    void notificationAudioMovStop() {
        sendBroadcast(new Intent(ACTION_AUDIO_MOVSTOP));
    }

    /**
     * Sound of working self-timer
     */
    void notificationAudioSelf() {
        sendBroadcast(new Intent(ACTION_AUDIO_SELF));
    }

    /**
     * Sound of warning
     */
    void notificationAudioWarning() {
        sendBroadcast(new Intent(ACTION_AUDIO_WARNING));
    }

    /**
     * Turn on LED3 with color
     *
     * @param ledColor target LED
     */
    void notificationLed3Show(@NonNull LedColor ledColor) {
        Intent intent = new Intent(ACTION_LED_SHOW);
        intent.putExtra(TARGET, LedTarget.LED3.toString());
        intent.putExtra(COLOR, ledColor.toString());
        sendBroadcast(intent);
    }

    /**
     * Turn on LED
     *
     * @param ledTarget target LED
     */
    void notificationLedShow(@NonNull LedTarget ledTarget) {
        if (ledTarget == LedTarget.LED3) {
            notificationLed3Show(LedColor.BLUE);
        } else {
            Intent intent = new Intent(ACTION_LED_SHOW);
            intent.putExtra(TARGET, ledTarget.toString());
            sendBroadcast(intent);
        }
    }

    /**
     * Blink LED
     *
     * @param ledTarget target LED
     * @param ledColor color
     * @param period period 250-2000 (msec)
     */
    void notificationLedBlink(@NonNull LedTarget ledTarget, LedColor ledColor, int period) {
        if (ledColor == null) {
            ledColor = LedColor.BLUE;
        }
        if (period < 250) {
            period = 250;
        }
        if (period > 2000) {
            period = 2000;
        }

        Intent intent = new Intent(ACTION_LED_BLINK);
        intent.putExtra(TARGET, ledTarget.toString());
        intent.putExtra(COLOR, ledColor.toString());
        intent.putExtra(PERIOD, period);
        sendBroadcast(intent);
    }
    /**
     * Turn off LED
     *
     * @param ledTarget target LED
     */
    void notificationLedHide(@NonNull LedTarget ledTarget) {
        Intent intent = new Intent(ACTION_LED_HIDE);
        intent.putExtra(TARGET, ledTarget.toString());
        sendBroadcast(intent);
    }

    void notificationWlanOff() {
        sendBroadcast(new Intent(ACTION_WLAN_OFF));
    }

    void notificationWlanAp() {
        sendBroadcast(new Intent(ACTION_WLAN_AP));
    }

    void notificationWlanCl() {
        sendBroadcast(new Intent(ACTION_WLAN_CL));
    }

    void notificationDatabaseUpdate(@NonNull String[] targets) {
        Intent intent = new Intent(ACTION_DATABASE_UPDATE);
        intent.putExtra(TARGETS, targets);
        sendBroadcast(intent);
    }

    /**
     * Notifying Completion of Plug-in when the plug-in ends normally
     */
    void notificationSuccess() {
        Intent intent = new Intent(ACTION_FINISH_PLUGIN);
        intent.putExtra(PACKAGE_NAME, this.cordova.getActivity().getPackageName());
        intent.putExtra(EXIT_STATUS, ExitStatus.SUCCESS.toString());
        sendBroadcast(intent);
        this.cordova.getActivity().finish();
    }

    /**
     * Notifying Completion of Plug-in when the plug-in ends with error
     *
     * @param message error message
     */
    void notificationError(String message) {
        Intent intent = new Intent(ACTION_FINISH_PLUGIN);
        intent.putExtra(PACKAGE_NAME, this.cordova.getActivity().getPackageName());
        intent.putExtra(EXIT_STATUS, ExitStatus.FAILURE.toString());
        intent.putExtra(MESSAGE, message);
        sendBroadcast(intent);
        this.cordova.getActivity().finish();
    }

    /**
     * Notifying Occurrences of Errors
     */
    void notificationErrorOccured() {
        sendBroadcast(new Intent(ACTION_ERROR_OCCURED));
    }

}
