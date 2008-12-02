package toastwars.server.datamodel.core;

public enum Type {
	type1("Der 1. Typ"),type2("Der 2. Typ"),type3("Der 3. Typ");

	private String description;

	Type(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
}