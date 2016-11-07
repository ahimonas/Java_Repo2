public final class Rectangle {

	private double height;
	private double width;

	public Rectangle() {
		setHeight(0.0);
		setWidth(0.0);
	}

	public Rectangle(double theHeight, double theWidth) {
		setHeight(theHeight);
		setWidth(theWidth);
	}

	public void setHeight(double theHeight) {
		height = (theHeight > 0.0 && theHeight < 20.0 ? theHeight : 0.0);
	}

	public void setWidth(double theWidth) {
		width = (theWidth > 0 && theWidth < 20.0 ? theWidth : 0.0);
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public double calculatePerimeter() {
		return 2 * height + 2 * width;
	}

	public double calculateArea() {
		return height * width;
	}

}
