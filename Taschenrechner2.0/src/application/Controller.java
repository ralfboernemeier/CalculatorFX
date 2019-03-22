package application;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Controller {

	// Fields

	@FXML
	Text resultLabel;
	@FXML
	Text errorLabel;
	boolean isTypingNumber = false;
	int countButtonTapped = 0;
	double firstNumber = 0;
	double secondNumber = 0;
	String operation = "";
	Model model = new Model();

	// Constructor

	// Methods

	public void numberTapped(ActionEvent event) {
		String value = ((Button) event.getSource()).getText();
		if (countButtonTapped <= 10) {
			if (isTypingNumber) {
				resultLabel.setText(resultLabel.getText() + value);
				countButtonTapped += 1;
			} else {
				resultLabel.setText(value);
				isTypingNumber = true;
				countButtonTapped += 1;
			}
		}
	}

	public void calculationTapped(ActionEvent event) {
		isTypingNumber = false;
		firstNumber = Double.parseDouble(resultLabel.getText());
		operation = ((Button) event.getSource()).getText();
		countButtonTapped = 0;
	}

	public void equalsTapped(ActionEvent event) {
		isTypingNumber = false;
		countButtonTapped = 0;
		secondNumber = Double.parseDouble(resultLabel.getText());
		if (!(secondNumber == 0)) {
			double result = model.calculate(firstNumber, secondNumber, operation);
			BigDecimal bdResult = new BigDecimal(result, MathContext.DECIMAL32);
			resultLabel.setText(bdResult.toEngineeringString());
//			if (getDecimalPlace(result) > 4) {
//				NumberFormat numberFormat = new DecimalFormat("0.0000");
//				// NumberFormat numberFormat = new DecimalFormat("##0.0000E0");
//				numberFormat.setRoundingMode(RoundingMode.DOWN);
//				resultLabel.setText(String.valueOf(numberFormat.format(result)));
//			} else {
//				resultLabel.setText(String.valueOf(result));
//			}
		} else {
			errorLabel.setText("ERROR-Division by 0");
		}
		
	}

	public void restartTapped(ActionEvent event) {
		resultLabel.setText("");
		errorLabel.setText("");
		firstNumber = 0;
		secondNumber = 0;
		countButtonTapped = 0;
	}

	public int getDecimalPlace(double d) {
		String s = Double.toString(d);
		s = s.substring(s.indexOf(".") + 1);
		return s.length();
	}
}
