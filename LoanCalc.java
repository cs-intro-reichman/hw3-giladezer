// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter;    // Number of iterations 
	
	// Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods " + n);

		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a percentage), the number of periods (n), and the periodical payment.
	private static double endBalance(double loan, double rate, int n, double payment) {	
		for(int i = 0; i < n; i++) {
			loan = (loan - payment) * (1 + rate / 100);
		}
		return loan;
	}
	
	// Uses sequential search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
		// Replace the following statement with your code
		double payment = 0;
		double balance = 0;
		double irretaionCounter = 0;
		rate /= 100;

		do{
			balance = loan;
			for (int i = 0; i < n; i++) {
				balance = (balance - payment) * (1 + rate);
			}
			irretaionCounter++;
			payment += epsilon;
		}while (balance > 0);

		return payment;
    }
    
    // Uses bisection search to compute an approximation of the periodical payment 
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
    iterationCounter = 0;
    rate = rate / 100.0;  // convert percent to decimal

    double low = 0;
    double high = loan;
    double payment = 0;
    double balance = loan;

    while (true) {
        payment = (low + high) / 2.0;  // midpoint payment
        balance = loan;

        // simulate n payments
        for (int i = 0; i < n; i++) {
            balance = balance * (1 + rate) - payment;
        }

        iterationCounter++;

        // check if close enough
        if (Math.abs(balance) <= epsilon) {
            break;
        }

        // adjust bounds
        if (balance > 0) {
            // still owe money → payment too small
            low = payment;
        } else {
            // overpaid → payment too big
            high = payment;
        }

        // stop if the interval is very small (avoid infinite loop)
        if (Math.abs(high - low) < epsilon) {
            break;
        }
    }
    return payment;
}

}