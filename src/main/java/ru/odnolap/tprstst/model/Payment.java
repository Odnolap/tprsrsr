package ru.odnolap.tprstst.model;

import org.joda.time.DateTime;


public class Payment {
    private Integer id; // id платежа
    private final String productArticle; // артикул товара
    private final Integer contragentId; // id контрагента
    private final DateTime contragentTime; // дата платежа контрагента
    private double sum; // сумма платежа
    private DateTime registrationTime; // дата регистрация платежа в системе
    private DateTime autorisationTime; // дата подтверждения платежа в системе
    private Integer status = 0; // статус платежа: 0 - зарегистрирован, 1 - подтвержден

    public Payment(String productArticle, Integer contragentId, DateTime contragentTime) {
        this.productArticle = productArticle;
        this.contragentId = contragentId;
        this.contragentTime = contragentTime;
    }

    public Payment(String productArticle, Integer contragentId, DateTime contragentTime, Double sum) {
        this.productArticle = productArticle;
        this.contragentId = contragentId;
        this.contragentTime = contragentTime;
        this.sum = sum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductArticle() {
        return productArticle;
    }

    public Integer getContragentId() {
        return contragentId;
    }

    public DateTime getContragentTime() {
        return contragentTime;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public DateTime getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(DateTime registrationTime) {
        this.registrationTime = registrationTime;
    }

    public DateTime getAutorisationTime() {
        return autorisationTime;
    }

    public void setAutorisationTime(DateTime autorisationTime) {
        this.autorisationTime = autorisationTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public boolean isNew() {
        return this.id == null;
    }
}
