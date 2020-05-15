package mk.finki.ukim.mk.lab.web.servlets;

import mk.finki.ukim.mk.lab.service.PizzaService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "show-pizza", urlPatterns = "")
public class ShowPizza extends HttpServlet {

    private final PizzaService pizzaService;
    private final SpringTemplateEngine templateEngine;

    public ShowPizza(PizzaService pizzaService, SpringTemplateEngine templateEngine) {
        this.pizzaService = pizzaService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("pizzas", this.pizzaService.getAll());
        this.templateEngine.process("listPizzas", context, resp.getWriter());
    }
}
