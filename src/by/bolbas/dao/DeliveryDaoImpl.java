package by.bolbas.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import by.bolbas.entity.Delivery;
import by.bolbas.util.HibernateUtil;

/**
 * Класс "подписавший" интерфейс DeliveryDao
 *
 */
public class DeliveryDaoImpl implements DeliveryDao{

	private static Transaction transObj;
	private static Session sessionObj = HibernateUtil.getSessionFactory().openSession();
	private final static Logger LOG = Logger.getLogger(DeliveryDaoImpl.class);

	/* 
	 * Метод получает список всех заказов
	 */
	@Override
	public List<Delivery> getAll() {
		List<Delivery> allDelivery = new ArrayList<>();
		try {
			transObj = sessionObj.beginTransaction();
			Query queryObj = sessionObj.createQuery("from Delivery");
			allDelivery = queryObj.list();
			LOG.debug(
					"Запущен метод - getAll(); (Метод получение всех \"Delivery\"), в классе -  DeliveryDaoImpl");
		} catch (Exception exceptionObj) {
			LOG.error(
					"Ошибка в методе - getAll(); (Метод получение всех \"Delivery\"), в классе -  DeliveryDaoImpl");
			exceptionObj.printStackTrace();
		} finally {
			transObj.commit();
		}
		return allDelivery;
	}

}
