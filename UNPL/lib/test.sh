#!/usr/bin/env bash

set -e

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
AUTO_CLEAN=1

if [[ ! -d $1 ]]
then
  echo "Please provide a valid test folder!"
  exit
fi

TEST_FOLDER=$1

shift

while [[ $1 ]]
do
  case $1 in
    "--no-clean")
    AUTO_CLEAN=0
    ;;
  esac
  shift
done

cd lib
make > /dev/null
cd ..

TEST_FILES=$(find ${TEST_FOLDER} -name "*.test.h")

TEST_SOURCE=$(printf "#include <neu_assert.h>\r\n")

for test_file in $TEST_FILES
do
 TEST_SOURCE="${TEST_SOURCE}#include \"${test_file}\"$(printf '\r\n')"
done

TEST_SOURCE="${TEST_SOURCE}$(echo "int main() { neu_assert_init();")"

for test_file in $TEST_FILES
do
  functions=$(cat $test_file | grep -Eo "void test[a-zA-Z_]+" | echo $(read arr; set $arr; echo $2))
  for func in $functions
  do
    TEST_SOURCE="${TEST_SOURCE}neu_assert_add_test($func);"
  done
  #gcc $test_file -o testlib.o -lneu -Llib -Ilib -c
  #gcc test-hook.c testlib.o -o test.o -lneu -Llib -Ilib > /dev/null
  #./test.o
done

TEST_SOURCE="${TEST_SOURCE}neu_assert_run();
  neu_assert_report();
}"

echo ${TEST_SOURCE} > ${DIR}/test.c

gcc -o ${DIR}/test.o -lneu -Llib -Ilib -I. ${DIR}/test.c
./test.o

if [[ 1 = $AUTO_CLEAN ]]
then
  rm ${DIR}/test.c
  rm ${DIR}/test.o
fi