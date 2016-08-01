package ru.odnolap.tprstst.web;

import java.time.LocalDateTime;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.odnolap.tprstst.model.Payment;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.odnolap.tprstst.util.PaymentUtil.MIN_DATE_TIME;
import static ru.odnolap.tprstst.util.PaymentUtil.MAX_DATE_TIME;

public class PaymentServlet extends HttpServlet {

    private ConfigurableApplicationContext springContext;
    private PaymentRestController paymentController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        paymentController = springContext.getBean(PaymentRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            Payment payment = new Payment(request.getParameter("productArticle"),
                    Integer.parseInt(request.getParameter("contragentId")),
                    LocalDateTime.parse(request.getParameter("contragentTime")),
                    Double.parseDouble(request.getParameter("sum")));
            payment.setRegistrationTime(LocalDateTime.now());
            paymentController.save(payment);
            response.sendRedirect("payments");
        } else if ("filter".equals(action)) {
            String s;
            String productArticle = (s = request.getParameter("productArticle")).isEmpty() ? null : s;
            Integer contragentId = (s = request.getParameter("contragentId")).isEmpty() ? null : Integer.parseInt(s);
            Double sumFrom = (s = request.getParameter("sumFrom")).isEmpty() ? 1d : Double.parseDouble(s);
            Double sumTo = (s = request.getParameter("sumTo")).isEmpty() ? 9999999999d : Double.parseDouble(s);
            Integer status = (s = request.getParameter("status")).isEmpty() ? null : Integer.parseInt(s);
            LocalDateTime contragentTimeFrom = (s = request.getParameter("contragentTimeFrom")).isEmpty() ? MIN_DATE_TIME : LocalDateTime.parse(s);
            LocalDateTime contragentTimeTo = (s = request.getParameter("contragentTimeTo")).isEmpty() ? MAX_DATE_TIME : LocalDateTime.parse(s);
            LocalDateTime registrationTimeFrom = (s = request.getParameter("registrationTimeFrom")).isEmpty() ? MIN_DATE_TIME : LocalDateTime.parse(s);
            LocalDateTime registrationTimeTo = (s = request.getParameter("registrationTimeTo")).isEmpty() ? MAX_DATE_TIME : LocalDateTime.parse(s);
            LocalDateTime authorizationTimeFrom = (s = request.getParameter("authorizationTimeFrom")).isEmpty() ? null : LocalDateTime.parse(s);
            LocalDateTime authorizationTimeTo = (s = request.getParameter("authorizationTimeTo")).isEmpty() ? null : LocalDateTime.parse(s);
            request.setAttribute("paymentList", paymentController.getFiltered(productArticle, contragentId, sumFrom, sumTo, status,
                    contragentTimeFrom, contragentTimeTo, registrationTimeFrom, registrationTimeTo, authorizationTimeFrom, authorizationTimeTo));
            request.getRequestDispatcher("/payments.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            request.setAttribute("paymentList", paymentController.getAll());
            request.getRequestDispatcher("/payments.jsp").forward(request, response);
        } else if ("create".equals(action)) {
            final Payment payment = new Payment("", null, LocalDateTime.now(), 0d);
            request.setAttribute("payment", payment);
            request.getRequestDispatcher("paymentAdd.jsp").forward(request, response);
        } else if ("confirm".equals(action)) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            Payment payment = paymentController.get(id);
            paymentController.confirm(payment, payment.getSum());
            response.sendRedirect("payments");
        }
    }
}
