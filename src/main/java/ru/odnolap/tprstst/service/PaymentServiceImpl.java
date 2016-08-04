package ru.odnolap.tprstst.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.odnolap.tprstst.model.Payment;
import ru.odnolap.tprstst.repository.PaymentRepository;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    @Qualifier("springDataJpaPaymentRepositoryImpl")
    private PaymentRepository repository;

    @Override
    public Payment save(Payment payment) {
        if (payment == null
                || payment.getProductArticle().length() > 100
                || payment.getContragentId() % 2 == 1) {
            throw new RuntimeException("Saving error!");
        }
        if (payment.getSum() == null) {
            // Здесь должна доставаться сумма платежа по артиклу, но мы сделаем ее случайной величиной от 100 до 100 000
            BigDecimal bd = new BigDecimal(ThreadLocalRandom.current().nextDouble(100d, 100000d));
            payment.setSum(bd.setScale(2, RoundingMode.HALF_UP).doubleValue());
        }
        payment.setRegistrationTime(LocalDateTime.now());
        return repository.save(payment);
    }

    @Override
    public LocalDateTime confirm(Payment payment, Double sum) {
        if (sum == null
                || payment == null
                || payment.getSum() == null
                || Math.abs(sum - payment.getSum()) > 0.0001
                || payment.getStatus() == 1) {
            throw new RuntimeException("Confirm error!");
        }
        payment.setStatus(1);
        LocalDateTime authorizationTime = LocalDateTime.now();
        payment.setAuthorizationTime(authorizationTime);
        repository.save(payment);
        return authorizationTime;
    }

    @Override
    public Payment get(Integer id) {
        Assert.notNull(id, "Payment id must not be null");
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
