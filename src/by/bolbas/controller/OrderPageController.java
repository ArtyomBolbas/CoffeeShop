package by.bolbas.controller;

import java.io.Serializable;
import java.util.Date;
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
 * Класс контроллер для оформления заказа
 *
 */
@ManagedBean
@ViewScoped
public class OrderPageController implements Serializable {

	private static final long serialVersionUID = 5021930446584860266L;
	private CoffeeOrderDao dao;
	private CoffeeDao coffeeDao;
	private DeliveryDao deliveryDao;
	
	private Long count;
	private List<Coffee> coffeeList;
	private Coffee selectedCoffee;
	
	private List<Delivery> deliveryList;
	private Delivery selectedDelivery;
	
	private Integer weight;
	private Date date;
	private Date startTime;
	private Date endTime;
	private Integer totalCost;

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
		if (!coffeeList.isEmpty()) {
			selectedCoffee = coffeeList.get(0);
		}
		if (!deliveryList.isEmpty()) {
			selectedDelivery = deliveryList.get(0);
		}
	}

	/**
	 * Метод предназначен для обновления текущей цены заказа
	 */
	public void updateTotalCost() {
		if (selectedCoffee == null || selectedDelivery == null) {
			totalCost = 0;
		}
		totalCost = selectedCoffee.getPrice() + selectedDelivery.getPrice();
	}
	
	/**
	 * Метод предназначен для добавления заказа
	 * Перенаправляет на главную страницу
	 */
	public String addOrder() {
		CoffeeOrder coffeeOrder = new CoffeeOrder();
		coffeeOrder.setCoffee(selectedCoffee);
		coffeeOrder.setDelivery(selectedDelivery);
		coffeeOrder.setWeight(weight);
		coffeeOrder.setOrderDate(date);
		coffeeOrder.setStartTime(startTime);
		coffeeOrder.setEndTime(endTime);
		coffeeOrder.setTotalCost(totalCost);
		
		if (dao.add(coffeeOrder)) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("createdCoffeeOrderMessage",
					"Заказ принят в обработку");
		} else {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("createdCoffeeOrderMessage",
					"Заказ не был принят в обработку");			
		}
		
		return "index";
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

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Integer totalCost) {
		this.totalCost = totalCost;
	}
	
	

}
