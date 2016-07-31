DELETE FROM payments;
DELETE FROM contragents;
DELETE FROM products;
ALTER SEQUENCE payment_id_seq RESTART WITH 1;
ALTER SEQUENCE contragent_id_seq RESTART WITH 2;

INSERT INTO contragents (name) VALUES
  ('Контрагент номер 2'),
  ('Контрагент номер 4'),
  ('Контрагент номер 6');

INSERT INTO products (article, name) VALUES
  ('100-1', 'Товар номер 1'),
  ('100-2', 'Товар номер 2'),
  ('100-3', 'Товар номер 3'),
  ('100-4', 'Товар номер 4'),
  ('100-5', 'Товар номер 5'),
  ('100-6', 'Товар номер 6'),
  ('100-7', 'Товар номер 7'),
  ('100-8', 'Товар номер 8'),
  ('100-9', 'Товар номер 9');

INSERT INTO payments
 (product_article, contragent_id, contragent_time, summ, registration_time) VALUES
  ('100-8', 4, TIMESTAMP '2016-07-30 22:31:46', 584, TIMESTAMP '2016-07-30 22:34:01'),
  ('100-1', 2, TIMESTAMP '2016-07-30 21:08:15', 322.48, TIMESTAMP '2016-07-30 21:10:33'),
  ('100-3', 2, TIMESTAMP '2016-07-30 23:11:52', 122, TIMESTAMP '2016-07-30 23:14:29');


