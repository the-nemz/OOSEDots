Todo Demo Web App
=================

This repository contains a small Todo list web application.  It is a complete web stack from frontend to server to database.

Travis Status
-------------
![](https://travis-ci.org/jhu-oose/todo.svg)


Setup
-----

It depends only on Java 8 and Maven to build and run.

For details on installing Java 8 and an IDE that includes Maven, see the [OOSE Tools] page.

The lecture that goes with this code is the [OOSE Web Java Lecture].


Usage
-----

<<<<<<< HEAD
The code should be directly importable as an existing Maven project into Eclipse or IntelliJ, and should directly build and run from within the IDE.

If you are using IntelliJ, clone the git repository on your computer and then use the IntelliJ Import function to import that clone.  Select the pom.xml file as what you want to import (to be clear you are importing it as a Maven project), and you can use the defaults for all other import parameters.  Once it is imported you can invoke any of the Maven phases.  You can right-click on the Bootstrap class to start the server.  Or, set up a run configuration to run <tt>Bootstrap.main()</tt>  -- from the Run menu select Edit Configurations, add (+) a new Application configuration, and set the main class to be com.todoapp.Bootstrap.  The green triangle will then launch the server.

If you instead want to compile and run from the command line, you will need the Maven command line installed and invoke:
=======
The code should be directly importable as an existing Maven project into Eclipse or IntelliJ, and should directly build and run from within the IDE.  You need to set up a run configuration to run <tt>Bootstrap.main()</tt>.

If you instead want to compile and run from the command line, assuming you have the Maven command line installed:
>>>>>>> 4f03b33a615c00f3efb960cfc3612f8bbed28213

```console
mvn package
java -jar target/todoapp1-1.0-SNAPSHOT.jar
```

Now simply point your browser to http://localhost:8080 to use the application.

[OOSE Tools]:http://pl.cs.jhu.edu/oose/resources/tools.shtml
[OOSE Web Java Lecture]: http://pl.cs.jhu.edu/oose/lectures/webjava.shtml
