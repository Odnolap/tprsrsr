package ru.odnolap.tprstst.repository;

import org.joda.time.DateTime;
import ru.odnolap.tprstst.model.Payment;

import java.util.Collection;

public interface PaymentRepository {
    Collection<Payment> getAll();

    Payment save(Payment payment);

    Collection<Payment> getFiltered(String productArticle, Integer contragentId,
                                    Double sumFrom, Double sumTo, Integer status,
                                    DateTime contragentDateFrom, DateTime contragentDateTo,
                                    DateTime registratioinDateFrom, DateTime RegistrationDateTo,
                                    DateTime autorisationDateFrom, DateTime autorisationDateTo);

    void confirm(Payment payment, Double sum);

    Payment get(Integer id);

}
