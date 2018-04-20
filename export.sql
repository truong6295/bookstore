--------------------------------------------------------
--  File created - Friday-April-20-2018   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table BOOK
--------------------------------------------------------

  CREATE TABLE "BOOKSTORE"."BOOK" 
   (	"IDBOOK" NUMBER(*,0), 
	"NAMEBOOK" VARCHAR2(225 BYTE), 
	"AUTHOR" VARCHAR2(225 BYTE), 
	"CATERGORY" VARCHAR2(225 BYTE), 
	"PRICE" FLOAT(126), 
	"DESCRIPTION" VARCHAR2(225 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Sequence SEQUENCE1
--------------------------------------------------------

   CREATE SEQUENCE  "BOOKSTORE"."SEQUENCE1"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
REM INSERTING into BOOKSTORE.BOOK
SET DEFINE OFF;
Insert into BOOKSTORE.BOOK (IDBOOK,NAMEBOOK,AUTHOR,CATERGORY,PRICE,DESCRIPTION) values (1,'english','tomt','nglishe',20001,'teach english');
Insert into BOOKSTORE.BOOK (IDBOOK,NAMEBOOK,AUTHOR,CATERGORY,PRICE,DESCRIPTION) values (2,'itauto','petter','codel',230000,'teach coder');
Insert into BOOKSTORE.BOOK (IDBOOK,NAMEBOOK,AUTHOR,CATERGORY,PRICE,DESCRIPTION) values (3,'history world','adam','history',500054,'teach history for student');
Insert into BOOKSTORE.BOOK (IDBOOK,NAMEBOOK,AUTHOR,CATERGORY,PRICE,DESCRIPTION) values (4,'exp soccer','rooney','soccer',2000,'teach soccer');
Insert into BOOKSTORE.BOOK (IDBOOK,NAMEBOOK,AUTHOR,CATERGORY,PRICE,DESCRIPTION) values (5,'exp soccer of 2','rooney','soccer',2000,'teach soccer');
Insert into BOOKSTORE.BOOK (IDBOOK,NAMEBOOK,AUTHOR,CATERGORY,PRICE,DESCRIPTION) values (6,'exp soccer of 3','rooney','soccer',2000,'teach soccer');
--------------------------------------------------------
--  DDL for Index BOOK_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BOOKSTORE"."BOOK_PK" ON "BOOKSTORE"."BOOK" ("IDBOOK") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger AUTO_ID
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "BOOKSTORE"."AUTO_ID" 
   before insert on "BOOKSTORE"."BOOK" 
   for each row 
begin  
   if inserting then 
      if :NEW."IDBOOK" is null then 
         select SEQUENCE1.nextval into :NEW."IDBOOK" from dual; 
      end if; 
   end if; 
end;

/
ALTER TRIGGER "BOOKSTORE"."AUTO_ID" ENABLE;
--------------------------------------------------------
--  Constraints for Table BOOK
--------------------------------------------------------

  ALTER TABLE "BOOKSTORE"."BOOK" ADD CONSTRAINT "BOOK_PK" PRIMARY KEY ("IDBOOK")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "BOOKSTORE"."BOOK" MODIFY ("IDBOOK" NOT NULL ENABLE);
