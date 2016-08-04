package ru.odnolap.tprstst.message;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import ru.odnolap.tprstst.model.Payment;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

@JsonAutoDetect(fieldVisibility =  ANY, getterVisibility = NONE, isGetterVisibility = NONE, setterVisibility = NONE)
public class PrepareMessageResponse {
    private Integer paymentId;
    private Double sum;
    private LocalDateTime registrationTime;

    public PrepareMessageResponse() {
    }

    public PrepareMessageResponse(Payment payment){
        paymentId = payment.getId();
        sum = payment.getSum();
        registrationTime = payment.getRegistrationTime();
    }

}
