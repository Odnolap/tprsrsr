<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add payment</title>
    <style>
        dl {
            background: none repeat scroll 0 0 #FAFAFA;
            margin: 8px 0;
            padding: 0;
        }

        dt {
            display: inline-block;
            width: 170px;
        }

        dd {
            display: inline-block;
            margin-left: 8px;
            vertical-align: top;
        }
    </style>
</head>
<body>
<section>
    <h2><a href="">Home</a> </h2>
    <h3>Add payment</h3>
    <hr>
    <jsp:useBean id="payment" type="ru.odnolap.tprstst.model.Payment" scope="request"/>
    <form method="post" action="payments">
        <dl>
            <dt>Артикул товара</dt>
            <dd><input type="text" value="${payment.productArticle}" size="20" name="productArticle"></dd>
        </dl>
        <dl>
            <dt>Контрагент (id)</dt>
            <dd><input type="number" min="1" value="${payment.contragentId}" name="contragentId"></dd>
        </dl>
        <dl>
            <dt>Сумма</dt>
            <dd><input type="number" min="1" value="${payment.sum}" name="sum"></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</section>
</body>
</html>
