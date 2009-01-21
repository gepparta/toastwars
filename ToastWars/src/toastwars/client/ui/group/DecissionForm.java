package toastwars.client.ui.group;

import java.util.ArrayList;

import toastwars.client.Controller;
import toastwars.client.slider.SliderBar;
import toastwars.client.slider.SliderChangeListener;
import toastwars.client.slider.SliderLabelFormatter;
import toastwars.server.datamodel.core.Company;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Type;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.Status;

import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.Field;
import com.gwtext.client.widgets.form.FieldSet;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.event.FieldListenerAdapter;
import com.gwtext.client.widgets.layout.HorizontalLayout;

public class DecissionForm extends Panel {

	private Button[]				buttons;
	private ArrayList<NumberField>	fields	= new ArrayList<NumberField>();
	private ArrayList<SliderBar>	sliders	= new ArrayList<SliderBar>();
	private NumberField				capital;
	private Company					company;
	private Toaster					toaster;
	private Type					type;
	private Panel					newPanel;
	private ArrayList<Toaster>		toasterList;

	public DecissionForm(Button[] buttons, NumberField capital, Object o,
			ArrayList<Toaster> toasterList) {

		setBorder(false);

		this.toasterList = toasterList;
		company = ((Group) Controller.getInstance().getUser()).getCompany();

		this.buttons = buttons;
		this.capital = capital;

		if (o instanceof Toaster) {
			toaster = (Toaster) o;
			createContent();
		} else if (o instanceof Type) {
			type = (Type) o;
			createEmptyContent();
		}
	}

	private void createEmptyContent() {
		newPanel = new Panel();
		newPanel.setBorder(false);
		newPanel.setButtonAlign(Position.CENTER);
		newPanel.addButton(new Button(type.getDescription() + " entwickeln",
				new ButtonListenerAdapter() {
					public void onClick(Button button, EventObject e) {
						super.onClick(button, e);
						toaster = new Toaster();
						toaster.setType(type);
						try {
							toaster.setPrice((type.getMinPrice() + type
									.getMaxPrice()) / 2);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						company.getToasterList().add(toaster);
						toasterList.add(toaster);
						createContent();
					}
				}));

		add(newPanel);
	}

	private void createContent() {
		if (newPanel != null)
			remove(newPanel, true);

		createFields();

		// add marketing and research field sets
		Panel horPanel = new Panel();
		horPanel.setLayout(new HorizontalLayout(15));
		horPanel.setPaddings(0);

		FieldSet marketingFS = createMarketingFieldSet();
		horPanel.add(marketingFS);

		FieldSet researchFS = createResearchFieldSet();
		horPanel.add(researchFS);

		add(createTopPanel());
		add(horPanel);
		add(createStockField());

		Status status = ((Group) Controller.getInstance().getUser())
				.getStatus();
		if (status == Status.COMPLETED || status == Status.INACTIVE)
			disableSliders();

		doLayout();
	}

	private Panel createTopPanel() {
		type = toaster.getType();

		Panel topPanel = new Panel();
		topPanel.setLayout(new HorizontalLayout(0));
		topPanel.setWidth(930);
		topPanel.setStyle("text-align: center;");

		// add price field
		SliderBar priceSlider = new SliderBar(type.getMinPrice(), type
				.getMaxPrice());
		int ticks = 15;
		if (type == Type.TYPE3)
			ticks = 7;
		int stepSize = (type.getMaxPrice() - type.getMinPrice()) / ticks;
		configureSlider(priceSlider, stepSize, ticks, 4, " &euro;", fields
				.get(0), toaster.getPrice());
		Panel pricePanel = createSliderField(fields.get(0), priceSlider);
		pricePanel.setMargins(0, 0, 35, 0);
		topPanel.add(pricePanel);

		// add amount field
		SliderBar amountSlider = new SliderBar(0, type.getMarketVolume());
		configureSlider(amountSlider, type.getMarketVolume() / 20, 20, 2,
				" ME", fields.get(1), toaster.getProduction());
		Panel amountPanel = createSliderField(fields.get(1), amountSlider);
		amountPanel.setMargins(0, 5, 0, 0);
		topPanel.add(amountPanel);

		return topPanel;
	}

	private void createFields() {
		// create all fields and put them in a list
		fields.add(createNumberField("Preis", "price", toaster.getPrice(),
				toaster.getType().getMinPrice(), toaster.getType()
						.getMaxPrice(), true));
		fields.add(createNumberField("Menge", "amount",
				toaster.getProduction(), 0,
				toaster.getType().getMarketVolume(), false));
		fields.add(createNumberField("Zeitung", "mag", toaster
				.getNewspaperInvestment(), 0, 0, false));
		fields.add(createNumberField("Radio", "radio", toaster
				.getRadioInvestment(), 0, 0, false));
		fields.add(createNumberField("TV", "tv", toaster.getTvInvestment(), 0,
				0, false));
		fields.add(createNumberField("Qualit&auml;t", "quality", toaster
				.getQualityInvestment(), 0, 0, false));
		fields.add(createNumberField("Design", "design", toaster
				.getDesignInvestment(), 0, 0, false));
		fields.add(createNumberField("&Ouml;kologie", "ecology", toaster
				.getEcologyInvestment(), 0, 0, false));
	}

	private FieldSet createMarketingFieldSet() {
		// add marketing fields
		FieldSet marketingFS = new FieldSet("Marketing");
		marketingFS.setPaddings(5, 5, 5, 0);

		SliderBar magSlider = new SliderBar(0, 100000);
		configureSlider(magSlider, 5000, 20, 2, " &euro;", fields.get(2),
				toaster.getNewspaperInvestment());
		marketingFS.add(createSliderField(fields.get(2), magSlider));

		SliderBar radioSlider = new SliderBar(0, 100000);
		configureSlider(radioSlider, 10000, 10, 2, " &euro;", fields.get(3),
				toaster.getRadioInvestment());
		marketingFS.add(createSliderField(fields.get(3), radioSlider));

		SliderBar tvSlider = new SliderBar(0, 120000);
		configureSlider(tvSlider, 20000, 6, 2, " &euro;", fields.get(4),
				toaster.getTvInvestment());
		marketingFS.add(createSliderField(fields.get(4), tvSlider));

		return marketingFS;
	}

	private FieldSet createResearchFieldSet() {
		// add research fields
		FieldSet researchFS = new FieldSet("Forschung und Entwicklung");
		researchFS.setPaddings(5, 5, 5, 0);

		SliderBar qSlider = new SliderBar(0, 100000);
		configureSlider(qSlider, 5000, 20, 2, " &euro;", fields.get(5), toaster
				.getQualityInvestment());
		researchFS.add(createSliderField(fields.get(5), qSlider));

		SliderBar designSlider = new SliderBar(0, 100000);
		configureSlider(designSlider, 5000, 20, 2, " &euro;", fields.get(6),
				toaster.getDesignInvestment());
		researchFS.add(createSliderField(fields.get(6), designSlider));

		SliderBar ecoSlider = new SliderBar(0, 100000);
		configureSlider(ecoSlider, 5000, 20, 2, " &euro;", fields.get(7),
				toaster.getEcologyInvestment());
		researchFS.add(createSliderField(fields.get(7), ecoSlider));

		return researchFS;
	}

	private Panel createSliderField(NumberField field, SliderBar slider) {
		Panel panel = new Panel();
		panel.setLayout(new HorizontalLayout(13));
		panel.setPaddings(0);

		FormPanel form = new FormPanel();
		form.setBorder(false);
		form.setWidth(110);
		form.setLabelWidth(50);
		form.add(field);

		panel.add(form);

		if (slider != null)
			panel.add(slider);

		return panel;
	}

	private void configureSlider(SliderBar slider, int stepSize, int ticks,
			int labels, String postfix, NumberField field, Number value) {
		slider.setStepSize(stepSize);
		slider.setCurrentValue(value.doubleValue());
		slider.setNumTicks(ticks);
		slider.setNumLabels(labels);
		slider.setLabelFormatter(new SliderLabelFormatter(postfix));
		slider.addChangeListener(new SliderChangeListener(field, slider,
				capital));
		sliders.add(slider);
	}

	private NumberField createNumberField(String text, String name,
			Number value, int minvalue, int maxvalue, boolean decimal) {
		NumberField numField = new NumberField(text, name, 55, value
				.floatValue());

		numField.setAllowNegative(false);

		if (maxvalue != 0)
			numField.setMaxValue(maxvalue);
		numField.setMinValue(minvalue);
		numField.setMinText("Der minimale Wert f&uuml;r dieses Feld ist: "
				+ minvalue);
		numField.setMaxText("Der maximale Wert f&uuml;r dieses Feld ist: "
				+ maxvalue);
		numField.setNanText("Ung&uuml;ltige Zahl");

		numField.setDecimalPrecision(2);
		numField.setDecimalSeparator(",");
		numField.setAllowDecimals(decimal);

		numField.setStyle("text-align:right");

		numField.addListener(new FieldListenerAdapter() {
			public void onInvalid(Field field, String msg) {
				super.onInvalid(field, msg);
				for (Button button : buttons)
					button.disable();
			}

			public void onValid(Field field) {
				super.onValid(field);
				if (areAllFieldsValid()) {
					for (Button button : buttons)
						button.enable();
				}
			}
		});

		if (!name.equals("price"))
			numField.setReadOnly(true);

		return numField;
	}

	private Panel createStockField() {
		Panel panel = new Panel();
		panel.setWidth(930);
		panel.setStyle("text-align: center;");
		panel.setBorder(false);

		FormPanel form = new FormPanel();
		form.setWidth(180);
		form.setLabelWidth(100);
		form.setBorder(false);
		form.setPaddings(7);

		NumberField stockField = new NumberField("Lagerbestand", "stock", 55,
				company.getStock().getStock(type));
		stockField.setStyle("text-align: right;");
		stockField.setReadOnly(true);
		stockField.setAllowDecimals(false);
		fields.add(stockField);

		form.add(stockField);
		panel.add(form);

		return panel;
	}

	private boolean areAllFieldsValid() {
		for (NumberField field : fields) {
			if (!field.isValid())
				return false;
		}

		return true;
	}

	public void updateToasterData() {
		if (toaster != null) {
			try {
				toaster.setPrice(fields.get(0).getValue().doubleValue());
				toaster.setProduction(fields.get(1).getValue().intValue());
				toaster.setNewspaperInvestment(fields.get(2).getValue()
						.doubleValue());
				toaster.setRadioInvestment(fields.get(3).getValue()
						.doubleValue());
				toaster.setTvInvestment(fields.get(4).getValue().doubleValue());
				toaster.setQualityInvestment(fields.get(5).getValue()
						.doubleValue());
				toaster.setDesignInvestment(fields.get(6).getValue()
						.doubleValue());
				toaster.setEcologyInvestment(fields.get(7).getValue()
						.doubleValue());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void disableSliders() {
		if (toaster != null) {
			fields.get(0).setReadOnly(true);
			fields.get(1).setReadOnly(true);
			for (SliderBar slider : sliders) {
				slider.setVisible(false);
			}
		}
	}
}
