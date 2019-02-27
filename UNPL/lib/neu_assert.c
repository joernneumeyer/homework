#include <neu_assert.h>
#include <neu_memory.h>
#include <neu_types.h>

typedef struct neu_assert_node {
  cstring message;
  struct neu_assert_node* next;
} neu_assert_node_t;

neu_assert_node_t* neu_assert_node_make() {
  neu_assert_node_t* result = neu_alloc(neu_assert_node_t, 1);
  result->message = NULL;
  result->next = NULL;
  return result;
}


int neu_assert_assertion_counter = 0;
int neu_assert_assertion_counter_failed = 0;

const char* neu_assert_status_failed = "failed";
const char* neu_assert_status_success = "success";
neu_assert_node_t* neu_assert_message_head = NULL;
neu_assert_node_t* neu_assert_message_tail = NULL;

void neu_assert_init() {
  neu_assert_message_head = neu_assert_node_make();
  neu_assert_message_head->message = neu_assert_status_success;
  neu_assert_message_tail = neu_assert_message_head;
}

void neu_assert(boolean condition, cstring message) {
  ++neu_assert_assertion_counter;
  if (condition) return;
  ++neu_assert_assertion_counter_failed;
  neu_assert_message_head->message = neu_assert_status_failed;
  neu_assert_node_t* assert_message = neu_assert_node_make();
  assert_message->message = message;
  neu_assert_message_tail->next = assert_message;
  neu_assert_message_tail = assert_message;
}

void neu_assert_report() {
  if (neu_assert_message_head->message == neu_assert_status_success) {
    printf("No assertion errors!\r\n");
    return;
  }
  neu_assert_node_t* runner = neu_assert_message_head;
  while (runner) {
    printf("%s\r\n", runner->message);
    runner = runner->next;
  }
}
