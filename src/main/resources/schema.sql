
IF  EXISTS (
    SELECT * FROM sys.tables t 
    JOIN sys.schemas s ON (t.schema_id = s.schema_id) 
    WHERE s.name = 'dbo' AND t.name = 'ExampleObject1') 
    DROP TABLE ExampleObject1 
   DROP SEQUENCE EOseq;
   
IF NOT EXISTS (
    SELECT * FROM sys.tables t 
    JOIN sys.schemas s ON (t.schema_id = s.schema_id) 
    WHERE s.name = 'dbo' AND t.name = 'ExampleObject1') 	
 CREATE TABLE  ExampleObject1 (
    id   int IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
   
);
 /***/
 CREATE SEQUENCE EOseq
    START WITH 1
    INCREMENT BY 50
    NO CACHE
    ;
