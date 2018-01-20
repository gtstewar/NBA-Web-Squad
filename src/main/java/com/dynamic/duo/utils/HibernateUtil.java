package com.dynamic.duo.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * A utility class for setting up the Hibernate SessionFactory
 *
 * @author Elizabeth Gilbert
 */
public class HibernateUtil {

    /**
     * SeesionFactory used
     */
    private static SessionFactory sessionFactory = buildSessionFactory();

    /**
     * Creates a SessionFactory
     *
     * @return SessioNFactory that was built
     */
    private static SessionFactory buildSessionFactory () {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            System.out.println(configuration.getSqlResultSetMappings());
            System.out.println("Hibernate Configuration loaded");
            System.out.println(configuration.getProperty("hibernate.connection.password"));
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate serviceRegistry created");

            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            return sessionFactory;
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Retrieves the SessionFactory generated
     *
     * @return sessionFactory that was generated
     */
    public static SessionFactory getSessionFactory () {
        return sessionFactory;
    }

    /**
     * Close the SessionFactory
     */
    public static void shutdown () {
        // Close caches and connection pools
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }
}