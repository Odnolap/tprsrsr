package ru.odnolap.tprstst.repository;

import ru.odnolap.tprstst.model.Payment;
import ru.odnolap.tprstst.util.PaymentUtil;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

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
        if (payment.isNew()) {
            payment.setId(counter.incrementAndGet());
        }

        repository.put(payment.getId(), payment);
        return payment;
    }

    @Override
    public Collection<Payment> getFiltered(String productArticle, Integer contragentId, Double sumFram, Double sumTo, Integer status, Date contragentDateFrom, Date contragentDateTo, Date registratioinDateFrom, Date RegistrationDateTo, Date autorisationDateFrom, Date autorisationDateTo) {
        // Пока что пусть возвращает все, не обращая внимание на значения фильтров
        return getAll();
    }

    @Override
    public void confirmPayment(Payment payment, Double sum) {
        if (sum == null || !sum.equals(payment.getSum())) {
            throw new RuntimeException("Invalid payment sum!");
        }
        payment.setStatus(1);
        repository.put(payment.getId(), payment);
    }

    @Override
    public Payment get(Integer id) {
        return repository.get(id);
    }
}
