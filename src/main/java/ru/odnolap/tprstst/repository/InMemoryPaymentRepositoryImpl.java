package ru.odnolap.tprstst.repository;

import java.time.LocalDateTime;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import ru.odnolap.tprstst.model.Payment;
import ru.odnolap.tprstst.util.PaymentUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryPaymentRepositoryImpl implements PaymentRepository {
    private Map<Integer, Payment> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        for (Payment p: PaymentUtil.getPaymentList()) {
            save(p);
        }
    }

    @Override
    public Collection<Payment> getAll() {
        return repository.values();
    }

    @Override
    public Payment save(Payment payment) {
        Assert.notNull(payment, "Payment must not be null");
        if (payment.isNew()) {
            payment.setId(counter.incrementAndGet());
        }

        repository.put(payment.getId(), payment);
        return payment;
    }

    @Override
    public Collection<Payment> getFiltered(String productArticle, Integer contragentId,
                                           Double sumFrom, Double sumTo, Integer status,
                                           LocalDateTime contragentDateFrom, LocalDateTime contragentDateTo,
                                           LocalDateTime registratioinDateFrom, LocalDateTime RegistrationDateTo,
                                           LocalDateTime authorizationDateFrom, LocalDateTime authorizationDateTo) {
        // Пока что пусть возвращает все, не обращая внимание на значения фильтров
        return getAll();
    }

    @Override
    public void confirm(Payment payment, Double sum) {
        if (sum == null || !sum.equals(payment.getSum())) {
            throw new RuntimeException("Invalid payment sum!");
        }
        Assert.notNull(payment, "Payment must not be null");
        payment.setStatus(1);
        payment.setAuthorizationTime(LocalDateTime.now());
        repository.put(payment.getId(), payment);
    }

    @Override
    public Payment get(Integer id) {
        Assert.notNull(id, "Payment id must not be null");
        return repository.get(id);
    }
}
