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
#define neu_assert_assertion_error_message_default \
  char* neu_assert_message_buffer = neu_alloc(char, 150); \
  { \
    memset(neu_assert_message_buffer, 0, sizeof(char) * 150); \
    sprintf(neu_assert_message_buffer, "Assertion in file '%s' on line %d failed!", __FILE__, __LINE__); \
  }
/*
 * Makes an assertion, that the two passed values are equal.
 */
#define neu_assert_equals(val1, val2) { neu_assert_assertion_error_message_default; neu_assert(val1 == val2, neu_assert_message_buffer);}
/**
 * Makes an assertion, that the two passed values are equal and specifies an error message.
 */
#define neu_assert_equals_m(val1, val2, message) neu_assert(val1 == val2, message)

/*
 * Makes an assertion, that the first value is larger than the second value.
 */
#define neu_assert_bigger_than(val1, val2) { neu_assert_assertion_error_message_default; neu_assert(val1 > val2, neu_assert_message_buffer);}
/**
 * Makes an assertion, that the first value is larger than the second value and specifies an error message.
 */
#define neu_assert_bigger_than_m(val1, val2, message) neu_assert(val1 > val2, message)

/*
 * Makes an assertion, that the first value is smaller than the second value.
 */
#define neu_assert_smaller_than(val1, val2) { neu_assert_assertion_error_message_default; neu_assert(val1 < val2, neu_assert_message_buffer);}
/**
 * Makes an assertion, that the first value is smaller than the second value and specifies an error message.
 */
#define neu_assert_smaller_than_m(val1, val2, message) neu_assert(val1 < val2, message)

#endif