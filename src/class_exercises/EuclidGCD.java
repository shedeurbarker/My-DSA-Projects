package src.class_exercises;

import java.util.Scanner;

/*
Euclid Algorithm: An algorithm for finding the Greatest Common Divisor of two +ive Integers(A and B)
STEPS:
    1. Take two numbers, A and B
    2. Return A if B = 0
    3. Return B if B divides A without a remainder (A % B)
    4. Else, let A = B and let B = A % B
    5. Repeat step 3 until A % B == 0
 */
public class EuclidGCD {
    public static void main(String[] a) {
        Scanner scanner = new Scanner(System.in);       // create a scanner for receiving value from user
        System.out.println("\"Euclid GCD Algorithm\"");

        EuclidGCD algorithm = new EuclidGCD();          // create a new object of the class

        System.out.print("Enter First Integers: ");
        int number1 = scanner.nextInt();                // read first number

        System.out.print("Enter Second Integer: ");
        int number2 = scanner.nextInt();                // read second line

        int gcd = algorithm.gcd(number1, number2);      // calculate the Greatest common divisor

        System.out.println("Greatest Common Divisor of " + number1 + " and " + number2 + " is: " + gcd);
    }

    public int gcd(int A, int B) {
        if(B == 0)          // if the divisor is 0, return A
            return A;
        if(A == 0)          // if A is 0, return the divisor, B
            return B;
        if(A % B == 0)      // if B divides A without a remainder, B is the GCD
            return B;
        return gcd(B, A % B);   // assign B to A, and A%B to B
    }
}




