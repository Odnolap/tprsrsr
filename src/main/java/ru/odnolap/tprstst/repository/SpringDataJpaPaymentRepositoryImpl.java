package ru.odnolap.tprstst.repository;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.odnolap.tprstst.model.Payment;

import java.util.Collection;

import static ru.odnolap.tprstst.util.PaymentUtil.MIN_DATE_TIME;
import static ru.odnolap.tprstst.util.PaymentUtil.MAX_DATE_TIME;

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
    @Transactional
    public Payment save(Payment payment) {
        return proxy.save(payment);
    }

    @Override
    public Collection<Payment> getFiltered(String productArticle, Integer contragentId,
                                           Double sumFrom, Double sumTo, Integer status,
                                           LocalDateTime contragentDateFrom, LocalDateTime contragentDateTo,
                                           LocalDateTime registratioinDateFrom, LocalDateTime registrationDateTo,
                                           LocalDateTime authorizationDateFrom, LocalDateTime authorizationDateTo) {
        String productArticlePattern = productArticle == null ? "%" : productArticle;
        Integer contragentIdFrom = contragentId == null ? 0 : contragentId;
        Integer contragentIdTo = contragentId == null ? 2000000000 : contragentId;
        Integer statusFrom = status == null ? 0 : status;
        Integer statusTo = status == null ? 1 : status;

        if (authorizationDateFrom == null && authorizationDateTo == null) { // Возвращаем платежи с любой датой подтверждения, в т.ч. с пустой
            return proxy.findByProductArticleLikeAndContragentIdBetweenAndSumBetweenAndStatusBetweenAndContragentTimeBetweenAndRegistrationTimeBetweenOrderByRegistrationTimeDesc
                    (productArticlePattern, contragentIdFrom, contragentIdTo, sumFrom, sumTo,
                            statusFrom, statusTo,
                            contragentDateFrom, contragentDateTo,
                            registratioinDateFrom, registrationDateTo);
        } else {
            if (authorizationDateFrom == null) {
                authorizationDateFrom = MIN_DATE_TIME;
            }
            if (authorizationDateTo == null) {
                authorizationDateTo = MAX_DATE_TIME;
            }
            return proxy.findByProductArticleLikeAndContragentIdBetweenAndSumBetweenAndStatusBetweenAndContragentTimeBetweenAndRegistrationTimeBetweenAndAuthorizationTimeBetweenOrderByRegistrationTimeDesc
                    (productArticlePattern, contragentIdFrom, contragentIdTo, sumFrom, sumTo,
                            statusFrom, statusTo,
                            contragentDateFrom, contragentDateTo,
                            registratioinDateFrom, registrationDateTo,
                            authorizationDateFrom, authorizationDateTo);
        }
    }

    @Override
    public Payment get(Integer id) {
        return proxy.findOne(id);
    }
}
