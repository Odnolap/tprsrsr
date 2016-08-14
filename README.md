Payment table with adding, confirming and searching.
Project using java 1.8, Tomcat 8.
Application context - /tprstst
Web-interface (adding, confirming and searching) - localhost:8080/tprstst -> localhost:8080/tprstst/payments
REST prepare (adding) - localhost:8080/tprstst/rest/payments/prepare?(article, contragent_id, contragent_time)
REST pay (confirming) - localhost:8080/tprstst/rest/payments/pay?(payment_id, sum)