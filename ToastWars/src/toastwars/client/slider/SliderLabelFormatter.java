package toastwars.client.slider;

/*
 * Author: Waldemar Geppart
 * */

import toastwars.client.slider.SliderBar.LabelFormatter;

public class SliderLabelFormatter implements LabelFormatter {

	private String	postfix;

	public SliderLabelFormatter(String postfix) {
		this.postfix = postfix;
	}

	public String formatLabel(SliderBar slider, double value) {
		if (value == 0)
			return 0 + "";
		return (int) value + postfix;
	}
}
