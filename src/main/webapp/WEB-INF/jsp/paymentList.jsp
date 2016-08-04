<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Payments</title>
    <%--
    pageContext.request.requestURL = http://localhost:8080/tprstst/WEB-INF/jsp/paymentList.jsp
    pageContext.request.requestURI = /tprstst/WEB-INF/jsp/paymentList.jsp
    pageContext.request.contextPath = /tprstst
    --%>
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
    <link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
<section>
<h2>Payments</h2>
    <form method="post" action="payments/filter">
        <dl>
            <dt>Артикул товара:</dt>
            <dd><input type="text" name="productArticle" value="${productArticle}"></dd>
        </dl>
        <dl>
            <dt>id Контрагента:</dt>
            <dd><input type="number" name="contragentId" value="${contragentId}"></dd>
        </dl>
        <dl>
            <dt>Сумма от:</dt>
            <dd><input type="number" step="0.01" name="sumFrom" value="${sumFrom}"></dd>
        </dl>
        <dl>
            <dt>Сумма до:</dt>
            <dd><input type="number" step="0.01" name="sumTo" value="${sumTo}"></dd>
        </dl>
        <dl>
            <dt>Статус (0 или 1):</dt>
            <dd><input type="number" step="1" min="0" max="1" name="status" value="${status}"></dd>
        </dl>
        <dl>
            <dt>Дата контрагента от:</dt>
            <dd><input type="datetime-local" name="contragentTimeFrom" value="${contragentTimeFrom}"></dd>
        </dl>
        <dl>
            <dt>Дата контрагента до:</dt>
            <dd><input type="datetime-local" name="contragentTimeTo" value="${contragentTimeTo}"></dd>
        </dl>
        <dl>
            <dt>Дата регистрации от:</dt>
            <dd><input type="datetime-local" name="registrationTimeFrom" value="${registrationTimeFrom}"></dd>
        </dl>
        <dl>
            <dt>Дата регистрации до:</dt>
            <dd><input type="datetime-local" name="registrationTimeTo" value="${registrationTimeTo}"></dd>
        </dl>
        <dl>
            <dt>Дата подтверждения от:</dt>
            <dd><input type="datetime-local" name="authorizationTimeFrom" value="${authorizationTimeFrom}"></dd>
        </dl>
        <dl>
            <dt>Дата подтверждения до:</dt>
            <dd><input type="datetime-local" name="authorizationTimeTo" value="${authorizationTimeTo}"></dd>
        </dl>
        <button type="submit">Filter</button>
    </form>
    <hr>
<a href="payments/create">Add Payment</a>
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

        <tr class="${payment.status == 1 ? 'authorized' : 'registred'}">
            <td>${payment.productArticle}</td>
            <td>${payment.contragentId}</td>
            <td>${payment.sum}</td>
            <td>${payment.status}</td>
            <td>${payment.contragentTime.toLocalDate()} ${payment.contragentTime.toLocalTime()}</td>
            <td>${payment.registrationTime.toLocalDate()} ${payment.registrationTime.toLocalTime()}</td>
            <td>${payment.authorizationTime.toLocalDate()} ${payment.authorizationTime.toLocalTime()}</td>
            <td><a href="payments/confirm?id=${payment.id}">Confirm</a></td>
        </tr>
    </c:forEach>
</table>
</section>
</body>
</html>
