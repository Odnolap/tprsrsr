package ru.odnolap.tprstst.web;

import java.time.LocalDateTime;
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
                                           LocalDateTime contragentDateFrom, LocalDateTime contragentDateTo,
                                           LocalDateTime registratioinDateFrom, LocalDateTime RegistrationDateTo,
                                           LocalDateTime authorizationDateFrom, LocalDateTime authorizationDateTo) {
        return service.getFiltered(productArticle, contragentId, sumFrom, sumTo, status,
                contragentDateFrom, contragentDateTo, registratioinDateFrom, RegistrationDateTo,
                authorizationDateFrom, authorizationDateTo);
    }
}
