package Tidee;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class TableItems {
	// Attributes
	private IntegerProperty depNum;
	private StringProperty itemID;
	private StringProperty itemName;
	private DoubleProperty price;
	private StringProperty productDescription;
	private IntegerProperty stockInventory;
	private IntegerProperty stockSecLocation;
	private IntegerProperty storeInventory;
	private IntegerProperty storeSecLocation;
	private IntegerProperty sum;
	
	// Operations
	public TableItems(int depNum,String itemID,String itemName,double price, String productDescription,int stockInventory,int stockSecLocation,
			int storeInventory, int storeSecLocation) {
		/*
		 * Purpose: 
		 */
		this.depNum=new SimpleIntegerProperty(depNum);
		this.itemID=new SimpleStringProperty(itemID);
		this.itemName=new SimpleStringProperty(itemName);
		this.price=new SimpleDoubleProperty(price);
		this.productDescription=new SimpleStringProperty(productDescription);
		this.stockInventory=new SimpleIntegerProperty(stockInventory);
		this.stockSecLocation=new SimpleIntegerProperty(stockSecLocation);
		this.storeInventory=new SimpleIntegerProperty(storeInventory);
		this.storeSecLocation=new SimpleIntegerProperty(storeSecLocation);
		this.sum= new SimpleIntegerProperty(stockInventory+storeInventory);
	}
	public int getSum() {
		return sum.get();
	}
	public void setSum(int sum) {
		this.sum.set(sum);
	}
	public int getDepNum() {
		return depNum.get();
	}

	public void setDepNum(int depNum) {
		this.depNum.set(depNum);
	}

	public String getItemID() {
		return itemID.get();
	}

	public void setItemID(String itemID) {
		this.itemID.set(itemID);
	}

	public String getItemName() {
		return itemName.get();
	}

	public void setItemName(String itemName) {
		this.itemName.set(itemName);
	}

	public double getPrice() {
		return price.get();
	}

	public void setPrice(double price) {
		this.price.set(price);
	}

	public String getProductDescription() {
		return productDescription.get();
	}

	public void setProductDescription(String productDescription) {
		this.productDescription.set(productDescription);
	}

	public int getStockInventory() {
		return stockInventory.get();
	}

	public void setStockInventory(int stockInventory) {
		this.stockInventory.set(stockInventory);
	}

	public int getStockSecLocation() {
		return stockSecLocation.get();
	}

	public void setStockSecLocation(int stockSecLocation) {
		this.stockSecLocation.set(stockSecLocation);
	}

	public int getStoreInventory() {
		return storeInventory.get();
	}

	public void setStoreInventory(int storeInventory) {
		this.storeInventory.set(storeInventory);
	}

	public int getStoreSecLocation() {
		return storeSecLocation.get();
	}

	public void setStoreSecLocation(int storeSecLocation) {
		this.storeSecLocation.set(storeSecLocation);
	}
	
	public IntegerProperty depNumProperty() {return this.depNum;}
	public StringProperty  itemIDProperty() {return this.itemID;}
	public StringProperty  itemNameProperty() {return this.itemName;}
	public DoubleProperty  priceProperty() {return this.price;}
	public StringProperty  productDescriptionProperty() {return this.productDescription;}
	public IntegerProperty stockInventoryProperty() {return this.stockInventory;}
	public IntegerProperty stockSecLocationProperty() {return this.stockSecLocation;}
	public IntegerProperty storeInventoryProperty() {return this.storeInventory;}
	public IntegerProperty storeSecLocationProperty() {return this.storeSecLocation;}
	public IntegerProperty sumProperty() {return this.sum;}
	
	
	
}
