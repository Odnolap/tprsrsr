package ru.odnolap.tprstst.repository;

import ru.odnolap.tprstst.model.Payment;

import java.util.Collection;
import java.util.Date;

public interface PaymentRepository {
    Collection<Payment> getAll();

    Payment save(Payment payment);

    Collection<Payment> getFiltered(String productArticle, Integer contragentId, Double sumFram,
                                    Double sumTo, Integer status,
                                    Date contragentDateFrom, Date contragentDateTo,
                                    Date registratioinDateFrom, Date RegistrationDateTo,
                                    Date autorisationDateFrom, Date autorisationDateTo);

    void confirmPayment(Payment payment, Double sum);

    Payment get(Integer id);

}
