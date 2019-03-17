#ifndef NEU_ASSERT_H
#define NEU_ASSERT_H 1
#include <neu_types.h>
#include <neu_memory.h>
#include <stdio.h>
#include <string.h>

#define neu_assert_test_case void(*test)(void)

/**
 * Initializes the library.
 */
void neu_assert_init();

/**
 * Adds a test case to list of test cases to execute.
 */
void neu_assert_add_test(neu_assert_test_case);

/**
 * Runs all the registered test cases.
 */
void neu_assert_run();

/**
 * Makes an assertion and specifies an error message.
 */
void neu_assert(boolean condition, const char* message);

/**
 * Prints a report of the outcome of the tests.
 */
void neu_assert_report();
/**
 * Constructs the default assertion error message.
 */
#define neu_assert_assertion_error_message_default \
  char* neu_assert_message_buffer = neu_calloc(char, 150); \
  { \
    sprintf(neu_assert_message_buffer, "Assertion in function '%s' failed! %s:%d", __func__, __FILE__, __LINE__); \
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
#define neu_assert_less_than(val1, val2) { neu_assert_assertion_error_message_default; neu_assert(val1 < val2, neu_assert_message_buffer);}
/**
 * Makes an assertion, that the first value is smaller than the second value and specifies an error message.
 */
#define neu_assert_less_than_m(val1, val2, message) neu_assert(val1 < val2, message)
/*
 * Makes an assertion, that the two provided strings are equal.
 */
#define neu_assert_string_equals(str1, str2) { neu_assert_assertion_error_message_default; neu_assert(strcmp(str1, str2) == 0, neu_assert_message_buffer);}
/*
 * Makes an assertion, that the two provided strings are equal and specifies an error message.
 */
#define neu_assert_string_equals_m(str1, str2, message) neu_assert(strcmp(str1, str2) == 0, message)

/*
 * Makes an assertion, that the provided expression evaluates to true.
 */
#define neu_assert_true(cond) { neu_assert_assertion_error_message_default; neu_assert(cond == true, neu_assert_message_buffer);}
/**
 * Makes an assertion, that the provided expression evaluates to true and specifies an error message.
 */
#define neu_assert_true_m(cond, message) neu_assert(cond == true, message)

/*
 * Makes an assertion, that the provided expression evaluates to false.
 */
#define neu_assert_false(cond) { neu_assert_assertion_error_message_default; neu_assert(cond == false, neu_assert_message_buffer);}
/**
 * Makes an assertion, that the provided expression evaluates to false and specifies an error message.
 */
#define neu_assert_false_m(cond, message) neu_assert(cond == false, message)

/*
 * Makes an assertion, that the provided expression evaluates to a non-zero value.
 */
#define neu_assert_non_zero(cond) { neu_assert_assertion_error_message_default; neu_assert(cond != 0, neu_assert_message_buffer);}
/**
 * Makes an assertion, that the provided expression evaluates to a non-zero value and specifies an error message.
 */
#define neu_assert_non_zero_m(cond, message) neu_assert(cond != 0, message)

#endif