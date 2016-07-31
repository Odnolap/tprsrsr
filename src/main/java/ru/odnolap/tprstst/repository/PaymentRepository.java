package ru.odnolap.tprstst.repository;

import org.joda.time.LocalDateTime;
import ru.odnolap.tprstst.model.Payment;

import java.util.Collection;

public interface PaymentRepository {
    Collection<Payment> getAll();

    Payment save(Payment payment);

    Collection<Payment> getFiltered(String productArticle, Integer contragentId,
                                    Double sumFrom, Double sumTo, Integer status,
                                    LocalDateTime contragentDateFrom, LocalDateTime contragentDateTo,
                                    LocalDateTime registratioinDateFrom, LocalDateTime RegistrationDateTo,
                                    LocalDateTime autorisationDateFrom, LocalDateTime autorisationDateTo);

    void confirm(Payment payment, Double sum);

    Payment get(Integer id);

}
