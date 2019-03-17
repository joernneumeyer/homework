#include <neu_assert.h>
#include <neu_memory.h>
#include <neu_types.h>

neu_types_linked_list_struct(neu_assert_message_node, const char*);
typedef struct neu_assert_test_node {
  neu_assert_test_case;
  struct neu_assert_test_node* next;
} neu_assert_test_node_t;

neu_assert_message_node_t* neu_assert_message_node_make() {
  neu_assert_message_node_t* result = neu_calloc(neu_assert_message_node_t, 1);
  result->value = NULL;
  result->next = NULL;
  return result;
}

neu_assert_test_node_t* neu_assert_test_node_make() {
  neu_assert_test_node_t* result = neu_calloc(neu_assert_test_node_t, 1);
  result->test = NULL;
  result->next = NULL;
  return result;
}


int neu_assert_assertion_counter = 0;
int neu_assert_assertion_counter_failed = 0;

neu_assert_message_node_t* neu_assert_message_head = NULL;
neu_assert_message_node_t* neu_assert_message_tail = NULL;


neu_assert_test_node_t* neu_assert_test_head = NULL;
neu_assert_test_node_t* neu_assert_test_tail = NULL;

void neu_assert_init() {
  neu_assert_message_head = neu_assert_message_node_make();
  neu_assert_message_tail = neu_assert_message_head;

  neu_assert_test_head = neu_assert_test_node_make();
  neu_assert_test_tail = neu_assert_test_head;
}

void neu_assert_add_test(neu_assert_test_case) {
  neu_assert_test_node_t* assert_test = neu_assert_test_node_make();
  assert_test->test = test;
  neu_assert_test_tail->next = assert_test;
  neu_assert_test_tail = assert_test;
}

void neu_assert(boolean condition, const char* message) {
  ++neu_assert_assertion_counter;
  if (condition) return;
  ++neu_assert_assertion_counter_failed;
  neu_assert_message_node_t* assert_message = neu_assert_message_node_make();
  assert_message->value = message;
  neu_assert_message_tail->next = assert_message;
  neu_assert_message_tail = assert_message;
}

void neu_assert_report() {
  if (neu_assert_message_head->next == NULL) {
    printf("No assertion errors. %d assertions without errors.\r\n", neu_assert_assertion_counter);
    return;
  }
  neu_assert_message_node_t* runner = neu_assert_message_head->next;
  printf("The following assertions failed:\r\n\r\n");
  while (runner) {
    printf("%s\r\n", runner->value);
    runner = runner->next;
  }

  int success_rate = 0;

  if (neu_assert_assertion_counter != neu_assert_assertion_counter_failed) {
    success_rate = ((neu_assert_assertion_counter_failed * 10000) / (neu_assert_assertion_counter * 100));
  }

  printf(
    "%d total assertions with %d failures (%d%% success rate)\r\n",
    neu_assert_assertion_counter,
    neu_assert_assertion_counter_failed,
    success_rate
  );
}
