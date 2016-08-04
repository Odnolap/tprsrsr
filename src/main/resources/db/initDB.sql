DROP TABLE IF EXISTS payments;
DROP SEQUENCE IF EXISTS payment_id_seq;

CREATE SEQUENCE payment_id_seq START 1;

CREATE TABLE payments
(
  id INTEGER  PRIMARY KEY DEFAULT nextval('payment_id_seq'),
  product_article VARCHAR(100) NOT NULL,
  contragent_id INTEGER NOT NULL,
  contragent_time TIMESTAMP NOT NULL,
  summ NUMERIC NOT NULL,
  registration_time TIMESTAMP DEFAULT now(),
  authorization_time TIMESTAMP,
  status INTEGER DEFAULT 0
)