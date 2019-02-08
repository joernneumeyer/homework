#include <stdlib.h>
#include <stdio.h>

unsigned long int datumcode(int jaar, int maand, int dag);

int main() {
  printf("%lu\r\n", datumcode(1970, 1, 100));
}

unsigned long int datumcode(int jaar, int maand, int dag) {
  unsigned long int result = (dag << 27) >> 27;
  result += ((maand << 28) >> 28) << 5;
  result += ((jaar << 20) >> 20) << 9;
  return result;
}