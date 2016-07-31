package ru.odnolap.tprstst.web;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.odnolap.tprstst.model.Payment;
import ru.odnolap.tprstst.service.PaymentService;

import java.util.Collection;

@Controller
public class PaymentRestController {

    @Autowired
    private PaymentService service;

    public Payment get(Integer id) {
        return service.get(id);
    }

    public Payment save(Payment payment) {
        return service.save(payment);
    }

    void confirm(Payment payment, Double sum) {
        service.confirm(payment, sum);
    }

    public Collection<Payment> getAll() {
        return service.getAll();
    }

    public Collection<Payment> getFiltered(String productArticle, Integer contragentId,
                                           Double sumFrom, Double sumTo, Integer status,
                                           DateTime contragentDateFrom, DateTime contragentDateTo,
                                           DateTime registratioinDateFrom, DateTime RegistrationDateTo,
                                           DateTime autorisationDateFrom, DateTime autorisationDateTo) {
        return service.getFiltered(productArticle, contragentId, sumFrom, sumTo, status,
                contragentDateFrom, contragentDateTo, registratioinDateFrom, RegistrationDateTo,
                autorisationDateFrom, autorisationDateTo);
    }
}
