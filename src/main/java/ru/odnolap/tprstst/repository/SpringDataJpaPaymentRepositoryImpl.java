package ru.odnolap.tprstst.repository;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import ru.odnolap.tprstst.model.Payment;

import java.util.Collection;

@Repository
public class SpringDataJpaPaymentRepositoryImpl implements PaymentRepository {
    private final static Sort SORT_REGISTRATION_TIME_DESC = new Sort(Sort.Direction.DESC, "registrationTime");

    @Autowired
    private ProxyPaymentRepository proxy;

    @Override
    public Collection<Payment> getAll() {
        return proxy.findAll(SORT_REGISTRATION_TIME_DESC);
    }

    @Override
    public Payment save(Payment payment) {
        Assert.notNull(payment, "Payment must not be null");
        return proxy.save(payment);
    }

    @Override
    public Collection<Payment> getFiltered(String productArticle, Integer contragentId,
                                           Double sumFrom, Double sumTo, Integer status,
                                           LocalDateTime contragentDateFrom, LocalDateTime contragentDateTo,
                                           LocalDateTime registratioinDateFrom, LocalDateTime RegistrationDateTo,
                                           LocalDateTime authorizationDateFrom, LocalDateTime authorizationDateTo) {
        return getAll(); // TODO !!!
    }

    @Override
    public void confirm(Payment payment, Double sum) {
        if (sum == null || !sum.equals(payment.getSum())) {
            throw new RuntimeException("Invalid payment sum!");
        }
        Assert.notNull(payment, "Payment must not be null");
        payment.setStatus(1);
        payment.setAuthorizationTime(LocalDateTime.now());
        proxy.save(payment);

    }

    @Override
    public Payment get(Integer id) {
        return proxy.findOne(id);
    }
}
