#!/bin/bash

mkdir -p class

javac -encoding euckr -d ./class ./src/*

java -cp ./class DentalCheck
