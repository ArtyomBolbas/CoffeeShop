package by.bolbas.util;

import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	/*
	 * Класс HibernateUtil. Отвечает за ......ДОПИСАТЬ!!!!!
	 */

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * Поля, свойства необходииые HibernateUtil 
	 */
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	private static SessionFactory sessionFactoryObj = buildSessionFactoryObj();

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// Create The SessionFactory Object From Standard (Hibernate.cfg.xml) Configuration File
	/*
	 * Создает ......ДОПИСАТЬ!!!!!!!!!!!!
	 */
	public static SessionFactory buildSessionFactoryObj() {
		try {
			sessionFactoryObj = new Configuration().configure().buildSessionFactory();
		} catch (ExceptionInInitializerError exceptionObj) {
			exceptionObj.printStackTrace();
		}
		return sessionFactoryObj;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * Геттеры, сеттеры
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactoryObj;
	}
}