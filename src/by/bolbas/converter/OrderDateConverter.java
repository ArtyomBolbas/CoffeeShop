package by.bolbas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter("orderDateConverter")
public class OrderDateConverter extends DateTimeConverter{
	/*
	 * Класс OrderDateConverter отвечает за формат даты, введенной пользователем.
	 * В случае неверного ввода "выбрасывает" предупреждение
	 */

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * Поля, свойства необходииые OrderDateConverter 
	 */
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * Конструктор класса OrderDateConverter
	 */
    public OrderDateConverter() {
        setPattern("MM/dd/yyyy");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * Метод отвечает за корректность даты (длина, проверка на Null) 
	 */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.length() != getPattern().length()) {
            throw new ConverterException("Invalid format");
        }

        return super.getAsObject(context, component, value);
    }
	
}

