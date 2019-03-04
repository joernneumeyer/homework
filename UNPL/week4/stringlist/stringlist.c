#include "stringlist.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

s_node* init() {
  s_node* head = (s_node*)malloc(sizeof(s_node));
  head->content = 0;
  head->next = 0;
  return head;
};

void add(s_node*head, char *newContent) {
  s_node* node = (s_node*)malloc(sizeof(s_node));
  char* content_buffer = (char*)malloc(sizeof(char) * strlen(newContent));
  strcpy(content_buffer, newContent);
  node->content = content_buffer;
  node->next = 0;
  s_node* i = head;
  while (i->next) {
    i = i->next;
  }
  i->next = node;
}

void print(s_node*head) {
  s_node* i = head->next;
  while (i) {
    printf("%s\r\n", i->content);
    i = i->next;
  }
}

void clear(s_node*head) {
  s_node* i = head;
  s_node* temp;
  while (i) {
    free(i->content);
    temp = i->next;
    free(i);
    i = temp;
  }
}

void delete(s_node*head, char* str) {
  s_node* i = head->next;
  s_node* last = head;
  while (i) {
    if (strcmp(str, i->content) == 0) {
      last->next = i->next;
      free(i->content);
      free(i);
    }
    last = i;
    i = i->next;
  }
}

char* read(s_node*head, int index) {
  s_node* i = head->next;
  int current_index = -1;
  while (i) {
    ++current_index;
    if (current_index == index) {
      return i->content;
    }
    i = i->next;
  }

  return NULL;
}