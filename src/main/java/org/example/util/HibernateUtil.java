
package org.example.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {

            String dbPassword = System.getenv("DB_PASSWORD");

            if (dbPassword == null || dbPassword.trim().isEmpty()) {
                throw new IllegalStateException(
                        "DB_PASSWORD environment variable is not configured!"
                );
            }

            Configuration configuration = new Configuration().configure();

            configuration.setProperty(
                    "hibernate.connection.password",
                    dbPassword
            );

            sessionFactory = configuration.buildSessionFactory();
        }

        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory == null) return;

        sessionFactory.close();
        sessionFactory = null;
    }
}
