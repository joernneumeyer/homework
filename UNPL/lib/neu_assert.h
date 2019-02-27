#ifndef NEU_ASSERT_H
#define NEU_ASSERT_H 1
#include <neu_types.h>

/**
 * Makes an assertion and specifies an error message.
 */
void assert(boolean condition, cstring message);
/**
 * Makes an assertion, that the two passed values are equal.
 */
#define assert_equals(val1, val2) assert(val1 == val2, sprintf("Equal assertion failed in line %d!", __LINE__))
/**
 * Makes an assertion, that the two passed values are equal and specifies an error message.
 */
#define assert_equals_m(val1, val2, message) assert(val1 == val2, message)

#endif