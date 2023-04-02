package nus.iss.day24workshop.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import nus.iss.day24workshop.model.Neworder;
import nus.iss.day24workshop.model.Orderdetails;
import nus.iss.day24workshop.service.OrderService;

@Controller
@RequestMapping(path = "")
public class OrderController {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    OrderService orderService;

    @GetMapping(value = { "/", "/index" })
    public String newForm(HttpSession session) {
        session.invalidate();
        return "redirect:/neworder";
    }

    @GetMapping(value = { "/neworder" })
    public String showEmpty(Model model, HttpSession session) throws ParseException {

        Neworder neworder = (Neworder) session.getAttribute("neworder");
        if (null == neworder) {
            Date today = new Date();
            neworder = new Neworder(today, "", "", "");
            session.setAttribute("neworder", neworder);
        }
        model.addAttribute("neworder", neworder);

        @SuppressWarnings("unchecked")
        List<Orderdetails> orderlist = (List<Orderdetails>) session.getAttribute("orderlist");
        if (null == orderlist) {
            orderlist = new LinkedList<>();
        }
        model.addAttribute("orderlist", orderlist);
        return "neworder";
    }

    @PostMapping("/neworder")
    public String addOrder(@ModelAttribute("neworder") Neworder neworder,
            @RequestBody MultiValueMap<String, String> form, Model model, HttpSession session) throws ParseException {
        session.setAttribute("neworder", neworder);
        model.addAttribute("neworder", neworder);

        String product = form.getFirst("product");
        Float unitPrice = Float.parseFloat(form.getFirst("unitPrice"));
        Integer quantity = Integer.parseInt(form.getFirst("quantity"));
        Orderdetails orderdetails = new Orderdetails(product, unitPrice, quantity);

        @SuppressWarnings("unchecked")
        List<Orderdetails> orderlist = (List<Orderdetails>) session.getAttribute("orderlist");
        if (null == orderlist) {
            orderlist = new LinkedList<>();
        }
        orderlist.add(orderdetails);
        session.setAttribute("orderlist", orderlist);
        model.addAttribute("orderlist", orderlist);
        System.out.println(orderlist);

        return "neworder";
    }

    @PostMapping("order")
    public String submitOrder(HttpSession session) {
        Neworder neworder = (Neworder) session.getAttribute("neworder");
        @SuppressWarnings("unchecked")
        List<Orderdetails> orderlist = (List<Orderdetails>) session.getAttribute("orderlist");
        if (null == orderlist) {
            orderlist = new LinkedList<>();
        }
        Integer lastInsertedId = orderService.addOrder(neworder);

        for (Orderdetails orderdetails : orderlist) {
            Integer orderdetailsadded = orderService.addOrderDetails(lastInsertedId, orderdetails);
            System.out.println(orderdetailsadded + " order details added...");
        }

        return "success";
    }

}
