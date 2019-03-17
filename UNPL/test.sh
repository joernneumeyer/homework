#!/usr/bin/env bash
cd lib
make > /dev/null
cd ..
gcc test-hook.c -o test.o -lneu -Llib -Ilib > /dev/null
./test.o