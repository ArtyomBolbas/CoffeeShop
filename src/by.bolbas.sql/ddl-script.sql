/* SQL Command To Create Database */
CREATE DATABASE IF NOT EXISTS coffeeShop;

/* SQL Command To Use The Database */
USE coffeeShop;

/* DROP Any Exisiting Table In The Database With Name As "coffeeOrder" */
DROP TABLE IF EXISTS coffeeOrder;

/* SQL Command To Create The Table In A Database */
CREATE TABLE coffeeOrder (
	id int NOT NULL AUTO_INCREMENT,
	coffee_type VARCHAR(100) NOT NULL,
	delivery_type VARCHAR(100) NOT NULL,
	weight int NOT NULL,
	order_date date,
	start_time time,
	end_time time,
	total_cost int,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;