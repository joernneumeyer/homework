#include <neu_assert.h>
#include <neu_memory.h>
#include <neu_types.h>

typedef struct neu_assert_node {
  cstring message;
  struct neu_assert_node* next;
} neu_assert_node_t;

neu_assert_node_t* neu_assert_node_make() {
  return neu(neu_assert_node_t, 1);
}

neu_assert_node_t* neu_assert_message_head = NULL;

void neu_assert_init() {
  neu_assert_message_head = neu_assert_node_make();
}

void neu_assert(boolean condition, cstring message) {
  if (condition) return;
  neu_assert_node_t* assert_message = neu_assert_node_make();
  assert_message->message = message;
  neu_assert_message_head->next = assert_message;
}
