package ru.odnolap.tprstst.web;

import org.joda.time.DateTime;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.odnolap.tprstst.model.Payment;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PaymentServlet extends HttpServlet {

    private ConfigurableApplicationContext springContext;
    private PaymentRestController paymentController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        paymentController = springContext.getBean(PaymentRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Payment payment = new Payment(request.getParameter("productArticle"),
                Integer.parseInt(request.getParameter("contragentId")),
                DateTime.parse(request.getParameter("contragentTime")),
                Double.parseDouble(request.getParameter("sum")));
        payment.setRegistrationTime(new DateTime());
        paymentController.save(payment);
        response.sendRedirect("payments");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            request.setAttribute("paymentList", paymentController.getAll());
            request.getRequestDispatcher("/payments.jsp").forward(request, response);
        } else if ("create".equals(action)) {
            final Payment payment = new Payment("", null, new DateTime(), 0d);
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
