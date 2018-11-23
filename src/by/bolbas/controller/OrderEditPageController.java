package by.bolbas.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import by.bolbas.dao.CoffeeDao;
import by.bolbas.dao.CoffeeDaoImpl;
import by.bolbas.dao.CoffeeOrderDao;
import by.bolbas.dao.CoffeeOrderDaoImpl;
import by.bolbas.dao.DeliveryDao;
import by.bolbas.dao.DeliveryDaoImpl;
import by.bolbas.entity.Coffee;
import by.bolbas.entity.CoffeeOrder;
import by.bolbas.entity.Delivery;

/**
 * Класс контроллер для изменений информации в заказе
 *
 */
@ManagedBean
@ViewScoped
public class OrderEditPageController implements Serializable {

	private static final long serialVersionUID = 5021930446584860266L;
	private CoffeeOrderDao dao;
	private CoffeeDao coffeeDao;
	private DeliveryDao deliveryDao;

	private Long count;
	private List<Coffee> coffeeList;
	private Coffee selectedCoffee;

	private List<Delivery> deliveryList;
	private Delivery selectedDelivery;

	private CoffeeOrder order;

	/**
	 * Метод получает начальные данные для отображения на странице
	 */
	@PostConstruct
	public void init() {
		dao = new CoffeeOrderDaoImpl();
		coffeeDao = new CoffeeDaoImpl();
		deliveryDao = new DeliveryDaoImpl();
		count = dao.getCount();
		coffeeList = coffeeDao.getAll();
		deliveryList = deliveryDao.getAll();

		order = (CoffeeOrder) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("editCoffeeOrder");
		selectedCoffee = order.getCoffee();
		selectedDelivery = order.getDelivery();
	}

	/**
	 * Метод предназнаен для обновления информации в заказе
	 * Перенаправляет на стартовую страницу
	 */
	public String update(CoffeeOrder order) {
		if (dao.update(order)) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("createdCoffeeOrderMessage",
					"Заказ обновлен");
		} else {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("createdCoffeeOrderMessage",
					"Заказ не был обновлен");
		}

		return "index";
	}

	/**
	 * Метод предназначен для обновления текущей цены заказа
	 */
	public void updateTotalCost() {
		if (selectedCoffee == null || selectedDelivery == null) {
			order.setTotalCost(0);
		}
		order.setTotalCost(selectedCoffee.getPrice() + selectedDelivery.getPrice());
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<Coffee> getCoffeeList() {
		return coffeeList;
	}

	public void setCoffeeList(List<Coffee> coffeeList) {
		this.coffeeList = coffeeList;
	}

	public Coffee getSelectedCoffee() {
		return selectedCoffee;
	}

	public void setSelectedCoffee(Coffee selectedCoffee) {
		this.selectedCoffee = selectedCoffee;
	}

	public List<Delivery> getDeliveryList() {
		return deliveryList;
	}

	public void setDeliveryList(List<Delivery> deliveryList) {
		this.deliveryList = deliveryList;
	}

	public Delivery getSelectedDelivery() {
		return selectedDelivery;
	}

	public void setSelectedDelivery(Delivery selectedDelivery) {
		this.selectedDelivery = selectedDelivery;
	}


}
