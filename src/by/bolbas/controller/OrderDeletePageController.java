package by.bolbas.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import by.bolbas.dao.CoffeeOrderDao;
import by.bolbas.dao.CoffeeOrderDaoImpl;
import by.bolbas.entity.CoffeeOrder;

/**
 * Класс контроллер для страницы удаления заказа
 *
 */
@ManagedBean
@ViewScoped
public class OrderDeletePageController implements Serializable {

	private static final long serialVersionUID = 5021930446584860266L;
	private CoffeeOrderDao dao;

	private Long count;

	/**
	 * Метод получает начальные данные для отображения на странице
	 */
	@PostConstruct
	public void init() {
		dao = new CoffeeOrderDaoImpl();
		count = dao.getCount();
	}

	/**
	 * 
	 * Метод предназначен для удаления заказа
	 * Перенаправляет на стартовую страницу
	 */
	public String delete(CoffeeOrder order) {
		dao.delete(order);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("createdCoffeeOrderMessage",
				"Заказ удален");

		return "index";
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
