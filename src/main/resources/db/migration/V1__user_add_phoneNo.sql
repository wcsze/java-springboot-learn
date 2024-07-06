-- db/migration/V1_user_add_phoneNo.sql

ALTER TABLE users
ADD COLUMN phoneNo VARCHAR(20);
