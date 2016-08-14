Payment table with adding, confirming and searching.<br>
Project using java 1.8, Tomcat 8.<br>
Application context - /tprstst<br>
Web-interface (adding, confirming and searching) - localhost:8080/tprstst -> localhost:8080/tprstst/payments<br>
REST prepare (adding) - GET localhost:8080/tprstst/rest/payments/prepare?(article, contragent_id, contragent_time)<br>
REST pay (confirming) - GET localhost:8080/tprstst/rest/payments/pay?(payment_id, sum)<br>
Test requests in PaymentRestController.java file.<br>