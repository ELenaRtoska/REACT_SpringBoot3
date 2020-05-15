package mk.finki.ukim.mk.lab.listeners;

import org.springframework.context.event.EventListener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionCreationListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("[WP-LOG] HttpSessionListener.sessionCreated()");
    }
}
