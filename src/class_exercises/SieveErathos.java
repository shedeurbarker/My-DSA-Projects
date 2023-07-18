package src.class_exercises;

import java.util.ArrayList;
import java.util.Scanner;

/*
Sieve Of Erathosthenes : An algorithm for finding all prime numbers up to a given limit
STEPS (Say prime numbers between 1 and 100):
    1. Take limiting number. eg: 100
    2. Create a boolean array from 0 to the number and initially mark all as true(prime)
    3. Iteratively marked as not prime the multiples of each prime starting with the first prime(2)
    4. return array with index remaining true which corresponding to the Prime numbers and
 */
public class SieveErathos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\"Sieve of Erasthenes\"");

        SieveErathos algorithm = new SieveErathos();

        System.out.print("Enter An Integers Greater Than 1: ");
        int number = scanner.nextInt();

        boolean[] primes = algorithm.primeArray(number);
        System.out.println("Prime numbers between 1 and " + number + " is:");
        if(primes != null) {
            for(int i=2; i< primes.length; i++) {
                if(primes[i]) {
                    System.out.print(i + ", ");
                }
            }
        }

        // could be done by finding numbers with only 2 factors according to definitions.
        System.out.println();
        System.out.println();
        System.out.println("Finding Primes Numbers from Definition");
        ArrayList<Integer> primes2 = algorithm.primeByDefinition(number);
        for (Integer integer : primes2) {
            System.out.print(integer + ", ");
        }
        System.out.println();
    }

    public boolean[] primeArray(int number) {
        boolean[] primes = new boolean[number + 1]; // I am creating an array from 0 to the number

        for (int i = 0; i<=number; i++) primes[i] = true;   // initially set the array values to true

        for(int j=2; j*j<=number; j++) {
            if(primes[j])                                   // if an index is not checked
                for(int k=j*j; k<=number; k +=j)            // eliminate the multiples of each prime from the prime number to the number provided
                    primes[k] = false;
        }
        return primes;
    }

    public ArrayList<Integer> primeByDefinition(int number) {
        ArrayList<Integer> primes = new ArrayList<>();
        if(number == 2) {
            System.out.print("Prime Numbers from 1 to " + 2 + " is: " + number);
            return primes;
        }
        // given that a prime number is a number with only 2 factors, 1 and itself...
        int counter = 0;
        int j = 1;
        for(int a=2; a<=number; a++, j++) {     // loop through the numbers from 2 to the number
            for(int b=1; b<=a; b++) {
                if(a % b == 0) {                // get factors of the number
                    if(counter > 2)
                        continue;               // more than 2 factor so skip
                    counter++;
                }
            }
            if(counter == 2) {
                primes.add(a);
            }
            counter = 0;    // reset counter
        }
        return primes;
    }
}



