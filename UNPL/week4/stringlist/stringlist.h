#ifndef _STRING_LIST_H_
#define _STRING_LIST_H_
typedef struct node {
  char * content;
  struct node* next;
} s_node;
/* pre : head undefined
post: head points to the head and head points to the tail, 
head and tail have content NULL, both act as dummies
*/
s_node* init();
/* pre : head points to the beginning of the list
post: a copy of newContent(design decision) has been added at 
the end of the list*/
void add(s_node*head, char *newContent);
void print(s_node*head);
/* pre : head points to the beginning of the list
post: all allocated memory has been deallocated and head==NULL*/
void clear(s_node*head);

void delete(s_node*head, char* str);
char* read(s_node*head, int index);

#endif