// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3    
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));
	}  

	// Returns x1 + x2
	public static int plus(int x1, int x2) {
		if(x2 > 0){
			for (int i = 0; i <x2 ;i++){
				x1++;
			}
		}else if (x2 < 0){
			for (int i = 0; i > x2 ;i--){
				x1--;
			}
		}
		return x1;
	}

	// Returns x1 - x2
	public static int minus(int x1, int x2) {
		if(x2 > 0){
			for (int i = 0; i < x2 ;i++){
				x1--;
			}
		}else if (x2 < 0){
			for (int i = 0; i > x2 ;i--){
				x1++;
			}
		}
		return x1;
	}

	// Returns x1 * x2
	public static int times(int x1, int x2) {
		int result = 0;
		if(x2 > 0){
			for (int i = 0; i < x2; i++){
				result = plus(result, x1);
			}
		}else if (x2 < 0){
			for (int i = 0; i > x2; i--){
				result = minus(result, x1);
			}
		}
		return result;
	}

	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) {
		int result = 1;
		for (int i = 0; i < n; i++){
			result = times(result, x);
		}
		return result;
	}

	// Returns the integer part of x1 / x2 
	public static int div(int x1, int x2) {
		if (x2 == 0) {
			throw new ArithmeticException("Division by zero");
		}

		// Determine sign of result
		boolean negative = (x1 < 0) ^ (x2 < 0);

		// Work with positive values
		if (x1 < 0) x1 = minus(0, x1);
		if (x2 < 0) x2 = minus(0, x2);

		int quotient = 0;
		int sum = 0;

		while (plus(sum, x2) <= x1) {
			sum = plus(sum, x2);
			quotient = plus(quotient, 1);
		}

		if (negative) {
			quotient = minus(0, quotient);
		}

		return quotient;
	}

	// Returns x1 % x2
	public static int mod(int x1, int x2) {
		int product = 0;
		int quotient = 0;
		while (product <= x1){
			product = plus(product, x2);
			quotient = plus(quotient, 1);
		}
		product = minus(product, x2);
		return minus(x1, product);
	}	

	// Returns the integer part of sqrt(x) 
	public static int sqrt(int x) {
		int i = 0;
		int square = 0;
		while (square <= x){
			square = pow(i, 2);
			i = plus(i, 1);
		}
		return minus(i, 2);
	}	  	  
}