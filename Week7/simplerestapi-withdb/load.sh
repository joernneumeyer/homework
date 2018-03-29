#!/bin/bash
echo loading fresh database

dropdb --if-exists words;
dropdb --if-exists testwords;
createdb -O exam words
createdb -O exam testwords

cat schema.sql | psql -U exam -X words
cat schema.sql | psql -U exam -X testwords
