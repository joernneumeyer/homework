#ifndef NEU_MEMORY_H
#define NEU_MEMORY_H 1
#include <stdlib.h>

#define neu(type, size) (type)malloc(sizeof(type) * size)

#endif