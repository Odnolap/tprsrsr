DELETE FROM payments;
ALTER SEQUENCE payment_id_seq RESTART WITH 1;

INSERT INTO payments
 (product_article, contragent_id, contragent_time, summ, registration_time) VALUES
  ('100-8', 4, TIMESTAMP '2016-07-30 22:31:46', 584, TIMESTAMP '2016-07-30 22:34:01'),
  ('100-1', 2, TIMESTAMP '2016-07-30 21:08:15', 322.48, TIMESTAMP '2016-07-30 21:10:33'),
  ('100-3', 2, TIMESTAMP '2016-07-30 23:11:52', 122, TIMESTAMP '2016-07-30 23:14:29');

INSERT INTO payments
(product_article, contragent_id, contragent_time, summ, registration_time, status, authorization_time) VALUES
  ('100-5', 6, TIMESTAMP '2016-07-31 22:15:11', 584, TIMESTAMP '2016-07-31 22:16:10', 1, TIMESTAMP '2016-07-31 22:16:42');


