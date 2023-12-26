@FunctionalInterface
interface MyFunctionalInterface {
    void myMethod(String input);
}

public class MethodAsInputExample {
    public static void main(String[] args) {
        // Pass a method as an input
        processString("Hello, World!", MethodAsInputExample::printUpperCase);

        // Alternatively, using a lambda expression
        processString("Hello, Lambda!", s -> System.out.println(s.toLowerCase()));
    }

    // Method that accepts a functional interface as a parameter
    private static void processString(String input, MyFunctionalInterface myFunction) {
        myFunction.myMethod(input);
    }

    // Example method to be passed as an input
    private static void printUpperCase(String input) {
        System.out.println(input.toUpperCase());
    }
}