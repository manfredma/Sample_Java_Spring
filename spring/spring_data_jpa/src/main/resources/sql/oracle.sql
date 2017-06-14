create table customer(
id number(10) primary key,
name varchar2(100),
code varchar2(100),
password varchar2(100),
type number(4),
address_id number(10),
detail_address varchar2(100),
first_name varchar2(100),
last_name varchar2(100),
sex number(4),
birth_day date,
parent_id number(10),
mem varchar2(100),
version number(10),
CREATE_ID number(10),
create_instant TIMESTAMP(6) WITH TIME ZONE default systimestamp,
modify_id      NUMBER(10),
modify_instant TIMESTAMP(6) WITH TIME ZONE default systimestamp,
transaction_id VARCHAR2(100),
server_name    VARCHAR2(100),
active_date_time TIMESTAMP(6) WITH TIME ZONE default systimestamp,
active_date_time2 TIMESTAMP(6) WITH TIME ZONE default systimestamp
);

-- Create/Recreate primary, unique and foreign key constraints
alter table CUSTOMER
  add constraint fk_customer_customer foreign key (PARENT_ID)
  references customer (ID);

-- Create sequence
create sequence SEQ_ID minvalue 1 maxvalue 9999999999 start with 1 increment by 1 nocache;
