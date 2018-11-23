package by.bolbas.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import by.bolbas.entity.Coffee;
import by.bolbas.util.HibernateUtil;

/**
 * Класс "подписавший" интерфейс CoffeeDao
 *
 */
public class CoffeeDaoImpl implements CoffeeDao {

	private static Transaction transObj;
	private static Session sessionObj = HibernateUtil.getSessionFactory().openSession();
	private final static Logger LOG = Logger.getLogger(CoffeeDaoImpl.class);

	/* 
	 * Метод получает список всего кофе
	 */
	@Override
	public List<Coffee> getAll() {
		List<Coffee> allCoffee = new ArrayList<>();
		try {
			transObj = sessionObj.beginTransaction();
			Query queryObj = sessionObj.createQuery("from Coffee");
			allCoffee = queryObj.list();
			LOG.debug(
					"Запущен метод - getAll(); (Метод получение всех \"Coffee\"), в классе -  CoffeeDaoImpl");
		} catch (Exception exceptionObj) {
			LOG.error(
					"Ошибка в методе - getAll(); (Метод получение всех \"Coffee\"), в классе -  CoffeeDaoImpl");
			exceptionObj.printStackTrace();
		} finally {
			transObj.commit();
		}
		return allCoffee;
	}

}
