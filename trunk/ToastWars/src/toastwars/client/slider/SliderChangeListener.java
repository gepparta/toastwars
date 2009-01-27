package toastwars.client.slider;

import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.Widget;
import com.gwtext.client.widgets.form.NumberField;

public class SliderChangeListener implements ChangeListener {

	private NumberField	field;
	private SliderBar	slider;
	private NumberField	capital;
	private double		previousValue;

	public SliderChangeListener(NumberField field, SliderBar slider,
			NumberField capital) {
		this.field = field;
		this.slider = slider;
		this.capital = capital;
		previousValue = slider.getCurrentValue();
	}

	public void onChange(Widget sender) {

		double delta = slider.getCurrentValue() - previousValue;

		field.setValue(slider.getCurrentValue());
		if (!field.getName().equals("price")
				&& !field.getName().equals("amount")) {
			double newCapital = capital.getValue().floatValue() - delta;
			if (newCapital >= 0) {
				capital.setValue(newCapital);
				capital.fireEvent("change");
			} else
				slider.setCurrentValue(previousValue);
		}
		previousValue = slider.getCurrentValue();
	}
}
