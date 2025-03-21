#!/bin/bash

fswatch ./src/back/src/ | (
    while read; do
        echo "Updating source"
        printf "update" | netcat -c localhost 8081
    done
)