Abstract
--------

In this project I will use the toolset of Java EE to develop a thin-client application which helps non-native speakers to better their English pronunciation. The general idea is to enable the users to train themselfs through repetition and give them feedback on how a certain phrase would be pronounced by a native speaker. 

Project outline
---------------

### Key use cases

The architecturel design of the application is affected by the key use cases of the desired system. 

1. Users can record they voice, and play it back 
2. Users can fetch native samples from the server and play them back
3. Users can request meta-information on native phrase samples

### Architecture

The system can be divided into three major layers, each with its own responsibility. 

- Client side: Controls the user's media devices, like the microphone and the speakers to record and play speech. 
- Front-end: Serves as a proxy between the lower-level services and the client scripts by implementing a REST like server.
- Back-end: Implements actual business logic, like voice processing or database querying. 

Furthermore, the backend is divided into two major modules based on the use-cases above

- Voice module (use case 1.): Processes user samples and returns its analysis to the frontend. For example determines the actual interval of the speech to help the eliminate the silent parts when playing back on the client side. 

- Native module (user case 2. & 3.): Manages the native voice samples. **Not yet implemented.**












 








 


