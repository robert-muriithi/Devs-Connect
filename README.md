### Automatic SMS Verification 

Automatic SMS verification is usually done with the help of an API called the `Sms Retriever APi` . With the use of this API, user does not need to input the verification code nor does it need any extra app permissions. This feature implemented on an android application gives a user a smooth experience. It also makes the app simple to use.
Let's learn how to use this feature in our app!

### Prerequisites

To follow along with this tutorial, the reader should:
- Have an understanding of `Kotlin` programming language
- Know how to design layouts using `XML` in android studio
- Have an understanding of `Android Broadcasts`

### Goal

By the end of this tutorial, the reader will have :
- What is the SMS Verification process
- How to use an automatic SMS verification feature on your app.

### Introduction

 This API allows you to verify users' SMS without forcing them to enter verification codes. With this API, you can extract verification codes for your app.  This is done without requesting full SMS reading permissions.
 When the user device receives a message, Google play services check the app hash. It then sends the message text to your app over the SMS Retriever API. The app then reads and extracts the code in the SMS message. This code is usually sent back to the server for verification.

### SMS Verification process

For mobile number verification, you need to implement the client side first. Afterwards, the server side, to complete the verification procedure. Usually, you send the user's phone number to the server performing verification. The server then sends an OTP code to the phone number. The SMS Retriever API starts listening for an SMS containing OTP code. After receiving the code, send it back to your server to complete the verification process.

### Why use Automatic SMS Retriever API

- Google abolished all apps using `CALL_LOG` and `READ_SMS` permissions. This is because they violated of users privacy. This led to removal of apps  using these permissions from play store in January 19th, 2021.
- Users experience is smooth and almost effortless when using the app with this feature
### Step 1. Create a new Android studio project

![Create Project](/automatic-sms-verification-with-the-sms-retriever-api-in-android/create-project.png)


### Step2: Put dependencies on build.gradle file

We are going to use the following: 
- Apache Commons - We are going to use this library to help us extract the code from the SMS message.
- Google Play Services API - This library holds the SMS retrieval class
- EventBus - To listen for received SMS from the SMS Retrieval API, we'll use a BroadcastReceiver. EventBus is a publisher/subscriber pattern library. We use it to communicate between our BroadcastReceiver and Activity classes.

Let's add these to the build.gradle file for our app:
```
implementation 'com.google.android.gms:play-services-auth:19.2.0'
implementation 'org.apache.commons:commons-lang3:3.11'
implementation 'org.greenrobot:eventbus:3.2.0'
```

### Step 3: Setup the XML layout for our project

We'll create an Edit Text in this section. This Edit text will display one-time code obtained from our SMS message.
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

        <EditText
            android:id="@+id/editText"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:textAlignment="center"
            android:inputType="number"
            android:layout_marginTop="80dp"
            android:layout_marginStart="30dp"
            android:layout_marginEnd="30dp"/>
</LinearLayout>
```

### Sending the mobile number to your server

In this step, you have to get the user's phone number from the `EditText`. Send this code to your verification server which will send you the one-time code. Because I don't have a verification server, I'm not going to use that method in this article. I'm going to send the SMS from another phone. The SMS I'll send will include a four-digit code. 
This code will be extracted and displayed on the Edit text we made in activity main.xml. We will tackle this later in the article.

Once you obtain the user's mobile number from the Edit text, send it to the server perfoming the verification. 
The server will send back a code that to the number that was sent to it. This now will have verified the phone number to your application. To perform SMS verification on a server, check out [SMS Verification on Server]. (https://developers.google.com/identity/sms-retriever/verify)

### Step 4: Getting an instance of the SmsRetriverClient object

We'll first create an instance of the SmsRetrieverClient object. This is followed by invoking its initSmsRetriever instance function and adding `onSuccessListener` and `onFailureListener` to the task. We wrap all these code in a function for later use.

```kotlin
private fun initSmsListener() {
    smsClient.startSmsRetriever()
        .addOnSuccessListener {
            //You can perform your tasks here
        }.addOnFailureListener { failure ->
            failure.printStackTrace()
            Toast.makeText(this, failure.message, Toast.LENGTH_SHORT).show()
        }
}
```
The above function is invoked on the `onCreate()` method.
```
class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding
    private lateinit var smsClient: SmsRetrieverClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        smsClient = SmsRetriever.getClient(this)
        
        initSmsListener()
    }
...

```
Our API will transmit a `SmsRetriever.SMS RETRIEVED ACTION` intent to the app. This happens in the event a device receives a message containing the code. This intent holds the SMS message and background processing status.
To deal with this, we'll make a BroadcastReceiver class:

```
class MessageBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION == intent?.action) {
            val data = intent.extras
            if (data != null) {
                val status = data[SmsRetriever.EXTRA_STATUS] as Status
                var timedOut = false
                var otpCode: String? = null
                when (status.statusCode) {
                    CommonStatusCodes.SUCCESS -> {
                        val appMessage = data[SmsRetriever.EXTRA_SMS_MESSAGE] as String
                        otpCode = appMessage
                    }
                    CommonStatusCodes.TIMEOUT -> {
                        timedOut = true
                    }

                }
                EventBus.getDefault().post(RetrievalEvent(timedOut, otpCode.toString()))
            }
        }
    }
}

```
On the `onReceive()` method, first check the status of `SMS Retriever` background processing. We also construct an instance of the `RetrievalEvent` class. This is the event class that `EventBus` will send to our `Subscriber`. The, class `RetrievalEvent` will be a data class. If you are completely new to [EventBus](https://greenrobot.org/eventbus/), please read on it. 

### RetrievalEvent

```
package com.roberts.smsretriverapi
data class RetrievalEvent (
    val timedOut: Boolean,
    val message: String
    )
    
```
