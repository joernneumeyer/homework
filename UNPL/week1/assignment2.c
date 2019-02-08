#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void sum_int_array(int* numbers, int length, int* sum);

int main() {
  srand(time(0));
  const int number_of_items = 200;
  int numbers[number_of_items];
  for (int i = 0; i < number_of_items; ++i) {
    numbers[i] = rand() % 50;
  }
  int sum = 0;
  sum_int_array(numbers, number_of_items, &sum);
  printf("The sum of the array is %d\r\n", sum);

  return 0;
}

void sum_int_array(int* numbers, int length, int* sum) {
  int buffer = 0;
  for (int i = 0; i < length; ++i) {
    buffer += numbers[i];
  }
  *sum = buffer;
}
