#include "stringlist.h"
#include <stdio.h>

int main() {
  s_node* head = init();
  add(head, "foo");
  add(head, "bar");
  add(head, "this is c");
  printf("%s\r\n", read(head, 1));
  clear(head);
  return 0;
}