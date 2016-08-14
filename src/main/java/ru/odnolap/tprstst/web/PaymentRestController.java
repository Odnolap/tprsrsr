package ru.odnolap.tprstst.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.odnolap.tprstst.message.PayMessageResponse;
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

    /*
    Проверочные вызовы (корректные):
    http://localhost:8080/tprstst/rest/payments/prepare?article=%D0%90%D1%80%D1%82%D0%B8%D0%BA%D1%83%D0%BB%201&contragent_id=34&contragent_time=2016-08-05T01:50:22
    http://localhost:8080/tprstst/rest/payments/prepare?article=309928-00&contragent_id=62&contragent_time=2016-08-05T01:48:11
    Проверочный вызов (некорректный - id контрагнте нечетное):
    http://localhost:8080/tprstst/rest/payments/prepare?article=309928-00&contragent_id=61&contragent_time=2016-08-05T01:48:11
     */
    @RequestMapping(value = "/prepare", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PrepareMessageResponse prepare(@RequestParam("article") String article, @RequestParam("contragent_id") Integer contragentId,
                                           @RequestParam("contragent_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime contragentTime) {
        return new PrepareMessageResponse(service.save(new Payment(article, contragentId, contragentTime)));
    }

    /*
    Проверочные вызовы (корректные):
    http://localhost:8080/tprstst/rest/payments/pay?payment_id=1&sum=584.12
    http://localhost:8080/tprstst/rest/payments/pay?payment_id=3&sum=122.03
    Проверочный вызов (некорректный - сумма некорректная):
    http://localhost:8080/tprstst/rest/payments/pay?payment_id=2&sum=222.22
    Проверочный вызов (некорректный - платеж уже подтвержден):
    http://localhost:8080/tprstst/rest/payments/pay?payment_id=4&sum=584.61
    Проверочный вызов (некорректный - несуществующий id платежа):
    http://localhost:8080/tprstst/rest/payments/pay?payment_id=8888&sum=584.61
     */
    @RequestMapping(value = "/pay", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PayMessageResponse pay(@RequestParam("payment_id") Integer id, @RequestParam("sum") Double sum) {
        return new PayMessageResponse(service.confirm(service.get(id), sum));
    }

    /*Тестирование REST-ответов*/
    @RequestMapping(value = "/prepareTest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PrepareMessageResponse prepareTest() {
        return new PrepareMessageResponse(service.save(new Payment("100-88", 22, LocalDateTime.now().minus(3L, ChronoUnit.SECONDS))));
    }

    @RequestMapping(value = "/payTest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PayMessageResponse payTest() {
        Payment payment = service.get(2);
        return new PayMessageResponse(service.confirm(payment, 322.48d));
    }

    /*Тестирование отображения кириллических символов*/
    @RequestMapping(value = "/cyrillicTextTest", method = RequestMethod.GET)
    public String testUTF() {
        return "Русский текст";
    }
}
