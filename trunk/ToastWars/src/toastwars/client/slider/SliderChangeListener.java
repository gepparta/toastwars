package toastwars.client.slider;

import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.Widget;
import com.gwtext.client.widgets.form.NumberField;

public class SliderChangeListener implements ChangeListener {

	private NumberField	field;
	private SliderBar	slider;

	public SliderChangeListener(NumberField field, SliderBar slider) {
		this.field = field;
		this.slider = slider;
	}

	public void onChange(Widget sender) {
		field.setValue(slider.getCurrentValue());
	}
}
