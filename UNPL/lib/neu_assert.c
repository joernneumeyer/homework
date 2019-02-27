#include <neu_assert.h>
#include <neu_memory.h>

struct neu_assert_node {
  cstring message;
  struct neu_assert_node* next;
};

struct neu_assert_node* make_neu_assert_node() {
  return neu(struct neu_assert_node, 1);
}

void assert(boolean condition, cstring message) {

}