package toastwars.server.datamodel.core;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum Type implements IsSerializable {
	TYPE1("Der 1. Typ"), TYPE2("Der 2. Typ"), TYPE3("Der 3. Typ");

	private String description;

	Type(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
}