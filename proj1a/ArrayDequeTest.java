public class ArrayDequeTest {
    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    /**
     * Adds a few things to the ArrayDeque, checking isEmpty() and size() are correct,
     * finally printing the results.
     * <p>
     * && is the "and" operation.
     */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");
        ArrayDeque<String> arrayd1 = new ArrayDeque<String>();

        boolean passed = checkEmpty(true, arrayd1.isEmpty());

        arrayd1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, arrayd1.size()) && passed;
        passed = checkEmpty(false, arrayd1.isEmpty()) && passed;

        arrayd1.addLast("middle");
        passed = checkSize(2, arrayd1.size()) && passed;

        arrayd1.addLast("back");
        passed = checkSize(3, arrayd1.size()) && passed;

        System.out.println("Printing out deque: ");
        arrayd1.printDeque();

        printTestStatus(passed);
    }

    /**
     * Adds an item, then removes an item, and ensures that dll is empty afterwards.
     */
    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");

        ArrayDeque<Integer> arrayd1 = new ArrayDeque<Integer>();
        // should be empty
        boolean passed = checkEmpty(true, arrayd1.isEmpty());

        arrayd1.addFirst(10);
        // should not be empty
        passed = checkEmpty(false, arrayd1.isEmpty()) && passed;

        arrayd1.removeFirst();
        // should be empty
        passed = checkEmpty(true, arrayd1.isEmpty()) && passed;

        printTestStatus(passed);
    }

    /**
     * add some Integer items and check the whether the sequence is as expected
     * then remove something and check the sequence
     */
    public static void addGetRemoveTest() {
        System.out.println("Running add/get/remove test.");

        ArrayDeque<Integer> arrayd1 = new ArrayDeque<Integer>();
        Integer[] exp = new Integer[4];
        for (int i = 0; i < 4; i++) {
            exp[i] = i;
        }
        //test the addFirst
        arrayd1.addFirst(1);
        arrayd1.addFirst(0);
        for (int i = 0; i < arrayd1.size(); i++) {
            if (!arrayd1.get(i).equals(exp[i])) {
                System.out.println("Test failed! Something wrong with addFirst\n");
                return;
            }
        }

        //test the addLast
        arrayd1.addLast(2);
        arrayd1.addLast(3);
        for (int i = 2; i < arrayd1.size(); i++) {
            if (!arrayd1.get(i).equals(exp[i])) {
                System.out.println("Test failed! Something wrong with addLast\n");
                return;
            }
        }

        //test Print
        System.out.println("Next line you should see 0 1 2 3");
        arrayd1.printDeque();

        //test the removeFirst
        arrayd1.removeFirst();  //should be 1 2 3 now
        if (!arrayd1.get(0).equals(1)) {
            System.out.println("Test failed! Something wrong with removeFirst\n");
            return;
        }

        //test the removeLast
        arrayd1.removeLast();   //should be 1 2 now
        if (!arrayd1.get(1).equals(2)) {
            System.out.println("Test failed! Something wrong with removeLast\n");
            return;
        }
        System.out.println("Test passed");
    }

    /**
     * test add/remove involving resize
     */
    public static void addRemoveResizeTest() {
        System.out.println("Running add/get/remove(involving resize) test.");
        ArrayDeque<Integer> arrayd1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 16; i++) {
            arrayd1.addFirst(15 - i);
        }
        for (int j = 16; j < 32; j++) {
            arrayd1.addLast(j);
        }
        for (int i = 0; i < arrayd1.size(); i++) {
            if (!arrayd1.get(i).equals(i)) {
                System.out.println("Test failed! Something wrong with resize\n");
                return;
            }
        }
        //test removeLast with resize
        for (int i = 0; i < 25; i++) {
            Integer removed = arrayd1.removeLast();
            if (!removed.equals(32 - i - 1)) {
                System.out.println("Test failed! Something wrong with removeLast's return value\n");
            }
        }
        for (int i = 0; i < 32 - 25; i++) {
            if (!arrayd1.get(i).equals(i)) {
                System.out.println("Test failed! Something wrong with resize when removeLast\n");
                return;
            }
        }

        System.out.println("Test passed");
    }


    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        addIsEmptySizeTest();
        addRemoveTest();
        addGetRemoveTest();
        addRemoveResizeTest();
    }
}
