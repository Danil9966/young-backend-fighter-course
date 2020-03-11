
DROP TABLE IF EXISTS DOGGIES;
CREATE TABLE DOGGIES (
  id INTEGER NOT NULL auto_increment primary key ,
  name varchar(255) NOT NULL default '',
  height double NOT NULL,
  weight double NOT NULL,
  age INTEGER ,
  dateOfBirth DATE NOT NULL,
  deleted bit default false
);

INSERT INTO DOGGIES (name, height, weight, dateOfBirth) VALUES
 ('Betty',20.0, 3.0, '2005-05-01'),
 ('Mr Pickles',20.0, 3.0, '2005-05-01'),
 ('Bim',20.0, 3.0, '1444-05-01');