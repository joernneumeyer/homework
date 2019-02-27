#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <assert.h>

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
  const char* var_before = getenv("RFTGHJEDFHGTR");
  assert(var_before == NULL);
  setenv("RFTGHJEDFHGTR", "foo", 0);
  setenv("RFTGHJEDFHGTRRTYU", "foo", 0);
  const char* var_after = getenv("RFTGHJEDFHGTR");
  assert(var_after != NULL);
}

char *getenv(const char *name) {
  for (char** e = environ; *e != 0; ++e) {
    char* first = strstr(*e, name);
    if (first != *e) continue;
    int offset = strlen(name) + 1;
    return (*e) + offset;
  }

  return NULL;
}

int setenv(const char *name, const char *value, int overwrite) {
  for (char** e = environ; *e != 0; ++e) {
    char* first = strstr(*e, name);
    if (first != *e) continue;
    if (overwrite == 0) return 0;
    int name_length = strlen(name);
    int copy_langth = name_length + 2 + strlen(value);
    char* name_copy = (char*)malloc(strlen(name) + 2);
    strcpy(name_copy, name);
    name_copy[name_length] = '=';
    name_copy[name_length + 1] = 0;
    strcat(name_copy, value);
    name_copy[copy_langth - 1] = 0;
    *e = name_copy;
    return 0;
  }

  int environ_entry_count = sizeof(environ) / sizeof(char*);
  char** environ_new = (char**)malloc((environ_entry_count + 1) * sizeof(char*));

  for (int i = 0; i < environ_entry_count; ++i) {
    environ_new[i] = environ[i];
  }
  static boolean was_called = false;
  if (was_called) {
    free(environ);
  } else {
    was_called = true;
  }
  environ = environ_new;

  int name_length = strlen(name);
  int copy_langth = name_length + 2 + strlen(value);
  char* name_copy = (char*)malloc(strlen(name) + 2);
  strcpy(name_copy, name);
  name_copy[name_length] = '=';
  name_copy[name_length + 1] = 0;
  strcat(name_copy, value);
  name_copy[copy_langth - 1] = 0;
  environ[environ_entry_count] = name_copy;

  return 0;
}
