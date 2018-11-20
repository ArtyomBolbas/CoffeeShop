package by.bolbas.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import by.bolbas.dao.CoffeeDao;
import by.bolbas.dao.CoffeeDaoImpl;
import by.bolbas.dao.CoffeeOrderDao;
import by.bolbas.dao.CoffeeOrderDaoImpl;

@ManagedBean
@RequestScoped
public class OrderPageController {

	private CoffeeOrderDao dao;
	private Long count;
	private CoffeeDao coffeeDao;
	private List<String> coffeeNames;
	private String selectedCoffeeName;

	@PostConstruct
	public void init() {
		dao = new CoffeeOrderDaoImpl();
		coffeeDao = new CoffeeDaoImpl();
		count = dao.getCount();
		coffeeNames = coffeeDao.getAll().stream().map(coffee -> coffee.getName()).collect(Collectors.toList());
	}

	public void coffeeTypeChanged() {
		//totalCost = getCoffeeCost();
	}
	
	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<String> getCoffeeNames() {
		return coffeeNames;
	}

	public void setCoffeeNames(List<String> coffeeNames) {
		this.coffeeNames = coffeeNames;
	}

	public String getSelectedCoffeeName() {
		return selectedCoffeeName;
	}

	public void setSelectedCoffeeName(String selectedCoffeeName) {
		this.selectedCoffeeName = selectedCoffeeName;
	}
	
	

}
