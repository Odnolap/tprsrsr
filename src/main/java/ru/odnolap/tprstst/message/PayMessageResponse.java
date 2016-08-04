package ru.odnolap.tprstst.message;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

@JsonAutoDetect(fieldVisibility =  ANY, getterVisibility = NONE, isGetterVisibility = NONE, setterVisibility = NONE)
public class PayMessageResponse {
    @JsonProperty("time")
    private String authorizationTime;

    public PayMessageResponse(){
    }

    public PayMessageResponse(LocalDateTime authorizationTime) {
        this.authorizationTime = authorizationTime.toString().substring(0, 19);
    }

}
