CC=gcc
CFLAGS=-I.

lib: memory assert types
	ar rcs libneu.a neu_memory.o neu_assert.o neu_types.o

memory: neu_memory.c
	$(CC) -c $(CFLAGS) neu_memory.c -o neu_memory.o

assert: neu_assert.c
	$(CC) -c $(CFLAGS) neu_assert.c -o neu_assert.o

types: neu_types.h
	$(CC) -c $(CFLAGS) neu_types.h -o neu_types.o

clean:
	rm *.o -f
	rm *.a -f
	rm *.so -f