package by.bolbas.dao;

import java.util.List;

import by.bolbas.entity.CoffeeOrder;

public interface CoffeeOrderDao {
	boolean add(CoffeeOrder order);

	Long getCount();

	List<CoffeeOrder> getAll();

	CoffeeOrder getById(int orderId);

	boolean update(CoffeeOrder order);

	void delete(CoffeeOrder order);
}
