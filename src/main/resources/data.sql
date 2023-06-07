insert into BANK(BANK_ID, NAME, DATE_CREATED) VALUES (1, 'Bank A', current_timestamp);
insert into BANK(BANK_ID, NAME, DATE_CREATED) VALUES (2, 'Bank B', current_timestamp);

insert into PRODUCT(PRODUCT_ID, BANK_ID_FK, NAME, FIXED_RATE, REDUCING_BALANCE, DATE_CREATED) values ( 1, 1, 'Bank A Product',  20.0, 22.0, CURRENT_TIMESTAMP);
insert into PRODUCT(PRODUCT_ID, BANK_ID_FK, NAME, FIXED_RATE, REDUCING_BALANCE, DATE_CREATED) values ( 2, 2, 'Bank B Product',  18.0, 25.0, CURRENT_TIMESTAMP);

insert into PRODUCT_CHARGES(PRODUCT_CHARGE_ID, NAME, CHARGE_VALUE, CHARGE_TYPE, PERCENTAGE_ON, CHARGE_ID, DATE_CREATED, PRODUCT_ID_FK) values ( 1, 'Processing Fee', 3.0, 'Percentage', 'Principal', null, current_timestamp, 1);
insert into PRODUCT_CHARGES(PRODUCT_CHARGE_ID, NAME, CHARGE_VALUE, CHARGE_TYPE, PERCENTAGE_ON, CHARGE_ID, DATE_CREATED, PRODUCT_ID_FK) values ( 2, 'Excise Duty', 20.0, 'Percentage', 'Charge', 1, current_timestamp, 1);
insert into PRODUCT_CHARGES(PRODUCT_CHARGE_ID, NAME, CHARGE_VALUE, CHARGE_TYPE, PERCENTAGE_ON, CHARGE_ID, DATE_CREATED, PRODUCT_ID_FK) values ( 3, 'Legal Fees', 10000.0, 'Fixed', null, null, current_timestamp, 1);

insert into PRODUCT_CHARGES(PRODUCT_CHARGE_ID, NAME, CHARGE_VALUE, CHARGE_TYPE, PERCENTAGE_ON, CHARGE_ID, DATE_CREATED, PRODUCT_ID_FK) values ( 4, 'Processing Fee', 3.0, 'Percentage', 'Principal', null, current_timestamp, 2);
insert into PRODUCT_CHARGES(PRODUCT_CHARGE_ID, NAME, CHARGE_VALUE, CHARGE_TYPE, PERCENTAGE_ON, CHARGE_ID, DATE_CREATED, PRODUCT_ID_FK) values ( 5, 'Excise Duty', 20.0, 'Percentage', 'Charge', 4, current_timestamp, 2);
insert into PRODUCT_CHARGES(PRODUCT_CHARGE_ID, NAME, CHARGE_VALUE, CHARGE_TYPE, PERCENTAGE_ON, CHARGE_ID, DATE_CREATED, PRODUCT_ID_FK) values ( 6, 'Legal Fees', 10000.0, 'Fixed', null, null, current_timestamp, 2);