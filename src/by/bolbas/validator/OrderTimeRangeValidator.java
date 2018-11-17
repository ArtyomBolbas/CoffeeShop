package by.bolbas.validator;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("orderTimeRangeValidator")
public class OrderTimeRangeValidator implements Validator {
	/*
	 * Класс OrderTimeRangeValidator. Контролирует диапазон времени введенный пользователем.
	 * В случае, некорректного ввода "выбрасывает" исключение (сообщние: "Диапазон времени введен некорректно")
	 */

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * Реализация метода validate
	 */
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value == null) {
			return; 
		}

		UIInput startDateComponent = (UIInput) component.getAttributes().get("startDateComponent");

		if (!startDateComponent.isValid()) {
			return; 
		}

		Date startDate = (Date) startDateComponent.getValue();

		if (startDate == null) {
			return;
		}

		Date endDate = (Date) value;

		if (startDate.after(endDate)) {
			startDateComponent.setValid(false);
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Диапазон времени введен некорректно", null));
		}
	}

}
