DROP TABLE IF EXISTS payments;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS contragents;
DROP SEQUENCE IF EXISTS payment_id_seq;
DROP SEQUENCE IF EXISTS contragent_id_seq;

CREATE SEQUENCE payment_id_seq START 1;
CREATE SEQUENCE contragent_id_seq START 2 INCREMENT 2;

CREATE TABLE products
(
  article    VARCHAR NOT NULL,
  name       VARCHAR NOT NULL
);
CREATE UNIQUE INDEX products_unique_article_idx ON products (article);

CREATE TABLE contragents
(
  id    INTEGER PRIMARY KEY DEFAULT nextval('contragent_id_seq'),
  name  VARCHAR NOT NULL
);

CREATE TABLE payments
(
  id INTEGER  PRIMARY KEY DEFAULT nextval('payment_id_seq'),
  product_article VARCHAR NOT NULL,
  contragent_id INTEGER NOT NULL,
  contragent_time TIMESTAMP NOT NULL,
  summ NUMERIC NOT NULL,
  registration_time TIMESTAMP DEFAULT now(),
  authorization_time TIMESTAMP,
  status INTEGER DEFAULT 0,
  FOREIGN KEY (product_article) REFERENCES products (article) ON DELETE CASCADE,
  FOREIGN KEY (contragent_id) REFERENCES contragents (id) ON DELETE CASCADE
)