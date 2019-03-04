#include "array_data_store.h"
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

char** array_data_store_pointer = 0;
bool* array_data_store_free_slots = 0;
int array_data_store_slot_count = 0;

void init(int n) {
  if (n <= 0) return;
  array_data_store_slot_count = n;
  array_data_store_pointer = (char**)malloc(sizeof(char*) * n);
  array_data_store_free_slots = (bool*)malloc(sizeof(bool) * n);
  for (int i = 0; i < n; ++i) array_data_store_free_slots[i] = true;
}

void destroy() {
  free(array_data_store_pointer);
  free(array_data_store_free_slots);
  array_data_store_pointer = 0;
  array_data_store_free_slots = 0;
  array_data_store_slot_count = 0;
}

bool insert(char * str) {
  int i = 0;
  for (;i<array_data_store_slot_count; ++i) {
    if (array_data_store_free_slots[i] == true) {
      break;
    }
  }
  if (i >= array_data_store_slot_count) return 0;
  array_data_store_free_slots[i] = false;
  char* copy = (char*)malloc(sizeof(char) * strlen(str));
  memset(copy, 0, sizeof(char) * strlen(str));
  strcpy(copy, str);
  array_data_store_pointer[i] = copy;
  return 1;
}

void delete(char * str) {
  for (int i = 0; i < array_data_store_slot_count; ++i) {
    if (strcmp(array_data_store_pointer[i], str) == 0) {
      free(array_data_store_pointer[i]);
      array_data_store_free_slots[i] = true;
    }
  }
}

void print() {
  if (array_data_store_pointer == 0) return;
  for (int i = 0; i < array_data_store_slot_count; ++i) {
    if (array_data_store_pointer[i] == 0) continue;
    printf("%s\r\n", array_data_store_pointer[i]);
  }
}