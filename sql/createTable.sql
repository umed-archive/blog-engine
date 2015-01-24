DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS Product_group;


CREATE TABLE Product_group(

	pg_id SERIAL NOT NULL,

	pg_name VARCHAR(50) NOT NULL,


	CONSTRAINT pk_product_group PRIMARY KEY(pg_id)
);

CREATE TABLE Product(

	prod_id SERIAL NOT NULL,

	prod_name VARCHAR(70) NOT NULL,

	prod_maker VARCHAR(50) NOT NULL,

	prod_price MONEY NOT NULL,

	pg_id INTEGER NOT NULL,



	CONSTRAINT pk_product_id PRIMARY KEY(prod_id),

	CONSTRAINT fk_product_pg_id FOREIGN KEY(pg_id) REFERENCES Product_group(pg_id)
);

