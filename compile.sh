#!/bin/bash

javac -d classes **/*.java #compile to classes folder
cd classes
jar cvfe PWM.jar main/PWM **/*.class # compress .class to a jar
mv PWM.jar ../PWM.jar
cd ..

exit 0
