package by.bolbas.converter;

import java.util.List;
import java.util.function.Predicate;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import by.bolbas.entity.Delivery;

/**
 * Класс - конвертер.
 * Предназначен для отображения и получения информации о доставке
 *
 */
@FacesConverter(value = "deliveryConverter")
public class DeliveryConverter implements Converter{

	  @Override
	    public Object getAsObject(FacesContext ctx, UIComponent comp, String value) {
	        Object o = null;
	        if (!(value == null || value.isEmpty())) {
	            o = this.getSelectedItemAsEntity(comp, value);
	        }
	        return o;
	    }

	    @Override
	    public String getAsString(FacesContext ctx, UIComponent comp, Object value) {
	        String s = "";
	        if (value != null) {
	            s = ((Delivery) value).toString();
	        }
	        return s;
	    }

	    /**
	     * Метод предназначен для преобразования выбранного типа доставки, в объект
	     * 
	     */
	    private Delivery getSelectedItemAsEntity(UIComponent comp, String value) {
	    	Delivery item = null;

	        List<Delivery> selectItems = null;
	        for (UIComponent uic : comp.getChildren()) {
	            if (uic instanceof UISelectItems) {
	            	String[] names = value.split(" ");
	            	StringBuilder builder = new StringBuilder();
	            	for (int j = 0; j < names.length - 1; j++) {
	            		builder.append(" ");
						builder.append(names[j]);
					}
	                String name = builder.deleteCharAt(0).toString();
	                selectItems = (List<Delivery>) ((UISelectItems) uic).getValue();

	                if (name != null && selectItems != null && !selectItems.isEmpty()) {
	                    Predicate<Delivery> predicate = i -> i.getType().equals(name);
	                    item = selectItems.stream().filter(predicate).findFirst().orElse(null);
	                }
	            }
	        }
	        return item;
	    }
	
}
