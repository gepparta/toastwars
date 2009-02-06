package toastwars.client.ui.group;

import com.gwtext.client.core.Position;
import com.gwtext.client.widgets.HTMLPanel;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.TabPanel;

public class InfoPanel extends Panel {

	private static InfoPanel	infoPanel;
	private String				intro		= "Aus dem Newsticker: <br><br> "
													+ "Alter Vorstand &#132;zum Mond geschossen&#147; - F&uuml;hrungswechsel bei der ToastAG <br><br> "
													+ "Als letzte Konsequenz der Schmiergeldaff&auml;re, in die neben der Handelskammer auch Teile des Vorstandes der ToastAG verwickelt waren, wurde dieser nun vom Aufsichtsrat abgew&auml;hlt. F&uuml;r die Nachfolge wurde eine Task Force gegr&uuml;ndet, die aus noch unbekannten Wirtschaftswissenschaftlern besteht.<br><br>"
													+ "Ob diese Entscheidung Einfluss auf die wirtschaftliche Lage des Unternehmens hat, bleibt abzuwarten. In den unendlichen Weiten des Toastermarktes konnte sich in der Vergangenheit kein Unternehmen deutlich von den anderen differenzieren. Jedoch ist zu erwarten, dass die Konkurrenten versuchen werden, ihre Marktanteile mit Macht zu vermehren.<br><br>"
													+ "Die ToastAG konnte in der letzten Periode ein Kapital von 100.000 &euro; ausweisen.";
	private String				game		= "&#132;Toast Wars&#147; ist ein Planspiel, welches von Teilen des Kurses WWI06F der Berufsakademie Mannheim erstellt wurde. Es handelt sich dabei um eine rundenbasierte Wirtschaftssimulation, bei der mehrere Gruppen an gemeinsamen M&auml;rkten gegeneinander spielen. Die Spieler sollen lernen wirtschaftspolitische Entscheidungen zu treffen. <br>"
													+ "Es ist eine Obergrenze von zehn Gruppen pro Spiel und eine maximale Rundenanzahl von 15 Runden vorgesehen.";
	private String				production	= "Die Produktion der ToastAG findet nicht auf eigenen Maschinen statt. Stattdessen werden die Maschinen f&uuml;r jede Periode angemietet, um Kosten zu sparen. Es ist jedoch m&ouml;glich, die Anzahl der zu produzierenden Toaster anzugeben. Werden mehr Toaster produziert als abgesetzt, werden die restlichen auf Lager gelegt. <br>"
													+ "<br>Produktpolitik und Produktbeschreibung<br><br>"
													+ "Die ToastAG vertreibt den Millennium Toaster, ein g&uuml;nstiges Ger&auml;t f&uuml;r den Discountmarkt. Er zeichnet sich durch seine geringen Kosten in Herstellung und Lagerung aus. <br>"
													+ "Zur Weiterentwicklung und Werbung des Toasters m&uuml;ssen mindestens 5000 &euro; investiert werden.";
	private String				type		= "Im Laufe des Spieles ist es m&ouml;glich, in die Entwicklung neuer Toastertypen zu investieren. Diese neuen Toaster beziehen sich auf den Standart- bzw. Luxusmarkt. Damit stehen diese neuen Produkte nicht in Konkurrenz mit dem Millennium Toaster. <br><br>"
													+ "TIE-Toaster<br>"
													+ "Dieser Toaster ist in Herstellung, Lagerung, Forschung und Werbung teurer als der Millenniums Toaster. Jedoch sind auch die m&ouml;glichen Gewinne h&ouml;her, so dass sich Risiko und Chancen die Wage halten.<br>"
													+ "Der TIE-Toaster ist f&uuml;r den Elektro-Fachhandel gedacht.<br><br>"
													+ "Startoaster<br>"
													+ "Das Schlachtschiff der ToastAG! Dieser Luxustoaster besticht durch eine hohe Qualit&auml;t, formsch&ouml;nen Design und einem hohen &Ouml;kologieindex. Daher ist das weitere Verbessern dieser Werte auch eine teure Investition.<br>"
													+ "Auch die Werbung in den verschiedenen Medien will aufwendig und damit kostspielig gestaltet werden.";
	private String				marketing	= "Das beste Produkt verkauft sich nicht, wenn potentielle Kunden es nicht kennen. Somit ist es ratsam, Geld in Werbung zu investieren.<br><br>"
													+ "Zeitungswerbung <br>"
													+ "Die Zeitungswerbung ist eine preisg&uuml;nstige M&ouml;glichkeit, Kunden auf sich aufmerksam zu machen. &uuml;ber Anzeigen Fachzeitschriften und Werbebeilagen in Tageszeitungen k&ouml;nnen verschiedene Zielgruppen erreicht und beworben werden. Es ist zu erwarten, dass sich bereits geringe Summen positiv auf den Absatz auswirken. Allerdings f&uuml;hrt eine &#132;Anzeigenflut&#147; nicht unbedingt zu steigenden Abs&auml;tzen. Es ist davon auszugehen, dass die positive Entwicklung fr&uuml;her oder sp&auml;ter stagniert und kaum noch Neukunden geworben werden k&ouml;nnen.<br><br>"
													+ "Radiowerbung <br>"
													+ "Die Werbung im Radio ist je nach Tageszeit unterschiedlich teuer. Daher ist davon auszugehen, dass geringe Summen nur zu Spots im Nachtprogramm f&uuml;hren. Diese erreichen somit weniger potentielle Kunden. Zus&auml;tzliche Investitionen verbessern allerdings die Sendezeit des Spotts. Damit erh&ouml;ht sich auch die H&ouml;rerzahl und damit die Zahl der potentiellen Neukunden. Doch wie bei der Zeitungswerbung ist fr&uuml;her oder sp&auml;ter eine Stagnation zu erwarten.<br><br>"
													+ "TV-Werbung <br>"
													+ "Ähnlich wie beim Radio ist auch der Erfolg von TV-Werbung an den Ausstrahlungszeitpunkt gekoppelt. Zus&auml;tzlich ist noch der TV-Sender relevant. So bringt eine niedrige Investition einen Spott im Nachtprogramm eines Regionalsenders ein, w&auml;hrend eine h&ouml;here Summe zu einem Spott auf einem gro&szlig;en Privatsenders zur Prime Time f&uuml;hren kann.<br><br>"
													+ "Marktforschungsbericht <br>"
													+ "Was macht die Konkurrenz? Um diese Frage beantworten zu k&ouml;nnen, ist es m&ouml;glich, einen Marktforschungsbericht anzufordern. In diesem werden Daten wie Kapital, Preis, Marktanteil u.a. der anderen Unternehmen angegeben. Allerdings ist dieser Bericht nicht umsonst zu bekommen.";
	private String				research	= "Das beste Marketing l&auml;uft ins Leere, wenn sich das Produkt nicht auch durch besondere Eigenschaften von der Konkurrenz absetzen kann. Dazu existieren in der ToastAG folgende Forschungsgebiete. <br><br>"
													+ "Qualit&auml;t<br>"
													+ "Die Qualit&auml;t eines Produktes schl&auml;gt sich in den verwendeten Materialien und der Verarbeitung wieder. Durch eine hohe Qualit&auml;t erh&ouml;ht sich beispielswei&szlig;e die Langlebigkeit der Produkte, und damit die Kundenzufriedenheit.<br><br>"
													+ "Design<br>"
													+ "Gutes Aussehen ist eben doch wichtig! Laut Marktforschern werden stilvolle, gut designte Produkte mehr gekauft als rein funktionelle, unsch&ouml;ne K&auml;sten. Sie sollten diesen Einfluss auf das  Kaufverhalten ihrer Kunden nicht untersch&auml;tzen!<br><br>"
													+ "&Ouml;kologie<br>"
													+ "In Zeiten steigender Energiepreise achten immer mehr Kunden auf stromspaarende Ger&auml;te. Somit sollten sie auch diesen Forschungsbereich nicht au&szlig;er acht lassen, um weitere Kunden werben zu k&ouml;nnen.";
	private String				stock		= "Im Lager befinden sich produzierte Toaster, die zum Ende einer Periode nicht verkauft werden konnten. Jedoch k&ouml;nnen nur eine bestimmte Menge pro Toastertyp eingelagert werden.<br>"
													+ "Jeder gelagerte Toaster verursacht Kosten pro Periode. Die H&ouml;he dieser Kosten ist vom Toastertyp abh&auml;ngig.<br>"
													+ "Liegen Toaster eines bestimmten Typs im Lager und wird f&uuml;r diesen Toaster geforscht, fliest ein Teil des investierten Forschungssumme in die Aufr&uuml;stung der eingelagerten Toaster.<br><br>";
	private String				scenario	= "Im Laufe des Spiels werden Sie auf einige Szenarios treffen, welche die Marktsituation verbessern oder verschlechtern werden. Ein Gegensteuern ist wichtig, doch sollten sie pr&uuml;fen, ob sie tats&auml;chlich jeden Trend verfolgen wollen.";

	private InfoPanel() {
		setTitle("Anleitung");
		setPaddings(0, 15, 0, 0);

		TabPanel tabPanel = new TabPanel();
		tabPanel.setMargins(0);
		tabPanel.setSize(960, 390);
		tabPanel.setStyle("border-color: gold; color: gold; "
				+ "text-align: center;");
		tabPanel.setTabPosition(Position.BOTTOM);

		HTMLPanel introPanel = new HTMLPanel(intro, 15);
		introPanel.setTitle("Einf&uuml;hrung");
		introPanel.setStyle("background: black;");
		HTMLPanel gamePanel = new HTMLPanel(game, 15);
		gamePanel.setTitle("Planspielbeschreibung");
		gamePanel.setStyle("background: black;");

		TabPanel departmentPanel = createDepartmentPanel();

		HTMLPanel scenarioPanel = new HTMLPanel(scenario, 15);
		scenarioPanel.setTitle("Szenarios");
		scenarioPanel.setStyle("background: black;");

		tabPanel.add(introPanel);
		tabPanel.add(gamePanel);
		tabPanel.add(departmentPanel);
		tabPanel.add(scenarioPanel);

		add(tabPanel);
	}

	private TabPanel createDepartmentPanel() {
		TabPanel departmentPanel = new TabPanel();
		departmentPanel.setTitle("Unternehmensbereiche");
		departmentPanel.setTabPosition(Position.BOTTOM);
		departmentPanel.setStyle("background: black;");

		HTMLPanel productionPanel = new HTMLPanel(production, 15);
		productionPanel.setTitle("Produktion");
		HTMLPanel typePanel = new HTMLPanel(type, 15);
		typePanel.setTitle("Toastertypen");
		HTMLPanel marketingPanel = new HTMLPanel(marketing, 15);
		marketingPanel.setTitle("Marketing");
		marketingPanel.setAutoScroll(true);
		HTMLPanel researchPanel = new HTMLPanel(research, 15);
		researchPanel.setTitle("Forschung &amp; Entwicklung");
		HTMLPanel stockPanel = new HTMLPanel(stock, 15);
		stockPanel.setTitle("Lager");

		departmentPanel.add(productionPanel);
		departmentPanel.add(typePanel);
		departmentPanel.add(marketingPanel);
		departmentPanel.add(researchPanel);
		departmentPanel.add(stockPanel);

		return departmentPanel;
	}

	public static InfoPanel getInstance() {
		if (infoPanel == null)
			infoPanel = new InfoPanel();
		return infoPanel;
	}
}
