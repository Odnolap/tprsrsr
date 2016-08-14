package ru.odnolap.tprstst.util;

import java.time.LocalDateTime;
import ru.odnolap.tprstst.model.Payment;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class PaymentUtil {

    public static final LocalDateTime MIN_DATE_TIME = LocalDateTime.of(0, 1, 1, 0, 0, 0);
    public static final LocalDateTime MAX_DATE_TIME = LocalDateTime.of(3000, 1, 1, 0, 0);

    public static final List<Payment> PAYMENT_LIST;

    static {
        Payment p1 = new Payment("1-12", 1, LocalDateTime.of(2016, Calendar.JULY, 15, 11, 23, 48), 166d);
        Payment p2 = new Payment("4-132", 2, LocalDateTime.of(2016, Calendar.JULY, 15, 15, 2, 26), 500d);
        Payment p3 = new Payment("1-16", 1, LocalDateTime.of(2016, Calendar.JULY, 16, 10, 12, 56), 1000.5d);

        p1.setRegistrationTime(LocalDateTime.of(2016, Calendar.JULY, 15, 11, 23, 55));
        p2.setRegistrationTime(LocalDateTime.of(2016, Calendar.JULY, 15, 15, 3, 1));
        p3.setRegistrationTime(LocalDateTime.of(2016, Calendar.JULY, 16, 10, 15, 22));

        PAYMENT_LIST = Arrays.asList(p1, p2, p3);
    }

    public static List<Payment> getPaymentList() {
        return PAYMENT_LIST;
    }


}
