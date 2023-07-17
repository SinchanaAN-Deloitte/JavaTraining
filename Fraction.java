/*Define a class Fraction having data members numerator and denominator. Initialize three
        objects using different constructors and display its fractional value.*/


public class Fraction {
    private int numerator;
    private int denominator;

    // Default constructor
    public Fraction() {
        numerator = 0;
        denominator = 1;
    }

    // Constructor with numerator and denominator
    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        if (denominator != 0) {
            this.denominator = denominator;
        } else {
            System.out.println("Error: Denominator cannot be zero. Setting denominator to 1.");
            this.denominator = 1;
        }
    }

    // Constructor with numerator (denominator defaults to 1)
    public Fraction(int numerator) {
        this.numerator = numerator;
        denominator = 1;
    }

    // Method to display the fractional value
    public void displayFraction() {
        System.out.println(numerator + "/" + denominator);
    }

    public static void main(String[] args) {
        // Creating three objects using different constructors
        Fraction fraction1 = new Fraction();                // Default constructor
        Fraction fraction2 = new Fraction(3, 4);            // Constructor with numerator and denominator
        Fraction fraction3 = new Fraction(5);               // Constructor with numerator (denominator defaults to 1)

        // Displaying fractional values
        System.out.print("Fraction 1: ");
        fraction1.displayFraction();

        System.out.print("Fraction 2: ");
        fraction2.displayFraction();

        System.out.print("Fraction 3: ");
        fraction3.displayFraction();
    }
}
