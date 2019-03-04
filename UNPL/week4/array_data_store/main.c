#include "array_data_store.h"


int main() {
  init(3);
  insert("Hello");
  insert("foo");
  delete("foo");
  print();
}