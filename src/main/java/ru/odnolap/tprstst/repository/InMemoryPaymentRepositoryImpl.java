package ru.odnolap.tprstst.repository;

import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;
import ru.odnolap.tprstst.model.Payment;
import ru.odnolap.tprstst.util.PaymentUtil;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
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
        Objects.requireNonNull(payment);
        if (payment.isNew()) {
            payment.setId(counter.incrementAndGet());
        }

        repository.put(payment.getId(), payment);
        return payment;
    }

    @Override
    public Collection<Payment> getFiltered(String productArticle, Integer contragentId,
                                           Double sumFrom, Double sumTo, Integer status,
                                           DateTime contragentDateFrom, DateTime contragentDateTo,
                                           DateTime registratioinDateFrom, DateTime RegistrationDateTo,
                                           DateTime autorisationDateFrom, DateTime autorisationDateTo) {
        // Пока что пусть возвращает все, не обращая внимание на значения фильтров
        return getAll();
    }

    @Override
    public void confirm(Payment payment, Double sum) {
        if (sum == null || !sum.equals(payment.getSum())) {
            throw new RuntimeException("Invalid payment sum!");
        }
        Objects.requireNonNull(payment);
        payment.setStatus(1);
        payment.setAutorisationTime(new DateTime());
        repository.put(payment.getId(), payment);
    }

    @Override
    public Payment get(Integer id) {
        Objects.requireNonNull(id);
        return repository.get(id);
    }
}
