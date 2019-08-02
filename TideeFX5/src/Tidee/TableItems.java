package Tidee;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class TableItems {
	private StringProperty depNum;
	private StringProperty itemID;
	private StringProperty itemName;
	private DoubleProperty price;
	private StringProperty productDescription;
	private IntegerProperty stockInventory;
	private IntegerProperty stockSecLocation;
	private IntegerProperty storeInventory;
	private StringProperty storeSecLocation;
	private StringProperty storeSubLocation;
	private IntegerProperty sum;
	/* clearance event */
	private StringProperty eventID;
	private StringProperty eventDate;
	private DoubleProperty markdownPrice;
	/*sale event */
	private IntegerProperty duration;
	/*layout*/
	private StringProperty secName;
	private StringProperty subSecName;
	
	
	/*layout constructor*/
	public TableItems (String secName, String subSecName)
	{
		this.secName = new SimpleStringProperty(secName);
		this.subSecName = new SimpleStringProperty(subSecName);
	}
	
	/*sale event constructor */
	public TableItems(String eventID, String eventDate, String itemID, String secLoc, String subLoc, double markdownPrice, int duration,String itemName)
	{
		this.eventID = new SimpleStringProperty(eventID);
		this.eventDate = new SimpleStringProperty(eventDate);
		this.itemID = new SimpleStringProperty(itemID);
		this.storeSecLocation = new SimpleStringProperty(secLoc);
		this.storeSubLocation = new SimpleStringProperty(subLoc);
		this.markdownPrice = new SimpleDoubleProperty(markdownPrice);
		this.duration = new SimpleIntegerProperty(duration);
		this.itemName= new SimpleStringProperty(itemName);
	}
	
	/*clearance event constructor*/
	public TableItems(String depNum,String itemID,String itemName,double price, String productDescription,int stockInventory,int stockSecLocation,
			int storeInventory, String storeSecLocation,String storeSubLocation)
	{
		this.depNum=new SimpleStringProperty(depNum);
		this.itemID=new SimpleStringProperty(itemID);
		this.itemName=new SimpleStringProperty(itemName);
		this.price=new SimpleDoubleProperty(price);
		this.productDescription=new SimpleStringProperty(productDescription);
		this.stockInventory=new SimpleIntegerProperty(stockInventory);
		this.stockSecLocation=new SimpleIntegerProperty(stockSecLocation);
		this.storeInventory=new SimpleIntegerProperty(storeInventory);
		this.storeSecLocation=new SimpleStringProperty(storeSecLocation);
		this.storeSubLocation=new SimpleStringProperty(storeSubLocation);
		this.sum= new SimpleIntegerProperty(stockInventory+storeInventory);
	}
	public int getSum()
	{
		return sum.get();
	}
	public void setSum(int sum)
	{
		this.sum.set(sum);
	}
	public String getDepNum() {
		return depNum.get();
	}

	public void setDepNum(String depNum) {
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

	public String getStoreSecLocation() {
		return storeSecLocation.get();
	}

	public void setStoreSecLocation(String storeSecLocation) {
		this.storeSecLocation.set(storeSecLocation);
	}
	public String getStoreSubLocation()
	{
		return storeSubLocation.get();
	}
	public void setStoreSubLocation(String storeSubLocation)
	{
		this.storeSubLocation.set(storeSubLocation);
	}
	
	/* clearance */
	public String getEventID()
	{
		return eventID.get();
	}
	public void setEventID(String eventID)
	{
		this.eventID.set(eventID);
	}
	public String getEventDate()
	{
		return eventDate.get();
	}
	public void setEventDate(String eventDate)
	{
		this.eventDate.set(eventDate);
	}
	public Double getMarkdownPrice()
	{
		return markdownPrice.get();
	}
	public void setMarkdownPrice(Double markdownPrice)
	{
		this.markdownPrice.set(markdownPrice);
	}
	
	/* sales event*/
	public void setDuration(int duration)
	{
		this.duration.set(duration);
	}
	public int getDuration()
	{
		return duration.get();
	}
	
	/*layout*/
	public void setSecName(String secName)
	{
		this.secName.set(secName);
	}
	public String getSecName()
	{
		return secName.get();
	}
	public void setSubSecName(String subSecName)
	{
		this.subSecName.set(subSecName);
	}
	public String getSubSecName()
	{
		return subSecName.get();
	}
	
	

	public StringProperty depNumProperty() {return this.depNum;}
	public StringProperty  itemIDProperty() {return this.itemID;}
	public StringProperty  itemNameProperty() {return this.itemName;}
	public DoubleProperty  priceProperty() {return this.price;}
	public StringProperty  productDescriptionProperty() {return this.productDescription;}
	public IntegerProperty stockInventoryProperty() {return this.stockInventory;}
	public IntegerProperty stockSecLocationProperty() {return this.stockSecLocation;}
	public IntegerProperty storeInventoryProperty() {return this.storeInventory;}
	public StringProperty storeSecLocationProperty() {return this.storeSecLocation;}
	public StringProperty storeSubLocationProperty() {return this.storeSubLocation;}
	public IntegerProperty sumProperty() {return this.sum;}
	/*clearance*/
	public StringProperty eventIDProperty() {return this.eventID;}
	public StringProperty eventDateProperty() {return this.eventDate;}
	public DoubleProperty markdownPriceProperty() {return this.markdownPrice;}
	/*sales*/
	public IntegerProperty durationProperty() {return this.duration;}
	/*layout*/
	public StringProperty secNameProperty( ) {return this.secName;}
	public StringProperty subSecNameProperty( ) {return this.subSecName;}
	
}

