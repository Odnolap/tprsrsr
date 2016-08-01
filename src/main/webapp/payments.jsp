<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Payments</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
<h2>Payments</h2>
<a href="payments?action=create">Add Payment</a>
<hr>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Товар</th>
        <th>Контрагент</th>
        <th>Сумма</th>
        <th>Статус</th>
        <th>Дата контрагента</th>
        <th>Дата регистрации в системе</th>
        <th>Дата подтверждения в системе</th>
        <th></th>
    </tr>
    </thead>
    <c:forEach items="${paymentList}" var="payment">
        <jsp:useBean id="payment" scope="page" type="ru.odnolap.tprstst.model.Payment"/>
        <tr class="string">
            <td>${payment.productArticle}</td>
            <td>${payment.contragentId}</td>
            <td>${payment.sum}</td>
            <td>${payment.status}</td>
            <td>${payment.contragentTime.toLocalDate()} ${payment.contragentTime.toLocalTime()}</td>
            <td>${payment.registrationTime.toLocalDate()} ${payment.registrationTime.toLocalTime()}</td>
            <td>${payment.authorizationTime.toLocalDate()} ${payment.authorizationTime.toLocalTime()}</td>
            <td><a href="payments?action=confirm&id=${payment.id}">Confirm</a></td>
        </tr>
    </c:forEach>
</table>
</section>
</body>
</html>
