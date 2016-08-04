package ru.odnolap.tprstst.message;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

@JsonAutoDetect(fieldVisibility =  ANY, getterVisibility = NONE, isGetterVisibility = NONE, setterVisibility = NONE)
public class PayMessageRequest {
    private Integer paymentId;
    private Double sum;

    public PayMessageRequest() {
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public Double getSum() {
        return sum;
    }
}
