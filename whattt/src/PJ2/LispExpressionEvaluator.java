package PJ2;

import java.util.Scanner;

public class LispExpressionEvaluator {
	// Current input Lisp expression
	private String myExp;

	private LinkedStack<Object> myStack;
	private LinkedStack<Double> opps;

	public LispExpressionEvaluator() {
		myExp = "";
		myStack = new LinkedStack<Object>();
		opps = new LinkedStack<Double>();
	}

	public LispExpressionEvaluator(String someR) {
		myExp = someR;
		myStack = new LinkedStack<Object>();
		opps = new LinkedStack<Double>();
	}

	public void reset(String someR) {

		if (someR == null) {
			throw new LispExpressionException();
		}
		myExp = someR;
		myStack.clear();
		opps.clear();

	}

	private void evaluateCurrentOperation() {
		double myRes = 0;
		if (myStack.empty()) {
			throw new LispExpressionException("Error, the stack is empty");
		}
		Object myO = myStack.pop();

		while (myO instanceof String) {
			double value = Double.parseDouble(String.valueOf(myO));

			opps.push(value);

			if (myStack.empty()) {
				throw new LispExpressionException("Error1");
			} else {
				myO = myStack.pop();
			}
		}

		try {
			String aToken = myO.toString();
			char its = aToken.charAt(0);
			switch (its) {
			case '+':
				if (opps == null || opps.empty()) {
					throw new LispExpressionException("error.add");
				}
				while (!opps.empty()) {
					myRes += opps.pop();
				}
				myStack.push(String.valueOf(myRes));
				break;

			case '-':
				if (opps == null || opps.empty()) {
					throw new LispExpressionException("error.sub");
				}
				myRes = opps.pop();
				if (opps.empty()) {
					myRes = -myRes;
					myStack.push(String.valueOf(myRes));
				} else {
					while (!opps.empty()) {
						myRes -= opps.pop();
					}
					myStack.push(String.valueOf(myRes));
				}
				break;

			case '*':
				if (opps == null || opps.empty()) {
					throw new LispExpressionException("error.mult");
				}
				myRes = 1;
				while (!opps.empty()) {
					myRes *= opps.pop();
				}
				myStack.push(String.valueOf(myRes));

				break;

			case '/':
				if (opps == null || opps.empty()) {
					throw new LispExpressionException("error.div");
				}
				myRes = opps.pop();

				if (opps.empty()) {
					myRes = 1 / myRes;
					myStack.push(String.valueOf(myRes));
				} else {
					while (!opps.empty()) {
						myRes /= opps.pop();
					}
					myStack.push(String.valueOf(myRes));
				}
				break;

			case '(':
			default:
				throw new LispExpressionException(its
						+ " is not a legal expression operator");

			}
		} catch (LispExpressionException e) {
			throw new LispExpressionException(e.getMessage());
		}
	}

	public double evaluate() {
		Scanner someEx = new Scanner(myExp);
		someEx = someEx.useDelimiter("\\s*");
		while (someEx.hasNext()) {
			if (someEx.hasNextInt()) {
				String dataString = someEx.findInLine("\\d+");
				myStack.push(dataString);
			} else {
				try {
					String aToken = someEx.next();
					char item = aToken.charAt(0);
					switch (item) {
					case '(':
						aToken = someEx.next();
						item = aToken.charAt(0);
						switch (item) {
						case '+':
							myStack.push(item);
							break;
						case '-':
							myStack.push(item);
							break;
						case '*':
							myStack.push(item);
							break;
						case '/':
							myStack.push(item);
							break;
						default:
							throw new LispExpressionException(item
									+ " is not a legal expression operator");
						}
						break;

					case ')':
						evaluateCurrentOperation();
						break;

					default:
						throw new LispExpressionException(item
								+ " is not a legal expression operator");
					}
				} catch (LispExpressionException e) {
					throw new LispExpressionException(e.getMessage());
				}
			}
		}
		double result = Double.parseDouble(String.valueOf(myStack.pop()));
		if (!myStack.empty()) {
			throw new LispExpressionException(
					"This stack still has values, but there is no operand");
		}
		return result;
	}

	private static void evaluateExprTest(String myS,
			LispExpressionEvaluator passedEx, String pex) {
		Double result;
		System.out.println("Expression " + myS);
		System.out.printf("Expected result : %s\n", pex);
		passedEx.reset(myS);
		try {
			result = passedEx.evaluate();
			System.out.printf("Evaluated result : %.2f\n", result);
		} catch (LispExpressionException e) {
			System.out.println("Evaluated result :" + e);
		}

		System.out.println("-----------------------------");
	}

	public static void main(String args[]) {
		LispExpressionEvaluator myE = new LispExpressionEvaluator();

	}
}