# Hand-drawn physics question, to Box2D simulation on phone

As part of bachelor's projects under SIPL. there was a project to convert Hand-drawn physics (mechanics) questions, into a game engine  (Box2D). in order to simulate the motion of object in the questions. using deep learning and image processing, the model create JSON mapper file that contain all the object in the simulation, and there connection.
This project contain the Box2D implamentation of this project (the Physics Simulation), in the phone client part (Android). 
The server client part was implemented using socket communication.

![alt text](https://github.com/BIueMan/Physics1_to_box2d_on_phone/blob/master/images/physics.PNG)

The main project is split into server and phone client. the client send a picture of the physics question to the server. the server side use a alexnet based object detection to detect all the following parts. and then use image processing to connect all the necessary part. and create a JSON mapper file in order to send beck the mapper to the client. this code contain the full client side, with the server communication part.

![image](https://github.com/BIueMan/Physics1_to_box2d_on_phone/blob/master/images/physics_parts.PNG)

![image](https://user-images.githubusercontent.com/55624377/168762319-540dd3c7-0981-4007-90a0-126696e84b7a.png)




## Little abuild Box2D
* Box2D is a 2D rigid body simulation library that animates physical interactions.
* commonly used in games to make objects move in realistic ways and make the game world more interactive.
* Box2D was written in C++ by Erin Catto and has a JAVA post too, so it can be imported to run on android.

![alt text](https://github.com/BIueMan/Physics1_to_box2d_on_phone/blob/master/images/physics_03.PNG)
![alt text](https://github.com/BIueMan/Physics1_to_box2d_on_phone/blob/master/images/physics_04.PNG)
