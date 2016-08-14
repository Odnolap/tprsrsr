package ru.odnolap.tprstst.service;

import java.time.LocalDateTime;
import ru.odnolap.tprstst.model.Payment;

import java.util.Collection;

public interface PaymentService {
    Payment save(Payment payment);

    LocalDateTime confirm(Payment payment, Double sum);

    Payment get(Integer id);

    Collection<Payment> getAll();

    Collection<Payment> getFiltered(String productArticle, Integer contragentId,
                                    Double sumFrom, Double sumTo, Integer status,
                                    LocalDateTime contragentDateFrom, LocalDateTime contragentDateTo,
                                    LocalDateTime registratioinDateFrom, LocalDateTime registrationDateTo,
                                    LocalDateTime authorizationDateFrom, LocalDateTime authorizationDateTo);
}
