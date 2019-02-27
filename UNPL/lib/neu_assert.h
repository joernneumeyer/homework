#ifndef NEU_ASSERT_H
#define NEU_ASSERT_H 1
#include <neu_types.h>
#include <neu_memory.h>
#include <stdio.h>
#include <string.h>

void neu_assert_init();

/**
 * Makes an assertion and specifies an error message.
 */
void neu_assert(boolean condition, cstring message);

void neu_assert_report();

/**
 * Makes an assertion, that the two passed values are equal.
 */
#define neu_assert_equals(val1, val2) { char* neu_assert_str_buffer = neu_alloc(char, 150); memset(neu_assert_str_buffer, 0, sizeof(char) * 150); sprintf(neu_assert_str_buffer, "Equal assertion failed in file '%s' in line %d!", __FILE__, __LINE__); neu_assert(val1 == val2, neu_assert_str_buffer);}
/**
 * Makes an assertion, that the two passed values are equal and specifies an error message.
 */
#define neu_assert_equals_m(val1, val2, message) neu_assert(val1 == val2, message)

#endif