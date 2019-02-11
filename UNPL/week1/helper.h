#include <stdlib.h>
#include <stdio.h>

typedef unsigned char bool;
#define false 0
#define true 1

char* read_from_console(int number_of_charcters) {
  char* buffer = (char*)malloc(sizeof(char) * number_of_charcters);
  memset(buffer, 0, sizeof(buffer));
  int number = 0;

  if (fgets(buffer, sizeof(buffer), stdin) == NULL) {
    fprintf(stderr, "Error in fgets()\n");
    exit(EXIT_FAILURE);
  }

  return buffer;
}

void println(const char* str) {
  printf("%s\r\n", str);
}
