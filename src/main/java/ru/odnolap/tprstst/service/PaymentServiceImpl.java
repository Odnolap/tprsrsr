package ru.odnolap.tprstst.service;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.odnolap.tprstst.model.Payment;
import ru.odnolap.tprstst.repository.PaymentRepository;

import java.util.Collection;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    @Qualifier("springDataJpaPaymentRepositoryImpl")
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
                                           LocalDateTime contragentDateFrom, LocalDateTime contragentDateTo,
                                           LocalDateTime registratioinDateFrom, LocalDateTime RegistrationDateTo,
                                           LocalDateTime authorizationDateFrom, LocalDateTime authorizationDateTo) {
        return repository.getFiltered(productArticle, contragentId, sumFrom, sumTo, status,
                contragentDateFrom, contragentDateTo, registratioinDateFrom, RegistrationDateTo,
                authorizationDateFrom, authorizationDateTo);
    }
}
