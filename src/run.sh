#!/bin/sh

find . -name "*.java" > sources.txt
javac -sourcepath . @sources.txt
java ru.school.avaj.Simulator scenario.txt