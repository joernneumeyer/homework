#ifndef NEU_MEMORY_H
#define NEU_MEMORY_H 1
#include <stdlib.h>

#define neu_malloc(type, factor) (type*)malloc(sizeof(type) * factor)
#define neu_calloc(type, factor) (type*)calloc(factor, sizeof(type))

#endif