package ru.odnolap.tprstst.message;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.odnolap.tprstst.model.Payment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

@JsonAutoDetect(fieldVisibility =  ANY, getterVisibility = NONE, isGetterVisibility = NONE, setterVisibility = NONE)
public class PrepareMessageRequest {

    private String article;

    @JsonProperty("contragent_id")
    private Integer contragentId;

    @JsonProperty("contragent_time")
    private String contragentTime;

    public PrepareMessageRequest(){
    }

    public Payment toPayment() {
        return new Payment(article, contragentId, LocalDateTime.parse(contragentTime, DateTimeFormatter.ISO_DATE_TIME));
    }

}
