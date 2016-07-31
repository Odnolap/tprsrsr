package ru.odnolap.tprstst.model;

import org.joda.time.LocalDateTime;


public class Payment {
    private Integer id; // id платежа
    private String productArticle; // артикул товара
    private Integer contragentId; // id контрагента
    private LocalDateTime contragentTime; // дата платежа контрагента
    private double sum; // сумма платежа
    private LocalDateTime registrationTime; // дата регистрация платежа в системе
    private LocalDateTime autorisationTime; // дата подтверждения платежа в системе
    private Integer status = 0; // статус платежа: 0 - зарегистрирован, 1 - подтвержден

    public Payment() {}

    public Payment(String productArticle, Integer contragentId, LocalDateTime contragentTime) {
        this.productArticle = productArticle;
        this.contragentId = contragentId;
        this.contragentTime = contragentTime;
    }

    public Payment(String productArticle, Integer contragentId, LocalDateTime contragentTime, Double sum) {
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

    public LocalDateTime getContragentTime() {
        return contragentTime;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public LocalDateTime getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(LocalDateTime registrationTime) {
        this.registrationTime = registrationTime;
    }

    public LocalDateTime getAutorisationTime() {
        return autorisationTime;
    }

    public void setAutorisationTime(LocalDateTime autorisationTime) {
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

    public void setProductArticle(String productArticle) {
        this.productArticle = productArticle;
    }

    public void setContragentId(Integer contragentId) {
        this.contragentId = contragentId;
    }

    public void setContragentTime(LocalDateTime contragentTime) {
        this.contragentTime = contragentTime;
    }
}
