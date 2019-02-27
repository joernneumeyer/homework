#include <stdlib.h>
#include <stdio.h>

typedef unsigned char bool;
#define false 0
#define true 1

char* read_from_console(int number_of_charcters) {
  char* buffer = (char*)malloc(sizeof(char) * (number_of_charcters + 1));
  memset(buffer, 0, sizeof(buffer));
  int number = 0;

  if (fgets(buffer, sizeof(buffer), stdin) == NULL) {
    fprintf(stderr, "Error in fgets()\n");
    exit(EXIT_FAILURE);
  }

  for (int i = number_of_charcters; i != 0; --i) {
    if (buffer[i] == '\n') {
      buffer[i] = '\0';
      if (buffer[i - 1] == '\r') {
        buffer[i - 1] = '\0';
      }
    }
  }

  return buffer;
}

void println(const char* str) {
  printf("%s\r\n", str);
}
