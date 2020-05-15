package mk.finki.ukim.mk.lab.web.filters;

import mk.finki.ukim.mk.lab.model.Order;
import org.springframework.context.annotation.Profile;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class PizzaTypeFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;

        if(!httpRequest.getRequestURI().endsWith("\\.do"))
            chain.doFilter(request, response);
        else if(httpRequest.getRequestURI().equals("/selectPizza.do")) {
            if (httpRequest.getParameter("pizza") != null)
                chain.doFilter(request, response);
            else
                httpResponse.sendRedirect("/");
        }
        else{
            Order sessionOrder = (Order)httpRequest.getSession().getAttribute("order");
            if(sessionOrder != null && sessionOrder.getPizzaType() != null)
                chain.doFilter(request, response);
            else
                httpResponse.sendRedirect("/");
        }
    }
}
