#!/bin/bash

if [ ! -f ./running ]; then
	read msg
	if [ $msg == "update" ]; then
		echo "" > running
		./mvnw compile
		rm running
	fi
fi