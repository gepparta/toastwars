package toastwars.server.datamodel.core;

import com.google.gwt.user.client.rpc.IsSerializable;
/*
 * @ author Michael Klein
 */
public class Stock implements IsSerializable {

	// Definition der Attribute
	private int		stockTT1		= 0;
	private int		stockTT2		= 0;
	private int		stockTT3		= 0;

	private int		maxStockTT1		= 2500;
	private int		maxStockTT2		= 1500;
	private int		maxStockTT3		= 700;

	private double	stockCostsTT1	= 1.25;
	private double	stockCostsTT2	= 6.25;
	private double	stockCostsTT3	= 16.25;

	private double	totalStockCosts	= 0;

	// Konstruktoren
	public Stock() {

	}

	public Stock(int stockTT1, int stockTT2, int stockTT3,
			double totalStockCosts) {
		this.stockTT1 = stockTT1;
		this.stockTT2 = stockTT2;
		this.stockTT3 = stockTT3;
		this.totalStockCosts = totalStockCosts;
	}

	public int getStock(Type type) {
		if (type == Type.TYPE1)
			return stockTT1;
		else if (type == Type.TYPE2)
			return stockTT2;
		else if (type == Type.TYPE3)
			return stockTT3;

		return -1;
	}

	// Get-Methoden
	public int getMaxStockTT1() {
		return maxStockTT1;
	}

	public int getMaxStockTT2() {
		return maxStockTT2;
	}

	public int getMaxStockTT3() {
		return maxStockTT3;
	}

	public double getStockCostsTT1() {
		return stockCostsTT1;
	}

	public double getStockCostsTT2() {
		return stockCostsTT2;
	}

	public double getStockCostsTT3() {
		return stockCostsTT3;
	}

	public int getStockTT1() {
		return stockTT1;
	}

	public int getStockTT2() {
		return stockTT2;
	}

	public int getStockTT3() {
		return stockTT3;
	}

	public double getTotalStockCosts() {
		return totalStockCosts;
	}

	// Set Methoden

	public void setMaxStockTT1(int maxStockTT1) {
		this.maxStockTT1 = maxStockTT1;
	}

	public void setMaxStockTT2(int maxStockTT2) {
		this.maxStockTT2 = maxStockTT2;
	}

	public void setMaxStockTT3(int maxStockTT3) {
		this.maxStockTT3 = maxStockTT3;
	}

	public void setStockCostsTT1(double stockCostsTT1) {
		this.stockCostsTT1 = stockCostsTT1;
	}

	public void setStockCostsTT2(double stockCostsTT2) {
		this.stockCostsTT2 = stockCostsTT2;
	}

	public void setStockCostsTT3(double stockCostsTT3) {
		this.stockCostsTT3 = stockCostsTT3;
	}

	public void setStockTT1(int stockTT1) {
		this.stockTT1 = stockTT1;
	}

	public void setStockTT2(int stockTT2) {
		this.stockTT2 = stockTT2;
	}

	public void setStockTT3(int stockTT3) {
		this.stockTT3 = stockTT3;
	}

	public void setTotalStockCosts(double stockCostsTotal) {
		this.totalStockCosts = stockCostsTotal;
	}

	// Berechnende Methoden

	// calculateTotalStockCosts berechnet die Gesamten Lagerkosten des Lagers,
	// liefert diesen
	// Wert als double zur�ck und setzt auch das entsprechende Attribut
	// totalStockCosts.
	public double calculateTotalStockCosts() {
		double total;
		total = (this.stockCostsTT1 * this.stockTT1)
				+ (this.stockCostsTT2 * this.stockTT2)
				+ (this.stockCostsTT3 * this.stockTT3);
		this.totalStockCosts = total;

		return total;
	}

	// ReduceStock ist f�r die Reduzierung des Lagers zust�ndig abh�ngig vom
	// Toastertyp zust�ndig.
	// Wenn m�glich wird die komplette Menge (amount) aus dem Lager entfernt und
	// die Methode gibt 0 zur�ck.
	// Falls dies nicht komplett m�glich ist, wird das Lager auf 0 reduziert und
	// die Anzahl der fehlenden Toaster zur�ckgeliefert.
	public int reduceStock(Type typ, int amount) {

		int rest = 0;
		if (typ == Type.TYPE1) {
			if (stockTT1 >= amount) {
				stockTT1 = stockTT1 - amount;
			} else {

				rest = amount - stockTT1;
				stockTT1 = 0;
			}
		}

		else if (typ == Type.TYPE2) {
			if (stockTT2 >= amount) {
				stockTT2 = stockTT2 - amount;
			} else {
				rest = amount - stockTT2;
				stockTT2 = 0;
			}
		}

		else {
			if (stockTT3 >= amount) {
				stockTT3 = stockTT3 - amount;
			} else {
				rest = amount - stockTT3;
				stockTT3 = 0;
			}
		}

		return rest;
	}

	// StockUP implementiert das Einlagern von Toaster anhand des Typen in das
	// Lager.
	// Abh�ngig von der maximalen Lagermenge wird entweder die gesamte
	// �bergebene Menge (amount) eingelagert
	// oder falls nicht alles eingelagert werden kann, wird bis zum Maximun
	// eingelagert und der Rest einfach verworfen.
	public void stockUp(Type typ, int amount) {

		if (typ == Type.TYPE1) {
			if (stockTT1 + amount <= maxStockTT1) {
				stockTT1 = stockTT1 + amount;
			} else {
				stockTT1 = maxStockTT1;
			}
		}

		else if (typ == Type.TYPE2) {
			if (stockTT2 + amount <= maxStockTT2) {
				stockTT2 = stockTT2 + amount;
			} else {
				stockTT2 = maxStockTT2;
			}
		}

		else if (typ == Type.TYPE3) {
			if (stockTT3 + amount <= maxStockTT3) {
				stockTT3 = stockTT3 + amount;
			} else {
				stockTT3 = maxStockTT3;
			}
		}
	}

}// Stock
