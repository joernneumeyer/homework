#ifndef NEU_ASSERT_H
#define NEU_ASSERT_H 1
#include <neu_types.h>

void neu_assert_init();

/**
 * Makes an assertion and specifies an error message.
 */
void neu_assert(boolean condition, cstring message);
/**
 * Makes an assertion, that the two passed values are equal.
 */
#define neu_assert_equals(val1, val2) neu_assert(val1 == val2, sprintf("Equal assertion failed in line %d!", __LINE__))
/**
 * Makes an assertion, that the two passed values are equal and specifies an error message.
 */
#define neu_assert_equals_m(val1, val2, message) neu_assert(val1 == val2, message)

#endif