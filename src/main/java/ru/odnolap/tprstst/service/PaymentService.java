package ru.odnolap.tprstst.service;

import org.joda.time.DateTime;
import ru.odnolap.tprstst.model.Payment;

import java.util.Collection;

/**
 * Created by Odnolap on 30.07.2016.
 */
public interface PaymentService {
    Payment save(Payment payment);

    void confirm(Payment payment, Double sum);

    Payment get(Integer id);

    Collection<Payment> getAll();

    Collection<Payment> getFiltered(String productArticle, Integer contragentId,
                                    Double sumFrom, Double sumTo, Integer status,
                                    DateTime contragentDateFrom, DateTime contragentDateTo,
                                    DateTime registratioinDateFrom, DateTime RegistrationDateTo,
                                    DateTime autorisationDateFrom, DateTime autorisationDateTo);
}
