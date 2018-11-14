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
	private static final long serialVersionUID = 1L;

	private String baseUrl = null;
	private String orderUrl = null;
	private String orderEditUrl = null;

	public String getBaseUrl() {return baseUrl; }
	public String getOrderUrl() { return orderUrl; }
	public String getOrderEditUrl() {return orderEditUrl; }

	@PostConstruct
    public void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String baseUrl = externalContext.getInitParameter("BaseUrl");

		this.baseUrl = baseUrl;
		this.orderUrl = baseUrl + "order.xhtml";
		this.orderEditUrl = baseUrl + "orderEdit.xhtml";
    }
}
