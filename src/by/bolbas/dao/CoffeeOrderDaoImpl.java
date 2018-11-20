package by.bolbas.dao;

import java.util.ArrayList;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import by.bolbas.entity.CoffeeOrder;
import by.bolbas.util.HibernateUtil;

public class CoffeeOrderDaoImpl implements CoffeeOrderDao {

	private static Transaction transObj;
	private static Session sessionObj = HibernateUtil.getSessionFactory().openSession();
	private final static Logger LOG = Logger.getLogger(CoffeeOrderDaoImpl.class);

	@Override
	public boolean add(CoffeeOrder order) {
		try {
			transObj = sessionObj.beginTransaction();
			sessionObj.save(order);
			LOG.debug("Запущен метод - addOrder(); (Метод добавления \"Заказа\"), в классе -  CoffeeOrderDaoImp");
			return true;
		} catch (Exception exceptionObj) {
			LOG.error("Ошибка в методе - addOrder(); (Метод добавления \"Заказа\"), в классе -  CoffeeOrderDaoImp");
			exceptionObj.printStackTrace();
		} finally {
			transObj.commit();
		}
		return false;
	}

	@Override
	public Long getCount() {
		try {
			LOG.debug(
					"Запущен метод - getOrderCount(); (Метод получаение кол-во \"Заказов\"), в классе -  CoffeeOrderDaoImp");
			transObj = sessionObj.beginTransaction();
			return (Long) sessionObj.createQuery("select count(*) from CoffeeOrder").uniqueResult();
		} catch (Exception exceptionObj) {
			LOG.error(
					"Ошибка в методе - getOrderCount(); (Метод получаение кол-во \"Заказов\"), в классе -  CoffeeOrderDaoImp");
			exceptionObj.printStackTrace();
		} finally {
			transObj.commit();
		}
		return 0L;
	}

	@Override
	public List<CoffeeOrder> getAll() {
		List<CoffeeOrder> allCoffeeOrders = new ArrayList<>();
		try {
			transObj = sessionObj.beginTransaction();
			Query queryObj = sessionObj.createQuery("from CoffeeOrder");
			allCoffeeOrders = queryObj.list();
			LOG.debug(
					"Запущен метод - retrieveCoffeeOrder(); (Метод получение всех \"Заказов\"), в классе -  CoffeeOrderDaoImp");
		} catch (Exception exceptionObj) {
			LOG.error(
					"Ошибка в методе - retrieveCoffeeOrder(); (Метод получение всех \"Заказов\"), в классе -  CoffeeOrderDaoImp");
			exceptionObj.printStackTrace();
		} finally {
			transObj.commit();
		}
		return allCoffeeOrders;
	}

	@Override
	public CoffeeOrder getById(int orderId) {
		CoffeeOrder particularOrdObj = null;
		try {
			transObj = sessionObj.beginTransaction();
			Query queryObj = sessionObj.createQuery("from CoffeeOrder where id= :order_id").setInteger("order_id",
					orderId);
			particularOrdObj = (CoffeeOrder) queryObj.uniqueResult();
			LOG.debug(
					"Запущен метод - getOrderById(); (Метод получния информации о конкретном \"Заказе\"), в классе -  CoffeeOrderDaoImp");
		} catch (Exception exceptionObj) {
			LOG.error(
					"Ошибка в методе - getOrderById(); (Метод получния информации о конкретном \"Заказе\"), в классе -  CoffeeOrderDaoImp");
			exceptionObj.printStackTrace();
		} finally {
			transObj.commit();
		}
		return particularOrdObj;
	}

	@Override
	public boolean update(CoffeeOrder order) {
		try {
			transObj = sessionObj.beginTransaction();
			sessionObj.update(order);
			LOG.debug(
					"Запущен метод - updateCoffeeOrderRecord(); (Метод обновляет информацию а каком-то конкретном \"Заказе\"), в классе -  CoffeeOrderDaoImp");
			return true;
		} catch (Exception exceptionObj) {
			exceptionObj.printStackTrace();
		} finally {
			LOG.error(
					"Ошибка в методе - updateCoffeeOrderRecord(); (Метод обновляет информацию а каком-то конкретном \"Заказе\"), в классе -  CoffeeOrderDaoImp");
			transObj.commit();
		}
		return false;
	}

	@Override
	public void delete(CoffeeOrder order) {
		try {
			transObj = sessionObj.beginTransaction();
			sessionObj.delete(order);
			LOG.debug(
					"Запущен метод - deleteCoffeeOrderInDb(); (Метод удаляет информацию а каком-то конкретном \"Заказе\"), в классе -  CoffeeOrderDaoImp");
		} catch (Exception exceptionObj) {
			LOG.error(
					"Ошибка в методе - deleteCoffeeOrderInDb(); (Метод удаляет информацию а каком-то конкретном \"Заказе\"), в классе -  CoffeeOrderDaoImp");
			exceptionObj.printStackTrace();
		} finally {
			transObj.commit();
		}
	}

}
