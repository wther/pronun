How I Sound? - English Pronunciation Trainer
============================================

This project is mainly academical and attempts to create an application helping non-native speakers to improve their English pronunciation or learn different types of English accents. 

How to launch the application?
-----------------------------

You will need to clone this repository onto your local machine, and Java JDK and Maven is required. You can compile and launch the server with these commands:

    mvn clean install
    mvn jetty:run-war
    
Alternatively you may deploy the war file in *pronun-webapp/target/* to a Tomcat server, and run it that way. Once you launched the application its accessible here:

    http://localhost:8080/

What's done so far?
-------------------

What a little over a month into the development, here's what's done so far. 

 - Skeleton of the **Java server** is implemented, including **some voice processing** functionality. No database yet.
 - Client side scripts for **speech capturing**, **speech playback** and native sample playback. But only for one sample. 
 - Basically you're able to train your English pronuncation of the word **deterrent**.

If you launch the application you will see three buttons (play native sample, capture your speech and playback your voice), try them :)

Project outline
---------------

OK, so what I'd like to do, is to put all these tools together and create a simple but effective way to train your English pronunciation on a daily basis. 

The idea is that the application will give you phrases that you have to say out loud, and then you'll be able to compare the way you pronounced the words to how a native speaker would pronounce it. 

### Milestone 1 - Demo ###

The target of the first milestone is to familiarize myself with the basic concepts needed to create a Web-based, thin-client application, thus the title: "JavaEE based web applications".  I will create a demo with the following functionality:
 * The server stores and manages the **native phrase samples** and their meta information (phonemes, text, etc.)
 * Users can record themselves pronounce a phrase and they can **hear their voice compared to a native speaker**.
 * I do not wish the create education content myself, and the demo will not try to algorithmically compare the recorded and native samples.
 
In order to achieve this I will familiarize myself with the following subjects:
 * Science behind voice, phonemes, pronunciation and accents
 * HTML5 and client side scripting, controlling a device's microphone
 * JavaEE application development, database management, etc.
 * Sound and voice processing, data mining for usable samples.


Inspirations
------------

 * There are countless videos on you Youtube depicting a specific word or phrase and **explaining its proper pronunciation** (e.g. http://www.youtube.com/watch?v=LR_AhXb8tTg)
 * Various websites and mobile apps provide **pronounce a word** functionality (e.g. http://www.pronouncehow.com/english/miscellaneous_pronunciation)
 * Several mobile apps are trying to teach English, some even use the **smartphone's microphone** to make you pronounce a certain word or phrase (e.g. Duolingo)
 * Google's speech recognition has proven that good quality **speech to word** recognition is possible. (Try: https://www.google.com/intl/en/chrome/demos/speech.html)
 * There are a few open source libraries offering **speech to phoneme** or **speech to text** conversion (http://cmusphinx.sourceforge.net/wiki/)
 * Accents, pronunciation, speech recognition and sound processing is a widely researched field
 








 


