package toastwars.server.datamodel.core;

public class Stock {

	private int stockTT1;
	private int stockTT2;
	private int stockTT3;
	
	
	private int maxStockTT1;
	private int maxStockTT2;
	private int maxStockTT3;

	private double stockCostsTT1;
	private double stockCostsTT2;
	private double stockCostsTT3;

	private double totalStockCosts;

	public Stock(int stockTT1, int stockTT2, int stockTT3, int maxStockTT1, int maxStockTT2, int maxStockTT3, double stockCostsTT1,
			double stockCostsTT2, double stockCostsTT3, double stockCostsTotal) {

		this.stockTT1 = stockTT1;
		this.stockTT2 = stockTT2;
		this.stockTT3 = stockTT3;
		this.maxStockTT1 = maxStockTT1;
		this.maxStockTT2 = maxStockTT2;
		this.maxStockTT3 = maxStockTT3;
		this.stockCostsTT1 = stockCostsTT1;
		this.stockCostsTT2 = stockCostsTT2;
		this.stockCostsTT3 = stockCostsTT3;
		this.totalStockCosts = stockCostsTotal;
	}

	public int getStockTT1() {
		return stockTT1;
	}

	public void setStockTT1(int stockTT1) {
		this.stockTT1 = stockTT1;
	}

	public int getStockTT2() {
		return stockTT2;
	}

	public void setStockTT2(int stockTT2) {
		this.stockTT2 = stockTT2;
	}

	public int getStockTT3() {
		return stockTT3;
	}

	public void setStockTT3(int stockTT3) {
		this.stockTT3 = stockTT3;
	}
	public int getMaxStockTT1() {
		return maxStockTT1;
	}

	public void setMaxStockTT1(int maxStockTT1) {
		this.maxStockTT1 = maxStockTT1;
	}

	public int getMaxStockTT2() {
		return maxStockTT2;
	}

	public void setMaxStockTT2(int maxStockTT2) {
		this.maxStockTT2 = maxStockTT2;
	}

	public int getMaxStockTT3() {
		return maxStockTT3;
	}

	public void setMaxStockTT3(int maxStockTT3) {
		this.maxStockTT3 = maxStockTT3;
	}
	public double getStockCostsTT1() {
		return stockCostsTT1;
	}

	public void setStockCostsTT1(double stockCostsTT1) {
		this.stockCostsTT1 = stockCostsTT1;
	}

	public double getStockCostsTT2() {
		return stockCostsTT2;
	}

	public void setStockCostsTT2(double stockCostsTT2) {
		this.stockCostsTT2 = stockCostsTT2;
	}

	public double getStockCostsTT3() {
		return stockCostsTT3;
	}

	public void setStockCostsTT3(double stockCostsTT3) {
		this.stockCostsTT3 = stockCostsTT3;
	}

	public double getTotalStockCosts() {
		return totalStockCosts;
	}

	public void setTotalStockCosts(double stockCostsTotal) {
		this.totalStockCosts = stockCostsTotal;
	}


	public void StockUp(Type typ, int amount) throws Exception {

		if (typ == Type.TYPE1) {
			if (stockTT1 + amount <= maxStockTT1) {
				stockTT1 = stockTT1 + amount;
			} else {
				throw new Exception("Unable to stock up");
			}
		}

		else 		if (typ == Type.TYPE2) {
			if (stockTT2 + amount <= maxStockTT2) {
				stockTT2 = stockTT2 + amount;
			} else {
				throw new Exception("Unable to stock up");
			}
		}
		
		else 		if (typ == Type.TYPE3) {
			if (stockTT3 + amount <= maxStockTT3) {
				stockTT3 = stockTT3 + amount;
			} else {
				throw new Exception("Unable to stock up");
			}
		}
	}

	
	
	public void ReduceStock(Type typ, int amount) throws Exception {

		if (typ == Type.TYPE1) {
			if (stockTT1 >= amount) {
				stockTT1 = stockTT1 - amount;
			} else {
				throw new Exception("Out of stock");
			}
		}

		else if (typ == Type.TYPE2) {
			if (stockTT2 >= amount) {
				stockTT2 = stockTT2 - amount;
			} else {
				throw new Exception("Out of stock");
			}
		}

		else {
			if (stockTT3 >= amount) {
				stockTT3 = stockTT3 - amount;
			} else {
				throw new Exception("Out of stock");
			}
		}

	}

	public double calculateTotalStockCosts() {
		double total;
		total = (this.stockCostsTT1 * this.stockTT1)
				+ (this.stockCostsTT2 * this.stockTT2)
				+ (this.stockCostsTT3 * this.stockTT3);
		this.totalStockCosts = total;

		return total;
	}
	




	
}
