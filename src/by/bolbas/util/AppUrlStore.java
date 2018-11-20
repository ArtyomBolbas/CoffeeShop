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
	
	public String indexPage() {
		return "index";
	}
	
	public String orderPage() {
		return "order";
	}
	
	public String ordersTablePage() {
		return "ordersTable";
	}
	
	
	
}
