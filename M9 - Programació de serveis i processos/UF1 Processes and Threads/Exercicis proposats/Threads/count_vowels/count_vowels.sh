#!/bin/bash

rm -rf ./text-files/

mkdir -p ./text-files/{1998..2003}/{01..12}/{a..z}/

file_input="/usr/share/dict/american-english"

for d in $(find ./text-files/ -type d | grep '[a-z]$' | sort); do
    for n in {00..99}; do
        file_output="$d/text_file_${n}"
        shuf -n $((RANDOM % 10000 + 1)) $file_input | tr '\n' ' ' > $file_output
        echo "end." >> $file_output
    done
    
    echo $d
done