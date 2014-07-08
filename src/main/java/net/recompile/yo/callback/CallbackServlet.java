package net.recompile.yo.callback;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Callback", urlPatterns = {"/callback"})
public class CallbackServlet extends HttpServlet {
    @Resource(name = "persistence/entityManagerFactory")
    private EntityManagerFactory entityManagerFactory;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        if (username != null && username.length() > 0) {
            EntityManager manager = null;
            EntityTransaction transaction = null;

            try {
                manager = entityManagerFactory.createEntityManager();
                transaction = manager.getTransaction();

                transaction.begin();

                TypedQuery<Number> query = manager.createNamedQuery("Subscriber.countByName", Number.class)
                                                  .setParameter("name", username);
                if (query.getSingleResult().intValue() == 0) {
                    Subscriber subscriber = new Subscriber();
                    subscriber.setName(username);

                    manager.persist(subscriber);
                }

                transaction.commit();

                resp.setStatus(HttpServletResponse.SC_OK);
            } finally {
                if (manager != null && manager.isOpen()) {
                    manager.close();
                }
            }

        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
