package ru.odnolap.tprstst.message;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.odnolap.tprstst.model.Payment;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

@JsonAutoDetect(fieldVisibility =  ANY, getterVisibility = NONE, isGetterVisibility = NONE, setterVisibility = NONE)
public class PrepareMessageResponse {

    @JsonProperty("payment_id")
    private Integer paymentId;

    private Double sum;

    @JsonProperty("time")
    private String registrationTime;

    public PrepareMessageResponse() {
    }

    public PrepareMessageResponse(Payment payment){
        paymentId = payment.getId();
        sum = payment.getSum();
        registrationTime = payment.getRegistrationTime().toString().substring(0, 19);
    }

}
