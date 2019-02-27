#ifndef NEU_MEMORY_H
#define NEU_MEMORY_H 1
#include <stdlib.h>

#define neu_alloc(type, factor) (type*)malloc(sizeof(type) * factor)

#endif