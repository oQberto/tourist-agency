package by.travel.touristagency.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static volatile HibernateSessionFactoryUtil instance;

    public SessionFactory buildSessionFactory() {
        Configuration configuration = buildConfiguration();
        configuration.configure("hibernate.cfg.xml");

        return configuration.buildSessionFactory();
    }

    private Configuration buildConfiguration() {
        return new Configuration();
    }

    public static HibernateSessionFactoryUtil getInstance() {
        var result = instance;
        if(result != null) {
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
