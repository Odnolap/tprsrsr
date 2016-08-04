package ru.odnolap.tprstst.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.odnolap.tprstst.model.Payment;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface ProxyPaymentRepository extends JpaRepository<Payment, Integer> {
    @Override
    @Transactional
    Payment save(Payment payment);

    @Override
    Payment findOne(Integer id);

    @Override
    List<Payment> findAll(Sort sort);

    List<Payment> findByProductArticleLikeAndContragentIdBetweenAndSumBetweenAndStatusBetweenAndContragentTimeBetweenAndRegistrationTimeBetweenAndAuthorizationTimeBetweenOrderByRegistrationTimeDesc
            (String productArticle, Integer contragentIdFrom, Integer contragentIdTo,
             Double sumFrom, Double sumTo, Integer statusFrom, Integer statusTo,
             LocalDateTime contragentDateFrom, LocalDateTime contragentDateTo,
             LocalDateTime registratioinDateFrom, LocalDateTime RegistrationDateTo,
             LocalDateTime authorizationDateFrom, LocalDateTime authorizationDateTo);

    List<Payment> findByProductArticleLikeAndContragentIdBetweenAndSumBetweenAndStatusBetweenAndContragentTimeBetweenAndRegistrationTimeBetweenOrderByRegistrationTimeDesc
            (String productArticle, Integer contragentIdFrom, Integer contragentIdTo,
             Double sumFrom, Double sumTo, Integer statusFrom, Integer statusTo,
             LocalDateTime contragentDateFrom, LocalDateTime contragentDateTo,
             LocalDateTime registratioinDateFrom, LocalDateTime RegistrationDateTo);
}
