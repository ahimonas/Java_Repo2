/**
 * Java 2571 - Car & Driver Homework 8
 * 
 * @author Brandon Dupy
 * @version 4.10.16
 */

public class Car {
	private int year;
	private String make;
	private String model;
	private Driver driver;

	public Car() {

	}

	public Car(int year, String make, String model, Driver driver) {
		this.year = year;
		this.make = make;
		this.model = model;
		this.driver = driver;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

}