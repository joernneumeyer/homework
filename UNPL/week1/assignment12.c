#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef unsigned char boolean;
#define true 1
#define false 0
#define null 0

char *getenv(const char *name);
int setenv(const char *name, const char *value, int overwrite);

extern char **environ;

void something() {
  for (char** e = environ; *e != 0; ++e) {
    printf("%s\r\n", *e);
  }
}

int main(int argc, const char** argv, char** env) {
  char* value = getenv("LANG");
  printf("%s", value);
}

char *getenv(const char *name) {
  for (char** e = environ; *e != 0; ++e) {
    char* first = strstr(*e, name);
    if (first != *e) continue;
    int offset = strlen(name) + 1;
    return (*e) + offset;
  }
}

int setenv(const char *name, const char *value, int overwrite) {
  for (char** e = environ; *e != 0; ++e) {
    char* first = strstr(*e, name);
    if (first != *e) continue;
    if (overwrite == 0) return 0;
    int copy_langth = strlen(name) + 2;
    char* name_copy = (char*)malloc(strlen(name) + 2);
    strcpy(name_copy, name);
    name_copy[copy_langth - 2] = '=';
    name_copy[copy_langth - 1] = 0;
    strcat(name_copy, value);
    *e = name_copy;
    return 0;
  }

  return 1;
}
