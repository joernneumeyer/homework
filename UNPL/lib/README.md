# neu-lib
neu-lib is a small library I build to add a little more convenience to my C assignments.

## neu_assert
neu_assert is my assertion library.
It is very simple, straight forward and offers some test reporting.

### neu_assert usage
It is mandatory to call the function ```neu_assert_init()``` before making any assertions!
That initialization function is responsible to initialize the underlying data structures used to track the assertions.

A simple example could look like this:
```c
#include <neu_assert.h>

int main() {
  neu_assert_init();
  neu_assert_equals(1, 1);
  neu_assert_report();
}
```
Sine this assertion is correct, the output of the report look like this ```No assertion errors. 1 assertions without errors.```

In case of a falsy assertion, like in:
```c
#include <neu_assert.h>

int main() {
  neu_assert_init();
  neu_assert_equals(1, 2);
  neu_assert_report();
}
```
The report output looks like this:
```
The following assertions failed:

Assertion in function 'main' failed! /homework/UNPL/test.c:5
1 total assertions with 1 failures (0% success rate)
```

In these examples, I made use of one of the pre-defined assertion macros.
The actual assertiob function ```void neu_assert(boolean condition, const char* message)``` can also just be used directly.
However, there are some pre-defined macros, which also make use auto generated error messages, to give hints about the failed assertion.
#### pre-defined assertion macros
All of the following pre-defined macros will use the default failed message, in case the assertion fails.
If you want to provide your own error message, you should add an ```_m``` to the macro you want to use, so ```neu_assert_equals(val1, val2)``` becomes ```neu_assert_equals_m(val1, val2, message)```.

* ```neu_assert_equals(val1, val2)``` checks if the two provided values are equal
* ```neu_assert_bigger_than(val1, val2)``` checks if the first value is larger than the second value
* ```neu_assert_less_than(val1, val2)``` checks if the first value is smaller than the second value
* ```neu_assert_string_equals(str1,str2)``` checks if the two provided string are equal (uses strcmp from string.h)
* ```neu_assert_true(val)``` checks if the provided value is true
* ```neu_assert_false(val)``` checks if the provided value is false
* ```neu_assert_non_zero(val)``` checks if the provided value is not zero