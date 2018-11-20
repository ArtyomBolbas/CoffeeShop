package by.bolbas.controller;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import by.bolbas.dao.CoffeeOrderDao;
import by.bolbas.dao.CoffeeOrderDaoImpl;

@ManagedBean
@RequestScoped
public class IndexPageController {

	private CoffeeOrderDao dao;
	private Long count;

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
