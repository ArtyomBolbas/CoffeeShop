package by.bolbas.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import by.bolbas.dao.CoffeeOrderDao;
import by.bolbas.dao.CoffeeOrderDaoImpl;

/**
 * Класс контроллер для стартовой страницы 
 *
 */
@ManagedBean
@RequestScoped
public class IndexPageController implements Serializable {

	private static final long serialVersionUID = 2181410275548985285L;
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
	
	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
