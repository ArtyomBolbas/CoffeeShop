package by.bolbas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

/*
 * Класс OrderDateConverter отвечает за формат даты, введенной пользователем.
 * В случае неверного ввода "выбрасывает" предупреждение
 */

@FacesConverter("orderDateConverter")
public class OrderDateConverter extends DateTimeConverter {

	public OrderDateConverter() {
		setPattern("MM/dd/yyyy");
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.length() != getPattern().length()) {
			throw new ConverterException("Invalid format");
		}

		return super.getAsObject(context, component, value);
	}

}
