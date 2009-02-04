package toastwars.server.datamodel.user;

import com.google.gwt.user.client.rpc.IsSerializable;
/*
 * @ author Alexander Geppart
 */
public enum Status implements IsSerializable
{
	STARTED("Runde gestartet"), EDITED("Daten zwischen gespeichert"), COMPLETED("Runde abgeschlossen"), INACTIVE("ABGEBRANNT!");

	private String description;

	private Status(String description)
	{
		this.description = description;
	}

	public String getDescription()
	{
		return this.description;
	}
}
