#!/bin/bash

trap_with_arg() {
	func="$1"
	shift
	for sig; do
		trap "$func $sig" "$sig"
	done
}

handle_signal() {
	SIGNUM=$1
	for PID in $SPRING_BOOT_PID $NCAT_PID; do
		kill -n $SIGNUM	$PID
	done
}

./mvnw spring-boot:run &
SPRING_BOOT_PID=$!

ncat -lkp 8081 -c ./scripts/handle.sh &
NCAT_PID=$!

trap_with_arg handle_signal SIGINT SIGTERM SIGKILL

wait