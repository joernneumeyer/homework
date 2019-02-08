#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main(int argc, const char** argv, char** env) {
  if (argc != 2) {
    printf("Please provide one environment variable name to this program!\r\n");
    return 1;
  }
  const char* env_name = argv[1];
  printf("%s", getenv(env_name));

  return 0;
}