package javaTestter;

public class CarCenter {

	public static void main(String[] args) {
		Car car = new Car();
		System.out.println(car.getCarColor());
		car.setCarColor("Black");
		System.out.println(car.getCarColor());
	}

}
