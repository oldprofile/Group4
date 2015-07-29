ALTER TABLE testdb.users
ADD FULLTEXT INDEX `FullTextAdmin` (email ASC, name ASC, login ASC);