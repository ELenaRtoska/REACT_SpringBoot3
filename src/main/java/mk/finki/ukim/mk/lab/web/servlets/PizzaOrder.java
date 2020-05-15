package mk.finki.ukim.mk.lab.web.servlets;

import mk.finki.ukim.mk.lab.model.Order;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/pizzaOrder.do")
public class PizzaOrder extends HttpServlet {

    private final SpringTemplateEngine templateEngine;

    public PizzaOrder(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String selectedPizzaSize = req.getParameter("pizza_size");
        HttpSession session = req.getSession();
        Order order = (Order) session.getAttribute("order");
        order.setPizzaSize(selectedPizzaSize);
        session.setAttribute("order", order);
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        webContext.setVariable("pizzaType", order.getPizzaType());
        webContext.setVariable("pizzaSize", selectedPizzaSize);
        this.templateEngine.process("deliveryInfo", webContext, resp.getWriter());
    }

}
