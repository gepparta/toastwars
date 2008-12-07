package toastwars.server.datamodel.core;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum Type implements IsSerializable {
	TYPE1("Der 1. Typ",10000), TYPE2("Der 2. Typ",20000), TYPE3("Der 3. Typ",30000);

	private String description;
	private int marketVolume;

	Type(String description, int marketVolume) {
		this.description = description;
		this.marketVolume = marketVolume;
	}

	public String getDescription() {
		return this.description;
	}

	public int getMarketVolume()
	{
		return marketVolume;
	}

	public void setMarketVolume(int marketVolume)
	{
		this.marketVolume = marketVolume;
	}
}