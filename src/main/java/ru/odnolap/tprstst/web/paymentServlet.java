package ru.odnolap.tprstst.web;

import ru.odnolap.tprstst.model.Payment;
import ru.odnolap.tprstst.repository.InMemoryPaymentRepositoryImpl;
import ru.odnolap.tprstst.repository.PaymentRepository;
import ru.odnolap.tprstst.util.PaymentUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Odnolap on 30.07.2016.
 */
public class paymentServlet extends HttpServlet {
    private PaymentRepository repository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        repository = new InMemoryPaymentRepositoryImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Payment payment = new Payment(request.getParameter("productArticle"),
                Integer.parseInt(request.getParameter("contragentId")),
                new Date(),
                Double.parseDouble(request.getParameter("sum")));
        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, 2);
        payment.setRegistrationTime(c.getTime());
        repository.save(payment);
        response.sendRedirect("payments");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            request.setAttribute("paymentList", repository.getAll());
            request.getRequestDispatcher("/payments.jsp").forward(request, response);
        } else if ("create".equals(action)) {
            final Payment payment = new Payment("", null, new Date());
            request.setAttribute("payment", payment);
            request.getRequestDispatcher("paymentAdd.jsp").forward(request, response);
        } else if ("confirm".equals(action)) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            Payment payment = repository.get(id);
            repository.confirmPayment(payment, payment.getSum());
            response.sendRedirect("meals");
        }
    }
}
