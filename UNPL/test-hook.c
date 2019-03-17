#include <neu_assert.h>

int main() {
  neu_assert_init();
  neu_assert_equals(1, 2);
  neu_assert_report();
}