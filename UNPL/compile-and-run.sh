#!/bin/bash

set -e

if [[ ! $1 ]];
then
  echo "please provide the path to a C file!"
  exit
fi

# author https://gist.github.com/earthgecko/3089509
TEMP_NAME=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 32 | head -n 1)
TEMP_NAME=${TEMP_NAME}.bin

cd lib && make clean && make && cd ..
gcc $(pwd)/$1 -o ${TEMP_NAME} -Ilib -Llib -lneu

echo "####################"
echo "# STARTING PROGRAM #"
echo "####################"

./${TEMP_NAME}

echo "####################"
echo "#    CLEANING UP   #"
echo "####################"
rm ${TEMP_NAME}