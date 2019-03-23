INSERT INTO Customers(fname, lname, passport_id) VALUES ('Petr', 'Hvostatiy', '1231231232');
INSERT INTO Customers(fname, lname, passport_id) VALUES ('Main', 'Helow', '1231231233');
INSERT INTO Customers(fname, lname, passport_id) VALUES ('Krutony', 'Kubansky', '1231231234');
INSERT INTO Customers(fname, lname, passport_id) VALUES ('Aleks', 'Hithow', '1231231235');
INSERT INTO Customers(fname, lname, passport_id) VALUES ('Misha', 'Marine', '1231231236');
INSERT INTO Customers(fname, lname, passport_id) VALUES ('John', 'Avolzon', '1231231237');
INSERT INTO Customers(fname, lname, passport_id) VALUES ('Liza', 'Ripova', '1231231238');
INSERT INTO Customers(fname, lname, passport_id) VALUES ('Anna', 'Nifnifova', '1231231239');
INSERT INTO Customers(fname, lname, passport_id) VALUES ('Sveta', 'Nafnafova', '3231231230');
INSERT INTO Customers(fname, lname, passport_id) VALUES ('Jenia', 'Nufnufova', '3231231231');

INSERT INTO Accounts(CUST_ID, name, balance) VALUES ('1', 'savings', '12000');
INSERT INTO Accounts(CUST_ID, name, balance) VALUES ('1', 'checkings', '9001');
INSERT INTO Accounts(CUST_ID, name, balance) VALUES ('2', 'savings', '120');
INSERT INTO Accounts(CUST_ID, name, balance) VALUES ('2', 'checkings', '121');
INSERT INTO Accounts(CUST_ID, name, balance) VALUES ('2', 'savings', '12200');
INSERT INTO Accounts(CUST_ID, name, balance) VALUES ('2', 'checkings', '777');

INSERT INTO Cards(id, name, uuid, pin) VALUES ('2', 'ddl card', '22330000', '9999');

--INSERT INTO Customer(fname, lname, passport_id) VALUES ('Jenia', 'Nufnufova', '3231231231');
--DELETE FROM Customer WHERE CUST_ID = 1;