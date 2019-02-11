#include <stdlib.h>
#include <stdio.h>
#include <time.h>

unsigned char pbcd(int n);

int main() {
  srand(time(0));
  printf("%d\r\n", pbcd(12345));

  return 0;
}

unsigned char pbcd(int n) {
  char least_significant = (char)(n % 10);
  char second_least_significant = (char)((n % 100 - least_significant) / 10);
  return (char)(least_significant + (second_least_significant << 4));
}