package mk.finki.ukim.mk.lab.web.servlets;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/ConfirmationInfo.do")
public class ConfirmationInfo extends HttpServlet {

    private final OrderService orderService;
    private final SpringTemplateEngine templateEngine;

    public ConfirmationInfo(OrderService orderService, SpringTemplateEngine templateEngine) {
        this.orderService = orderService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("clientName");
        String address = req.getParameter("clientAddress");

        Order fromSession = (Order)req.getSession().getAttribute("order");

        Order orderToDisplay = orderService.placeOrder(fromSession.getPizzaType(), fromSession.getPizzaSize(), name, address);
        req.getSession().setAttribute("order", orderToDisplay);

        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        webContext.setVariable("order", orderToDisplay);
        webContext.setVariable("clientIP", req.getRemoteAddr());
        webContext.setVariable("browser", req.getHeader("User-Agent"));
        this.templateEngine.process("confirmationInfo", webContext, resp.getWriter());
    }
}
