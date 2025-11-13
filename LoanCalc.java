public class LoanCalc {

    static double epsilon = 0.001;  // approximation accuracy
    static int iterationCounter;    // number of iterations 

    public static void main(String[] args) {
        double loan = Double.parseDouble(args[0]);
        double rate = Double.parseDouble(args[1]);
        int n = Integer.parseInt(args[2]);
        System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

        // Brute force solver
        iterationCounter = 0;
        System.out.print("\nPeriodical payment, using brute force: ");
        System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
        System.out.println("number of iterations: " + iterationCounter);

        // Bisection solver
        iterationCounter = 0;
        System.out.print("\nPeriodical payment, using bi-section search: ");
        System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
        System.out.println("number of iterations: " + iterationCounter);
    }

    // Ending balance after n payments
    private static double endBalance(double loan, double rate, int n, double payment) {
        rate /= 100.0;
        for (int i = 0; i < n; i++) {
            loan = (loan - payment) * (1 + rate);
        }
        return loan;
    }

    // Brute force solver
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
        iterationCounter = 0;
        double payment = 0;

        while (true) {
            double balance = endBalance(loan, rate, n, payment);
            iterationCounter++;

            if (balance <= 0) {  // stop once the loan is fully paid
                break;
            }
            payment += 0.002;
        }

        return payment ;
    }

    // Bisection solver
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {
        iterationCounter = 0;
        double low = 0;
        double high = loan;
        double payment = 0;

        while (true) {
            payment = (low + high) / 2.0;
            double balance = endBalance(loan, rate, n, payment);
            iterationCounter++;

            if (Math.abs(balance) <= epsilon || Math.abs(high - low) < 1) {
                break;
            }

            if (balance > 0) {
                low = payment;
            } else {
                high = payment;
            }
        }

        return Math.round(payment);
    }
}
