# Scan
This project is for have an easy way to find files/folder at the computers and servers (biggest | types of files) without the need to know or use the command line (well this is not really truth, you have to use one for do the install).

The are already others programs how do this kind of things, but all of them use old UI system and is very hard to change and use it how you like.

This project use java 8/vertx (not blocking process) and Angular2 

## How it work

This program have two important part:

* The first part is the server, this one will search and find all type of file from your computer and save the information at one memory database
* And the second part is the html client, this way you can show you all the information in an easy way and if you want you can modify the html code and show the way you want 

## At this moment

For the moment the program will scan only folder typed by yourself, like **c:\\** (windows) or **/** (unix like) and will show you the 20 biggest file finded.

The code source can change a lots between commit.

## How to use it

You have to compile the program using maven and execute the **server-0.1-SNAPSHOT.jar** and open your browser at **localhost:8080**

For compile you need:

* Java 8
* Node JS > 6
* Angular cli

## TODO

* Add useful features
* Migrate from Angular cli to gulp (or other similar)
* Make the node server version (for all people who doesn't like Java)
* Add documentation
