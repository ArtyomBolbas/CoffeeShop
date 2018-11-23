package by.bolbas.converter;

import java.util.List;
import java.util.function.Predicate;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import by.bolbas.entity.Coffee;

/**
 * Класс - конвертер.
 * Предназначен для отображения и получения информации о кофе
 *
 */
@FacesConverter(value = "coffeeConverter")
public class CoffeeConverter implements Converter{

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
	            s = ((Coffee) value).toString();
	        }
	        return s;
	    }

	    /**
	     * Метод предназначен для преобразования выбранного в меню кофе, в объект
	     * 
	     */
	    private Coffee getSelectedItemAsEntity(UIComponent comp, String value) {
	        Coffee item = null;

	        List<Coffee> selectItems = null;
	        for (UIComponent uic : comp.getChildren()) {
	            if (uic instanceof UISelectItems) {
	                String name = value.split(" ")[0];
	                selectItems = (List<Coffee>) ((UISelectItems) uic).getValue();

	                if (name != null && selectItems != null && !selectItems.isEmpty()) {
	                    Predicate<Coffee> predicate = i -> i.getName().equals(name);
	                    item = selectItems.stream().filter(predicate).findFirst().orElse(null);
	                }
	            }
	        }
	        return item;
	    }
	
}
