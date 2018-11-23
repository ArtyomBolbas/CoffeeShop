package by.bolbas.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import by.bolbas.dao.CoffeeOrderDao;
import by.bolbas.dao.CoffeeOrderDaoImpl;
import by.bolbas.entity.CoffeeOrder;

/**
 * Класс контроллер для отображения всех заказов
 *
 */
@ManagedBean
@ViewScoped
public class OrdersTablePageController implements Serializable {
	private static final long serialVersionUID = 7455215826305449347L;

	private CoffeeOrderDao dao;
	private Long count;

	private List<CoffeeOrder> coffeeOrders;


	/**
	 * Метод получает начальные данные для отображения на странице
	 */
	@PostConstruct
	public void init() {
		dao = new CoffeeOrderDaoImpl();
		count = dao.getCount();
		coffeeOrders = dao.getAll();
	}

	
	/**
	 * Метод перенаправляет на страницу редактирования
	 * выбранного заказа
	 */
	public String editPage(Integer id) {
		CoffeeOrder coffeeOrder = dao.getById(id);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("editCoffeeOrder", coffeeOrder);
		return "orderEdit";
	}

	
	/**
	 * Метод перенаправляет на страницу удаления
	 * выбранного заказа
	 */
	public String deletePage(Integer id) {
		CoffeeOrder coffeeOrder = dao.getById(id);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("deleteCoffeeOrder", coffeeOrder);
		return "orderDelete";
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<CoffeeOrder> getCoffeeOrders() {
		return coffeeOrders;
	}

	public void setCoffeeOrders(List<CoffeeOrder> coffeeOrders) {
		this.coffeeOrders = coffeeOrders;
	}

}
