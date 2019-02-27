#include <stdlib.h>
#include <stdio.h>

unsigned long int datumcode(int jaar, int maand, int dag);

int main() {
  printf("%lu\r\n", datumcode(4030, 11, 30));
}

unsigned long int datumcode(int jaar, int maand, int dag) {
  unsigned long int result = dag & 0b11111;
  result += maand & 0b1111 << 5;
  result += jaar << 9;
  return result;
}
