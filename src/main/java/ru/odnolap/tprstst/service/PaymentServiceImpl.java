package ru.odnolap.tprstst.service;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.odnolap.tprstst.model.Payment;
import ru.odnolap.tprstst.repository.PaymentRepository;

import java.util.Collection;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository repository;

    @Override
    public Payment save(Payment payment) {
        return repository.save(payment);
    }

    @Override
    public void confirm(Payment payment, Double sum) {
        repository.confirm(payment, sum);
    }

    @Override
    public Payment get(Integer id) {
        return repository.get(id);
    }

    @Override
    public Collection<Payment> getAll() {
        return repository.getAll();
    }

    @Override
    public Collection<Payment> getFiltered(String productArticle, Integer contragentId,
                                           Double sumFrom, Double sumTo, Integer status,
                                           DateTime contragentDateFrom, DateTime contragentDateTo,
                                           DateTime registratioinDateFrom, DateTime RegistrationDateTo,
                                           DateTime autorisationDateFrom, DateTime autorisationDateTo) {
        return repository.getFiltered(productArticle, contragentId, sumFrom, sumTo, status,
                contragentDateFrom, contragentDateTo, registratioinDateFrom, RegistrationDateTo,
                autorisationDateFrom, autorisationDateTo);
    }
}
