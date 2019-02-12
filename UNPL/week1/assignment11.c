#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "helper.h"
#define NUMBER_OF_DAYS 3

enum week {Mon, Tue, Wed, Thur, Fri, Sat, Sun};
typedef enum week Week;
const char* week_strings[] = {
  "Mon",
  "Tue",
  "Wed",
  "Thur",
  "Fri",
  "Sat",
  "Sun"
};

char* days[NUMBER_OF_DAYS];

bool day_string_exists(const char* day) {
  for (char i = 0; i < 7; ++i) {
    int diff = strcmp(day, week_strings[i]);
    if (diff == 0) {
      return true;
    }
  }

  return false;
}

int main() {
  println("started");
  for (int i = 0; i < NUMBER_OF_DAYS; ++i) {
    char* input = read_from_console(4);
    if (day_string_exists(input) == false) {
      --i;
      continue;
    }
    days[i] = input;
  }

  for (int i = 0; i < NUMBER_OF_DAYS; ++i) {
    println(days[i]);
    free(days[i]);
  }

  return 0;
}
