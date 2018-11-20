package by.bolbas.database;

import java.util.ArrayList;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import by.bolbas.model.CoffeeOrder;
import by.bolbas.util.HibernateUtil;

@ManagedBean
public class DatabaseOperations {
	/*
	 * Класс DatabaseOperations отвечает за операции производимые с базой данных.
	 */

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * Поля, свойства необходииые DatabaseOperations
	 */
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	private static Transaction transObj;
	private static Session sessionObj = HibernateUtil.getSessionFactory().openSession();
	private final static Logger LOG = Logger.getLogger(DatabaseOperations.class);

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * Метод отвечает за добавление "Заказа" в базу данных. После чего происходим
	 * перенаправление на "Главную" страницу
	 */
	public String addOrder(CoffeeOrder order) {
		try {
			transObj = sessionObj.beginTransaction();
			sessionObj.save(order);
			LOG.debug("Запущен метод - addOrder(); (Метод добавления \"Заказа\"), в классе -  DatabaseOperations");
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("createdCoffeeOrderMessage",
					"Заказ принят в обработку");
		} catch (Exception exceptionObj) {
			LOG.error("Ошибка в методе - addOrder(); (Метод добавления \"Заказа\"), в классе -  DatabaseOperations");
			exceptionObj.printStackTrace();
		} finally {
			transObj.commit();
		}
		return "index";
	}

	/*
	 * Метод получение количество "Заказов" в базе данных Используется для показа на
	 * каждой страничке кол-ва заказов
	 */
	public Long getOrderCount() {
		try {
			LOG.debug(
					"Запущен метод - getOrderCount(); (Метод получаение кол-во \"Заказов\"), в классе -  DatabaseOperations");
			transObj = sessionObj.beginTransaction();
			return (Long) sessionObj.createQuery("select count(*) from CoffeeOrder").uniqueResult();
		} catch (Exception exceptionObj) {
			LOG.error(
					"Ошибка в методе - getOrderCount(); (Метод получаение кол-во \"Заказов\"), в классе -  DatabaseOperations");
			exceptionObj.printStackTrace();
		} finally {
			transObj.commit();
		}
		return 0L;
	}

	/*
	 * Метод позволяет получить всю информацию из базы данных.
	 */
	@SuppressWarnings("unchecked")
	public List<CoffeeOrder> retrieveCoffeeOrder() {
		List<CoffeeOrder> allCoffeeOrders = new ArrayList<>();
		try {
			transObj = sessionObj.beginTransaction();
			Query queryObj = sessionObj.createQuery("from CoffeeOrder");
			allCoffeeOrders = queryObj.list();
			LOG.debug(
					"Запущен метод - retrieveCoffeeOrder(); (Метод получение всех \"Заказов\"), в классе -  DatabaseOperations");
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("findCoffeeOrderById", "true");
		} catch (Exception exceptionObj) {
			LOG.error(
					"Ошибка в методе - retrieveCoffeeOrder(); (Метод получение всех \"Заказов\"), в классе -  DatabaseOperations");
			exceptionObj.printStackTrace();
		} finally {
			transObj.commit();
		}
		return allCoffeeOrders;
	}

	/*
	 * Метод позволяет получения информации о конкретном "Заказе" Служит для
	 * дальнейшего обновления информации.
	 * 
	 */
	public String getOrderById(int orderId) {
		CoffeeOrder particularOrdObj = new CoffeeOrder();
		try {
			transObj = sessionObj.beginTransaction();
			Query queryObj = sessionObj.createQuery("from CoffeeOrder where id= :order_id").setInteger("order_id",
					orderId);
			particularOrdObj = (CoffeeOrder) queryObj.uniqueResult();
			LOG.debug(
					"Запущен метод - getOrderById(); (Метод получния информации о конкретном \"Заказе\"), в классе -  DatabaseOperations");
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("editCoffeeOrder",
					particularOrdObj);
		} catch (Exception exceptionObj) {
			LOG.error(
					"Ошибка в методе - getOrderById(); (Метод получния информации о конкретном \"Заказе\"), в классе -  DatabaseOperations");
			exceptionObj.printStackTrace();
		} finally {
			transObj.commit();
		}
		return "orderEdit";
	}

	/*
	 * Метод позволяет получения информации о конкретном "Заказе" Служит для
	 * дальнейшего удаления информации.
	 */
	public String getOrderByDeleteId(int orderId) {
		CoffeeOrder particularOrdObj = new CoffeeOrder();
		try {
			transObj = sessionObj.beginTransaction();
			Query queryObj = sessionObj.createQuery("from CoffeeOrder where id= :order_id").setInteger("order_id",
					orderId);
			particularOrdObj = (CoffeeOrder) queryObj.uniqueResult();
			LOG.debug(
					"Запущен метод - getOrderByDeleteId(); (Метод получения информации о конкретном \"Заказе\"), в классе -  DatabaseOperations");
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("deleteCoffeeOrder",
					particularOrdObj);
		} catch (Exception exceptionObj) {
			exceptionObj.printStackTrace();
		} finally {
			LOG.error(
					"Ошибка в методе - getOrderByDeleteId(); (Метод получения информации о конкретном \\\"Заказе\\\"), в классе -  DatabaseOperations");
			transObj.commit();
		}
		return "orderDelete";
	}

	/*
	 * Метод позволяет обновить информацию Служит для дальнейшего обновления
	 * информации.
	 * 
	 */
	public String updateCoffeeOrderRecord(CoffeeOrder updateCoffeeOrderObj) {
		try {
			transObj = sessionObj.beginTransaction();
			sessionObj.update(updateCoffeeOrderObj);
			LOG.debug(
					"Запущен метод - updateCoffeeOrderRecord(); (Метод обновляет информацию а каком-то конкретном \"Заказе\"), в классе -  DatabaseOperations");
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("createdCoffeeOrderMessage",
					"Заказ обновлен");
		} catch (Exception exceptionObj) {
			exceptionObj.printStackTrace();
		} finally {
			LOG.error(
					"Ошибка в методе - updateCoffeeOrderRecord(); (Метод обновляет информацию а каком-то конкретном \"Заказе\"), в классе -  DatabaseOperations");
			transObj.commit();
		}
		return "index";
	}

	/*
	 * Метод позволяет удалить информацию Служит для дальнейшего удаления
	 * информации.
	 */
	@SuppressWarnings("deprecation")
	public String deleteCoffeeOrderInDb(int delCoffeeOrderId) {
		try {
			transObj = sessionObj.beginTransaction();
			CoffeeOrder cOId = (CoffeeOrder) sessionObj.load(CoffeeOrder.class, new Integer(delCoffeeOrderId));
			sessionObj.delete(cOId);
			LOG.debug(
					"Запущен метод - deleteCoffeeOrderInDb(); (Метод удаляет информацию а каком-то конкретном \"Заказе\"), в классе -  DatabaseOperations");
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("createdCoffeeOrderMessage",
					"Заказ удален");
		} catch (Exception exceptionObj) {
			exceptionObj.printStackTrace();
		} finally {
			LOG.error(
					"Ошибка в методе - deleteCoffeeOrderInDb(); (Метод удаляет информацию а каком-то конкретном \"Заказе\"), в классе -  DatabaseOperations");
			transObj.commit();
		}
		return "index";
	}

}