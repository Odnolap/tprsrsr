package ru.odnolap.tprstst.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.odnolap.tprstst.message.PayMessageRequest;
import ru.odnolap.tprstst.message.PayMessageResponse;
import ru.odnolap.tprstst.message.PrepareMessageRequest;
import ru.odnolap.tprstst.message.PrepareMessageResponse;
import ru.odnolap.tprstst.model.Payment;
import ru.odnolap.tprstst.service.PaymentService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping(value = "/rest/payments")
public class PaymentRestController {

    @Autowired
    private PaymentService service;

    @RequestMapping(value = "/prepare", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PrepareMessageResponse prepare(@RequestBody PrepareMessageRequest prepareMessageRequest) {
        return new PrepareMessageResponse(service.save(prepareMessageRequest.toPayment()));
    }

    @RequestMapping(value = "/pay", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PayMessageResponse pay(@RequestBody PayMessageRequest payMessageRequest) {
        Payment payment = service.get(payMessageRequest.getPaymentId());
        return new PayMessageResponse(service.confirm(payment, payMessageRequest.getSum()));
    }

    @RequestMapping(value = "/prepareTest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PrepareMessageResponse prepareTest() {
        return new PrepareMessageResponse(service.save(new Payment("100-88", 22, LocalDateTime.now().minus(3L, ChronoUnit.SECONDS))));
    }

    @RequestMapping(value = "/payTest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PayMessageResponse payTest() {
        Payment payment = service.get(2);
        return new PayMessageResponse(service.confirm(payment, 322.48d));
    }
}
