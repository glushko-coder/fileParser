package com.example.parser.util;

import com.example.parser.model.Doc;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DBUtils {
	private static DBUtils dbUtils;

	private DBUtils() {}

	public static synchronized DBUtils getDBUtils() {
		if (dbUtils == null) {
			dbUtils = new DBUtils();
		}
		return dbUtils;
	}

	public SessionFactory getConfiguration() throws HibernateException {
		Configuration configuration = new Configuration()
				.setProperty( "hibernate.connection.driver_class",      "com.mysql.jdbc.Driver")
				.setProperty( "hibernate.connection.url",               "jdbc:mysql://localhost:3306/parser?useSSL=false")
				.setProperty( "hibernate.connection.username",          "admin")
				.setProperty( "hibernate.connection.password",          "admin")
				.setProperty( "hibernate.connection.pool_size",         "2")
				.setProperty( "hibernate.connection.autocommit",        "false")
				.setProperty( "hibernate.cache.provider_class",         "org.hibernate.cache.NoCacheProvider")
				.setProperty( "hibernate.dialect",                      "org.hibernate.dialect.MySQL5Dialect")
				.setProperty( "hibernate.show_sql",                     "false")
				.setProperty( "hibernate.current_session_context_class","thread")
				.setProperty( "hibernate.hbm2ddl.auto",                 "create-drop")
				.addAnnotatedClass(Doc.class)
		;
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.build();
		return configuration.buildSessionFactory(serviceRegistry);
	}

}
