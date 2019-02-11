#include <stdio.h>
#include <stdlib.h>
#include <time.h>

typedef struct sized_array {
  void* array;
  int length;
} sized_int_array_t;

int get_occurrences(sized_int_array_t array, int num) {
  int result = 0;
  for (int i = 0; i < array.length; ++i) {
    if (((int*)(array.array))[i] == num) {
      ++result;
    }
  }
  return result;
}

int get_occurrences2(int* array, int length, int num) {
  int result = 0;
  for (int i = 0; i < length; ++i) {
    if (array[i] == num) {
      ++result;
    }
  }
  return result;
}

int main() {
  srand(time(0));
  int number_of_items = 200;
  int* numbers = (int*)malloc(sizeof(int) * number_of_items);
  for (int i = 0; i < number_of_items; ++i) {
    numbers[i] = rand() % 10;
  }
  int needle = rand() % 10;
  sized_int_array_t sized_array = {numbers, number_of_items};
  int how_many_times = get_occurrences(sized_array, needle);
  int how_many_times2 = get_occurrences2(numbers, number_of_items, needle);
  printf("The needle %d occurred %d times.\r\n", needle, how_many_times);
  printf("2 The needle %d occurred %d times.\r\n", needle, how_many_times);
  free(numbers);
  return 0;
}