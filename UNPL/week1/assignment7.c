#include <stdlib.h>
#include <stdio.h>

int strcmp(const char *s1, const char *s2);

int main() {
  printf("%d\r\n", strcmp("Hello", "Hello"));
  printf("%d\r\n", strcmp("", ""));
  printf("%d\r\n", strcmp("Hell", "Hello"));
  printf("%d\r\n", strcmp("Hell", "Kello"));
  printf("%d\r\n", strcmp("Tello", "Hello"));
}

int strcmp(const char *s1, const char *s2) {
  int s1_length = 0;
  int s2_length = 0;
  for (; s1[s1_length] != 0; ++s1_length);
  for (; s2[s2_length] != 0; ++s2_length);

  if (s1_length == 0 && s2_length == 0) {
    return 0;
  } else if(s1_length == 0) {
    return -1;
  } else if(s2_length == 0) {
    return 1;
  }

  for (int i = 0; i < s1_length; ++i) {
    if (s1_length == i && s2_length == i) {
      return 0;
    } else if (s1_length == i) {
      return -1;
    } else if (s2_length == i) {
      return 1;
    }
    if (s1[i] != s2[i]) {
      return s1[i] - s2[i];
    }
  }

  return 0;
}
