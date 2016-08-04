<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Add payment</title>
    <%--
    pageContext.request.requestURL = http://localhost:8080/tprstst/WEB-INF/jsp/paymentAdd.jsp
    pageContext.request.requestURI = /tprstst/WEB-INF/jsp/paymentAdd.jsp
    pageContext.request.contextPath = /tprstst
    --%>
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
    <link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
<section>
    <h2><a href="${pageContext.request.contextPath}">Home</a> </h2>
    <h3>Add payment</h3>
    <hr>
    <jsp:useBean id="payment" type="ru.odnolap.tprstst.model.Payment" scope="request"/>
    <form method="post" action="payments">
        <dl>
            <dt>Артикул товара</dt>
            <dd><input type="text" value="${payment.productArticle}" size="20" name="productArticle"></dd>
        </dl>
        <dl>
            <dt>Контрагент (id, четное положительное значение)</dt>
            <dd><input type="number" min="2" step="2" value="${payment.contragentId}" name="contragentId"></dd>
        </dl>
        <dl>
            <dt>Время контрагента</dt>
            <dd><input type="datetime-local" value="${payment.contragentTime}" name="contragentTime"></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</section>
</body>
</html>
