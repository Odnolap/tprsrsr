package ru.odnolap.tprstst.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @SequenceGenerator(name = "payment_id_seq", sequenceName = "payment_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_id_seq")
    private Integer id; // id платежа

    @NotEmpty
    @Column(name = "product_article", nullable = false)
    private String productArticle; // артикул товара

    @NotNull
    @Column(name = "contragent_id", nullable = false)
    private Integer contragentId; // id контрагента

    @NotNull
    @Column(name = "contragent_time", nullable = false)
    private LocalDateTime contragentTime; // дата платежа контрагента

    @NotNull
    @Column(name = "summ")
    @Digits(fraction = 2, integer = 10)
    private Double sum; // сумма платежа

    @NotNull
    @Column(name = "registration_time")
    private LocalDateTime registrationTime; // дата регистрация платежа в системе

    @Column(name = "authorization_time")
    private LocalDateTime authorizationTime; // дата подтверждения платежа в системе

    @NotNull
    @Column(name = "status")
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

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public LocalDateTime getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(LocalDateTime registrationTime) {
        this.registrationTime = registrationTime;
    }

    public LocalDateTime getAuthorizationTime() {
        return authorizationTime;
    }

    public void setAuthorizationTime(LocalDateTime authorizationTime) {
        this.authorizationTime = authorizationTime;
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
