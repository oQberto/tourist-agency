package by.travel.touristagency.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static volatile HibernateSessionFactoryUtil instance;

    public SessionFactory buildSessionFactory() {
        Configuration configuration = buildConfiguration();
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/travel_agency_repository");
        configuration.setProperty("hibernate.connection.username", "postgres");
        configuration.setProperty("hibernate.connection.password", "postgres");
        configuration.configure();

        return configuration.buildSessionFactory();
    }

    private Configuration buildConfiguration() {
        return new Configuration();

    }

    public static HibernateSessionFactoryUtil getInstance() {
        var result = instance;
        if (result != null) {
            return result;
        }
        synchronized (HibernateSessionFactoryUtil.class) {
            if (instance == null) {
                instance = new HibernateSessionFactoryUtil();
            }
            return instance;
        }
    }
}
