package ru.odnolap.tprstst.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.odnolap.tprstst.model.Payment;
import ru.odnolap.tprstst.service.PaymentService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static ru.odnolap.tprstst.util.PaymentUtil.MAX_DATE_TIME;
import static ru.odnolap.tprstst.util.PaymentUtil.MIN_DATE_TIME;

@Controller
@RequestMapping(value = "/payments")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @RequestMapping(method = RequestMethod.GET)
    public String paymentList(Model model) {
        model.addAttribute("paymentList", service.getAll());
        return "paymentList";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String paymentAdd(Model model) {
        model.addAttribute("payment", new Payment("", null, LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)));
        return "paymentAdd";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(HttpServletRequest request) {
        Payment payment = new Payment(request.getParameter("productArticle"),
                Integer.parseInt(request.getParameter("contragentId")),
                LocalDateTime.parse(request.getParameter("contragentTime")));
        service.save(payment);
        return "redirect:/payments";
    }

    @RequestMapping(value = "/confirm")
    public String paymentConfirm(HttpServletRequest request) {
        String s = request.getParameter("id");
        Assert.notNull(s, "Confirm error!");
        Payment payment = service.get(Integer.parseInt(s));
        service.confirm(payment, payment.getSum());
        return "redirect:/payments";
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public String getBetween(HttpServletRequest request, Model model) {
        String s;
        String productArticle = (s = getParam("productArticle", request)).isEmpty() ? null : s;
        Integer contragentId = (s = getParam("contragentId", request)).isEmpty() ? null : Integer.parseInt(s);
        Double sumFrom = (s = getParam("sumFrom", request)).isEmpty() ? 1d : Double.parseDouble(s);
        Double sumTo = (s = getParam("sumTo", request)).isEmpty() ? 9999999999d : Double.parseDouble(s);
        Integer status = (s = getParam("status", request)).isEmpty() ? null : Integer.parseInt(s);
        LocalDateTime contragentTimeFrom = (s = getParam("contragentTimeFrom", request)).isEmpty() ? MIN_DATE_TIME : LocalDateTime.parse(s);
        LocalDateTime contragentTimeTo = (s = getParam("contragentTimeTo", request)).isEmpty() ? MAX_DATE_TIME : LocalDateTime.parse(s);
        LocalDateTime registrationTimeFrom = (s = getParam("registrationTimeFrom", request)).isEmpty() ? MIN_DATE_TIME : LocalDateTime.parse(s);
        LocalDateTime registrationTimeTo = (s = getParam("registrationTimeTo", request)).isEmpty() ? MAX_DATE_TIME : LocalDateTime.parse(s);
        LocalDateTime authorizationTimeFrom = (s = getParam("authorizationTimeFrom", request)).isEmpty() ? null : LocalDateTime.parse(s);
        LocalDateTime authorizationTimeTo = (s = getParam("authorizationTimeTo", request)).isEmpty() ? null : LocalDateTime.parse(s);
        model.addAttribute("paymentList", service.getFiltered(productArticle, contragentId, sumFrom, sumTo, status,
                contragentTimeFrom, contragentTimeTo, registrationTimeFrom, registrationTimeTo, authorizationTimeFrom, authorizationTimeTo));
        return "paymentList";
    }

    private String getParam(String param, HttpServletRequest request) {
        String value = request.getParameter(param);
        request.setAttribute(param, value);
        return value;
    }
}
