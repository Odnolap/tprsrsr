package ru.odnolap.tprstst.repository;

import java.time.LocalDateTime;
import ru.odnolap.tprstst.model.Payment;

import java.util.Collection;

public interface PaymentRepository {
    Collection<Payment> getAll();

    Payment save(Payment payment);

    Collection<Payment> getFiltered(String productArticle, Integer contragentId,
                                    Double sumFrom, Double sumTo, Integer status,
                                    LocalDateTime contragentDateFrom, LocalDateTime contragentDateTo,
                                    LocalDateTime registratioinDateFrom, LocalDateTime registrationDateTo,
                                    LocalDateTime authorizationDateFrom, LocalDateTime authorizationDateTo);

    Payment get(Integer id);

}
