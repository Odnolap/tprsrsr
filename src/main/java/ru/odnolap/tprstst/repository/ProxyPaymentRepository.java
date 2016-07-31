package ru.odnolap.tprstst.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.odnolap.tprstst.model.Payment;

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



}
