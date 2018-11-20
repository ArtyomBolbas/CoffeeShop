package by.bolbas.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import by.bolbas.entity.CoffeeOrder;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class CoffeeOrderController {
	/*
	 * Класс CoffeeOrder. Отвечает за создание новой сущности "CoffeeOrder".
	 */

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * Поля, свойства необходииые CoffeeOrder
	 */
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	private static final long serialVersionUID = -1540437138823134510L;

	private Integer id;
	private String coffeeType = "Arabica";
	private String deliveryType = "Without delivery";
	private Integer weight;
	private Date orderDate;
	private Date startTime;
	private Date endTime;
	private Integer totalCost = 10;

	private static final String COFFEE_TYPE_ARABICA = "Arabica";
	private static final String COFFEE_TYPE_CANEPHORA = "Canephora";
	private static final String COFFEE_TYPE_LIBERICA = "Liberica";

	private static final String COFFEE_TYPE_PRICE_ARABICA = "Coffee Arabica (10$)";
	private static final String COFFEE_TYPE_PRICE_CANEPHORA = "Coffee Canephora (15$)";
	private static final String COFFEE_TYPE_PRICE_LIBERICA = "Coffee Liberica (20$)";

	////////////////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * Присваеваем значение в зависимости от выбора пользователя. Делаем
	 * соответствие наименования кофе его значению для дальнейшего отображения на
	 * странице
	 */
	private Map<String, String> coffeeTypeMap = new HashMap<>();
	{
		coffeeTypeMap.put(COFFEE_TYPE_PRICE_ARABICA, COFFEE_TYPE_ARABICA);
		coffeeTypeMap.put(COFFEE_TYPE_PRICE_CANEPHORA, COFFEE_TYPE_CANEPHORA);
		coffeeTypeMap.put(COFFEE_TYPE_PRICE_LIBERICA, COFFEE_TYPE_LIBERICA);
	}

	/*
	 * Рассчитываем суммарную стоимость в зависимости от выбора кофе
	 */
	public void coffeeTypeChanged() {
		totalCost = getCoffeeCost();
	}

	/*
	 * Присваеваем значение в зависимости от выбора пользователя. Делаем
	 * соответствие выбора доставки, его значения для дальнейшего отображения на
	 * странице.
	 */
	private Map<String, String> coffeeDeliveryMap = new HashMap<>();
	{
		coffeeDeliveryMap.put("Без доставки", "Without delivery");
		coffeeDeliveryMap.put("С доставкой", "With delivery");
	}

	/*
	 * В зависимости от выбора пользователя: "With delivery" - добавляет к общей
	 * стоимости заказа +5, "Without delivery" - оставляет значение прежним
	 */
	public void coffeeDeliveryChanged() {
		if ("With delivery".equals(deliveryType)) {
			totalCost = getCoffeeCost() + 5;
		} else {
			totalCost = getCoffeeCost();
		}
	}

	/*
	 * Возвращает значение в зависимости от выбора пользователя
	 */
	private Integer getCoffeeCost() {
		if (coffeeType != null) {
			switch (coffeeType) {
			case "Arabica":
				return 10;
			case "Canephora":
				return 15;
			case "Liberica":
				return 20;
			default:
				return 0;
			}
		}
		return 0;
	}

	/*
	 * Геттеры, сеттеры
	 */
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCoffeeType() {
		return coffeeType;
	}

	public void setCoffeeType(String coffeeType) {
		this.coffeeType = coffeeType;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
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

	public Map<String, String> getCoffeeDeliveryMap() {
		return coffeeDeliveryMap;
	}

	public void setCoffeeDeliveryMap(Map<String, String> coffeeDeliveryMap) {
		this.coffeeDeliveryMap = coffeeDeliveryMap;
	}

	public Map<String, String> getCoffeeTypeMap() {
		return coffeeTypeMap;
	}

	public void setCoffeeTypeMap(Map<String, String> coffeeTypeMap) {
		this.coffeeTypeMap = coffeeTypeMap;
	}

	/*
	 * Переопределение hashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coffeeType == null) ? 0 : coffeeType.hashCode());
		result = prime * result + ((deliveryType == null) ? 0 : deliveryType.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((totalCost == null) ? 0 : totalCost.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}

	/*
	 * Переопределение equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoffeeOrderController other = (CoffeeOrderController) obj;
		if (coffeeType == null) {
			if (other.coffeeType != null)
				return false;
		} else if (!coffeeType.equals(other.coffeeType))
			return false;
		if (deliveryType == null) {
			if (other.deliveryType != null)
				return false;
		} else if (!deliveryType.equals(other.deliveryType))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (totalCost == null) {
			if (other.totalCost != null)
				return false;
		} else if (!totalCost.equals(other.totalCost))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}

	/*
	 * Переопределение toString
	 */
	@Override
	public String toString() {
		return weight + " гр. " + coffeeType + " c " + startTime + " по " + endTime + " " + orderDate + " "
				+ deliveryType + " $" + totalCost;
	}

}