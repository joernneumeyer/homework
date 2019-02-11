#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main() {
  int smallest = __INT32_MAX__;
  int second_smallest = __INT32_MAX__;

  do {
    char buffer[10];
    int number = 0;

    if (fgets(buffer, sizeof(buffer), stdin) == NULL) {
      fprintf(stderr, "Error in fgets()\n");
      exit(EXIT_FAILURE);
    }

    if (sscanf(buffer, "%d", &number) != 1) {
      break;
    }

    if (number < smallest) {
      second_smallest = smallest;
      smallest = number;
    } else if (number < second_smallest) {
      second_smallest = number;
    }
  } while(1);

  printf("%d\r\n", second_smallest);

  return 0;
}
