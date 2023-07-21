package src.class_exercises;

public class Fibonacci {
    public static int number = 25;
    public static double start_time;
    public static double end_time;
    public static StringBuilder results = new StringBuilder();

    public static void main(String[] arg) {
        start_time = System.currentTimeMillis();
        for(int i=0; i <= number; i++) {
            // Recursion
            results.append(Recursion(i));
            if(i < number)
                results.append(", ");
        }
        end_time = System.currentTimeMillis();
        double total_time = (end_time - start_time) / 1000;
        System.out.println(results.toString());
        System.out.println("Time Taken: " + total_time + "secs");
    }

    public static int Recursion(int number) {
        if(number <= 0)
            return 0;
        else if(number == 1 || number == 2)
            return 1;
        else {
            return Recursion(number-1) + Recursion(number - 2);
        }
    }

    public static int Memoization(int number) {
        return 0;
    }
}
