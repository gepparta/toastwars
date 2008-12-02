package toastwars.server.datamodel.core;

public class Toaster {

	private Double price;

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	private Integer marketing;

	public Integer getMarketing() {
		return marketing;
	}

	public void setMarketing(Integer marketing) {
		this.marketing = marketing;
	}

	private Integer research;

	public Integer getResearch() {
		return research;
	}

	public void setResearch(Integer research) {
		this.research = research;
	}

	private Integer round;

	public Integer getRound() {
		return round;
	}

	public void setRound(Integer round) {
		this.round = round;
	}

	private Type type;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	// //////////////////Konstruktor//////////////////////////

	public Toaster(Double price, Integer marketing, Integer research,
			Integer round, Type type) {

		this.price = price;
		this.marketing = marketing;
		this.research = research;
		this.round = round;
		this.type = type;

	}

}
