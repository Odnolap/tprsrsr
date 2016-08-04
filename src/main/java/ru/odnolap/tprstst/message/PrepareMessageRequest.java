package ru.odnolap.tprstst.message;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import ru.odnolap.tprstst.model.Payment;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

@JsonAutoDetect(fieldVisibility =  ANY, getterVisibility = NONE, isGetterVisibility = NONE, setterVisibility = NONE)
public class PrepareMessageRequest {
    private String article;
    private Integer contragentId;
    private LocalDateTime contragentTime;

    public PrepareMessageRequest(){
    }

    public Payment toPayment() {
        return new Payment(article, contragentId, contragentTime);
    }

}
