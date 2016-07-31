DROP TABLE IF EXISTS payments;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS contragents;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 1;

CREATE TABLE products
(
  article    VARCHAR NOT NULL,
  name       VARCHAR NOT NULL
);
CREATE UNIQUE INDEX products_unique_article_idx ON products (article);

CREATE TABLE contragents
(
  id    INTEGER PRIMARY KEY,
  name  VARCHAR NOT NULL
);

CREATE TABLE payments
(
  id INTEGER  PRIMARY KEY DEFAULT nextval('global_seq'),
  product_article VARCHAR NOT NULL,
  contragent_id INTEGER NOT NULL,
  contragent_time TIMESTAMP NOT NULL,
  summ NUMERIC NOT NULL,
  registration_time TIMESTAMP DEFAULT now(),
  autorisation_time TIMESTAMP,
  status INTEGER DEFAULT 0,
  FOREIGN KEY (product_article) REFERENCES products (article) ON DELETE CASCADE,
  FOREIGN KEY (contragent_id) REFERENCES contragents (id) ON DELETE CASCADE
)