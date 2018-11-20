/* SQL Command To Create Database */
CREATE DATABASE IF NOT EXISTS coffeeShop;

/* SQL Command To Use The Database */
USE coffeeShop;

/* DROP Any Exisiting Table In The Database With Name As "coffeeOrder" */
DROP TABLE IF EXISTS coffeeOrder;

/* DROP Any Exisiting Table In The Database With Name As "coffee" */
DROP TABLE IF EXISTS coffee;

/* SQL Command To Create The Table In A Database */
CREATE TABLE coffee (
	id int NOT NULL AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	price int,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/* DROP Any Exisiting Table In The Database With Name As "delivery" */
DROP TABLE IF EXISTS delivery;

/* SQL Command To Create The Table In A Database */
CREATE TABLE delivery (
	id int NOT NULL AUTO_INCREMENT,
	type VARCHAR(30) NOT NULL,
	price int,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/* SQL Command To Create The Table In A Database */
CREATE TABLE coffeeOrder (
	id int NOT NULL AUTO_INCREMENT,
	coffee_id int NOT NULL,
	delivery_id int NOT NULL,
	weight int NOT NULL,
	order_date date,
	start_time time,
	end_time time,
	total_cost int,
	PRIMARY KEY (id),
	FOREIGN KEY (coffee_id) REFERENCES coffee(id),
	FOREIGN KEY (delivery_id) REFERENCES delivery(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO coffee(name, price) VALUES ('Arabica', 10);
INSERT INTO coffee(name, price) VALUES ('Canephora', 15);
INSERT INTO coffee(name, price) VALUES ('Liberica', 20);

INSERT INTO delivery(type, price) VALUES ('Without delivery', 0);
INSERT INTO delivery(type, price) VALUES ('With delivery', 5);



