#include <neu_assert.h>

/**
 * Dummy definition for the function which is called
 * by the library in order to register all tests.
 */
void neu_assert_register_tests();

int main() {
  neu_assert_init();
  neu_assert_register_tests();
  neu_assert_run();
  neu_assert_report();
}