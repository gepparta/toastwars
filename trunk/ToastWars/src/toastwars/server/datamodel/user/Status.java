package toastwars.server.datamodel.user;

public enum Status {
	STARTED("Runde gestartet"),EDITED("Daten zwischen gespeichert"),COMPLITED("Runde abgeschlossen");

	private String description;

	Status(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
}
