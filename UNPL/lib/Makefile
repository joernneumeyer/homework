CC=gcc

lib: neu_memory.o neu_assert.o
	ar rcs libneu.a neu_memory.o neu_assert.o

memory: neu_memory.c
	$(CC) -c neu_memory.c -o neu_memory.o

assert: neu_assert.c
	$(CC) -c neu_assert.c -o neu_assert.o

clean:
	rm *.o -f
	rm *.a -f
	rm *.so -f