#include <stdlib.h>
#include <stdio.h>
#include <string.h>

void rectangle(int w, int h) {
  for (int k = 0; k < w; ++k) {
    printf("*");
  }
  printf("*\r\n");
  for (int i = 0; i < h; ++i) {
    printf("*");
    for (int k = 1; k < w; ++k) {
      printf(" ");
    }
    printf("*\r\n");
  }
  for (int k = 0; k < w; ++k) {
    printf("*");
  }
  printf("*\r\n");
}

void rectangle_filled(int w, int h) {
  for (int i = 0; i < h; ++i) {
    for (int k = 0; k < w; ++k) {
      printf("*");
    }
    printf("\r\n");
  }
}

int main() {
  rectangle(20, 10);
  return 0;
}