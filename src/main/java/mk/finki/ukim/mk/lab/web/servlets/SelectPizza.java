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

@WebServlet(urlPatterns = "/selectPizza.do")
public class SelectPizza extends HttpServlet {

    private final SpringTemplateEngine templateEngine;

    public SelectPizza(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String selectedPizzaType = req.getParameter("pizza");
        Order order = new Order();
        order.setPizzaType(selectedPizzaType);
        HttpSession session = req.getSession();
        session.setAttribute("order", order);
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        webContext.setVariable("pizzaType", selectedPizzaType);
        this.templateEngine.process("selectPizzaSize", webContext, resp.getWriter());
    }
}
