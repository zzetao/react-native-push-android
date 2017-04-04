# react-native-push-android

## Getting started

`$ npm install react-native-push-android --save`

### Mostly automatic installation

`$ react-native link react-native-push-android`

### Manual installation

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import br.com.helder.push.RNPushAndroidPackage;` to the imports at the top of the file
  - Add `new RNPushAndroidPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-push-android'
  	project(':react-native-push-android').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-push-android/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-push-android')
  	```

## Configuration

* Step 1 - Edit $PROJECT_NAME/android/build.gradle
```bash
 dependencies {
    ...
    classpath 'com.google.gms:google-services:3.0.0'
    ...
```

* Step 2 - Edit $PROJECT_NAME/android/app/build.gradle
```bash
    ...
    apply plugin: 'com.google.gms.google-services'
    ...
```

* Step 3 - Edit android/app/src/main/AndroidManifest.xml
```bash
    ...
    <application
      android:name=".MainApplication"
      android:allowBackup="true"
      android:label="@string/app_name"
      android:icon="@mipmap/ic_launcher"
      android:theme="@style/AppTheme">
        <receiver android:name="br.com.helderfarias.pushandroid.LocalMessagingReceiver" />

        <receiver android:enabled="true" android:exported="true"
            android:name="br.com.helderfarias.pushandroid.SystemBootEventReceiver">
            <intent-filter>
                <action android:name="android.intent.action.BOOT_COMPLETED"/>
                <action android:name="android.intent.action.QUICKBOOT_POWERON"/>
                <action android:name="com.htc.intent.action.QUICKBOOT_POWERON"/>
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </receiver>

        <service android:name="br.com.helderfarias.pushandroid.MessagingService">
            <intent-filter>
                <action android:name="com.google.firebase.MESSAGING_EVENT"/>
            </intent-filter>
        </service>

        <service android:name="br.com.helderfarias.pushandroid.InstanceIdService" 
            android:exported="false">
            <intent-filter>
                <action android:name="com.google.firebase.INSTANCE_ID_EVENT"/>
            </intent-filter>
        </service>
            
        <activity
            android:name=".MainActivity"
            android:label="@string/app_name"
            android:configChanges="keyboard|keyboardHidden|orientation|screenSize"
            android:windowSoftInputMode="adjustResize">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
            <intent-filter>
                <action android:name="fcm.ACTION.HELLO" />
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>        
        </activity>

        <activity android:name="com.facebook.react.devsupport.DevSettingsActivity" />
    </application>    
    ...
```

## Usage
```javascript
import RNPushAndroid from 'react-native-push-android';

// TODO: What to do with the module?
RNPushAndroid;
```
  
