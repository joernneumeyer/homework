CC=gcc
CFLAGS=-I.

lib: memory assert
	ar rcs libneu.a neu_memory.o neu_assert.o

memory: neu_memory.c
	$(CC) -c $(CFLAGS) neu_memory.c -o neu_memory.o

assert: neu_assert.c
	$(CC) -c $(CFLAGS) neu_assert.c -o neu_assert.o

clean:
	rm *.o -f
	rm *.a -f
	rm *.so -f