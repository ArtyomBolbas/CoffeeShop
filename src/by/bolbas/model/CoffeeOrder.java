package by.bolbas.model;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

@ManagedBean
@RequestScoped
public class CoffeeOrder implements java.io.Serializable {
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
	private String coffeeType;
	private String deliveryType;
	private Integer weight;
	private Date orderDate;
	private Date startTime;
	private Date endTime;
	private Integer totalCost = 20;

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 *  ........ДОПИСАТЬ!!!!!!!!
	 */
	/*private Map<String, String> coffeeCostMap = new HashMap<>();

	{
		coffeeCostMap.put("Coffee Arabica (10$)", "10");
		coffeeCostMap.put("Coffee Canephora (15$)", "15");
		coffeeCostMap.put("Coffee Liberica (20$)", "20");
	}

	public void coffeeTypeChanged(ValueChangeEvent e) {
		totalCost = (Integer) e.getNewValue();
	}*/
	
	
	private Map<String, String> coffeeTypeMap = new HashMap<>();

	{
		coffeeTypeMap.put("Coffee Arabica (10$)", "Arabica");
		coffeeTypeMap.put("Coffee Canephora (15$)", "Canephora");
		coffeeTypeMap.put("Coffee Liberica (20$)", "Liberica");
	}

	public void cofeeTypeChanged(AjaxBehaviorEvent event) {
	    // Тут любой нужный код
		// Например
		switch (coffeeType) {
			case "Arabica": totalCost += 10;
			case "Canephora": totalCost += 15;
			case "Liberica": totalCost += 20;
			// и т.д. логику написать
		}
	}
	
	
	
	private Map<String, Integer> coffeeDeliveryMap = new HashMap<>();
	
	{
		coffeeDeliveryMap.put("Без доставки", 0); 
		coffeeDeliveryMap.put("С доставкой", 5);
	}
	
	public void coffeeDeliverChanged(ValueChangeEvent e) {
		totalCost = (Integer) e.getNewValue();
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


	public Map<String, String> getCoffeeTypeMap() {
		return coffeeTypeMap;
	}

	public void setCoffeeTypeMap(Map<String, String> coffeeTypeMap) {
		this.coffeeTypeMap = coffeeTypeMap;
	}

	public Map<String, Integer> getCoffeeDeliveryMap() {
		return coffeeDeliveryMap;
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
		CoffeeOrder other = (CoffeeOrder) obj;
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
