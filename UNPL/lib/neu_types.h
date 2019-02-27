#ifndef NEU_TYPES_H
#define NEU_TYPES_H 1

typedef unsigned char boolean;
#define true 1
#define false 0

#define neu_types_linked_list_struct(name, type) \
  typedef struct name { \
    type value; \
    struct name* next; \
  } name ## _t;

#define neu_types_double_linked_list_struct(name, type) \
typedef struct name { \
  type value; \
  struct name* next; \
  struct name* last; \
} name ## _t;

#endif