package by.bolbas.util;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@ApplicationScoped
public class AppUrlStore implements Serializable {
	/*
	 * Класс AppUrlStore. Отвечает за хранение адресов страниц
	 */

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * Поля, свойства необходииые AppUrlStore 
	 */
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	private static final long serialVersionUID = 1L;

	private String baseUrl = null;
	private String orderUrl = null;
	private String ordersTableUrl = null;
	private String orderEditUrl = null;
	private String orderDeleteUrl = null;

	public String getBaseUrl() {return baseUrl; }
	public String getOrderUrl() { return orderUrl; }
	public String getOrdersTableUrl() {return ordersTableUrl; }
	public String getOrderEditUrl() {return orderEditUrl; }
	public String getOrderDeleteUrl() {return orderDeleteUrl; }
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * 	Метод вызывается после создания объекта, служит для инициализации.
	 */
	@PostConstruct
    public void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String baseUrl = externalContext.getInitParameter("BaseUrl");

		this.baseUrl = baseUrl;
		this.orderUrl = baseUrl + "order.xhtml";
		this.ordersTableUrl = baseUrl + "ordersTable.xhtml";
		this.orderEditUrl = baseUrl + "orderEdit.xhtml";
		this.orderDeleteUrl = baseUrl + "orderDelete.xhtml";
    }
}
