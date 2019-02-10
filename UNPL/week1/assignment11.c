#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "helper.h"
#define NAME_TO_STRING(NAME) #NAME
#define NUMBER_OF_DAYS 3

enum week {Mon, Tue, Wed, Thur, Fri, Sat, Sun};
typedef enum week Week;
const char* week_strings[] = {
  NAME_TO_STRING(Mon),
  NAME_TO_STRING(Tue),
  NAME_TO_STRING(Wed),
  NAME_TO_STRING(Thur),
  NAME_TO_STRING(Fri),
  NAME_TO_STRING(Sat),
  NAME_TO_STRING(Sun)
};

const char* days[NUMBER_OF_DAYS];

bool day_string_exists(const char* day) {
  for (char i = 0; i < 7; ++i) {
    int diff = my_strcmp(day, week_strings[i]);
    char buffer[5];
    sprintf(buffer, "%d", diff);
    println(buffer);
    if (diff == 0) {
      return true;
    }
  }

  return false;
}

int main() {
  for (int i = 0; i < NUMBER_OF_DAYS; ++i) {
    const char* input = read_from_console(4);
    if (day_string_exists(input) == false) {
      --i;
      println("false");
      continue;
    }
    println("correct");
    days[i] = input;
  }

  return 0;
}
