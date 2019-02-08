#include <stdlib.h>
#include <stdio.h>

const int row_count = 8;
const int column_count = 8;
int* column_numbers = 0;

void chess_field_to_array_index(const char* field, int* row, int* column) {
  const char* rows_chars = "ABCDEFGH";

  for (int i = 0; i < row_count; ++i) {
    if (field[0] == rows_chars[i]) {
      *row = i;
      break;
    }
  }

  int column_number = field[1] - 48;

  for (int i = 0; i < column_count; ++i) {
    if (column_number == column_numbers[i]) {
      *column = i;
      break;
    }
  }
}

int main() {
  column_numbers = (int*)malloc(sizeof(int) * column_count);
  for (int i = 0; i < column_count; ++i) {
    column_numbers[i] = i + 1;
  }

  int col = -1, row = -1;
  chess_field_to_array_index("C5", &row, &col);
  printf("The resulting row index is %d and the column index is %d\r\n", row, col);
  free(column_numbers);
}
