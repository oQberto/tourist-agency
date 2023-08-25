package by.travel.touristagency.util;

import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;


@UtilityClass
public class HibernateTestUtil {
    private static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:13");

    static {
        POSTGRES.start();
    }

    public static SessionFactory buildSessionFactory() {
        Configuration configuration = buildConfiguration();
        configuration.setProperty("hibernate.connection.url", POSTGRES.getJdbcUrl());
        configuration.setProperty("hibernate.configuration.username", POSTGRES.getUsername());
        configuration.setProperty("hibernate.configuration.password", POSTGRES.getPassword());
        configuration.configure();

        return configuration.buildSessionFactory();
    }

    private static Configuration buildConfiguration() {
        return new Configuration();
    }
}
