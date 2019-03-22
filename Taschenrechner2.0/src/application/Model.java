package application;


public class Model {

	// Fields

	// Constructor

	// Methods

	public double calculate(double number1, double number2, String operation) {
		switch (operation) {
		case "+":
			return number1 + number2;
		case "-":
			return number1 - number2;
		case "*":
			return number1 * number2;
		case "/":
			if (number2 == 0) {
				return 0;
			}
			return number1 / number2;
		}
		return 0;
	}
}
