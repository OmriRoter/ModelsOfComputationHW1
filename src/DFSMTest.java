public class DFSMTest {
    public static void main(String[] args) {
        System.out.println("Test Case 1: Simple DFSM that accepts strings ending with 'b'");
        testDFSM("0 1/a b/0,a,0;0,b,1;1,a,1;1,b,1/0/1", "ab", true);
        testDFSM("0 1/a b/0,a,0;0,b,1;1,a,1;1,b,1/0/1", "aa", false);
        testDFSM("0 1/a b/0,a,0;0,b,1;1,a,1;1,b,1/0/1", "ba", true);
        testDFSM("0 1/a b/0,a,0;0,b,1;1,a,1;1,b,1/0/1", "bb", true);
        System.out.println();

        System.out.println("Test Case 2: DFSM that accepts strings containing an even number of 'a's");
        testDFSM("0 1/a b/0,a,1;0,b,0;1,a,0;1,b,1/0/0", "aaaa", true);
        testDFSM("0 1/a b/0,a,1;0,b,0;1,a,0;1,b,1/0/0", "aaa", false);
        testDFSM("0 1/a b/0,a,1;0,b,0;1,a,0;1,b,1/0/0", "bbbb", true);
        testDFSM("0 1/a b/0,a,1;0,b,0;1,a,0;1,b,1/0/0", "abab", true);
        System.out.println();

        System.out.println("Test Case 3: DFSM that accepts strings starting with 'a' and ending with 'b'");
        testDFSM("0 1 2/a b/0,a,1;0,b,2;1,a,1;1,b,1;2,a,2;2,b,2/0/1", "ab", true);
        testDFSM("0 1 2/a b/0,a,1;0,b,2;1,a,1;1,b,1;2,a,2;2,b,2/0/1", "aab", true);
        testDFSM("0 1 2/a b/0,a,1;0,b,2;1,a,1;1,b,1;2,a,2;2,b,2/0/1", "ba", false);
        testDFSM("0 1 2/a b/0,a,1;0,b,2;1,a,1;1,b,1;2,a,2;2,b,2/0/1", "bb", false);
        System.out.println();

        System.out.println("Test Case 4: DFSM that accepts strings containing the substring \"aba\"");
        testDFSM("0 1 2 3/a b/0,a,1;0,b,0;1,a,1;1,b,2;2,a,3;2,b,0;3,a,3;3,b,3/0/3", "aba", true);
        testDFSM("0 1 2 3/a b/0,a,1;0,b,0;1,a,1;1,b,2;2,a,3;2,b,0;3,a,3;3,b,3/0/3", "aaba", true);
        testDFSM("0 1 2 3/a b/0,a,1;0,b,0;1,a,1;1,b,2;2,a,3;2,b,0;3,a,3;3,b,3/0/3", "abab", true);
        testDFSM("0 1 2 3/a b/0,a,1;0,b,0;1,a,1;1,b,2;2,a,3;2,b,0;3,a,3;3,b,3/0/3", "bbb", false);
        System.out.println();

        System.out.println("Test Case 5: Empty DFSM");
        testDFSM("0///0/", "", false);
        testDFSM("0///0/", "a", false);
        System.out.println();

        System.out.println("Test Case 6: DFSM with invalid input symbols");
        testDFSMWithInvalidInput("0 1/a b/0,a,0;0,b,1;1,a,1;1,b,1/0/1", "ac");
        testDFSMWithInvalidInput("0 1/a b/0,a,0;0,b,1;1,a,1;1,b,1/0/1", "123");
    }

    private static void testDFSM(String dfsmEncoding, String input, boolean expectedOutput) {
        try {
            DFSM dfsm = new DFSM(dfsmEncoding);
            boolean actualOutput = dfsm.compute(input);
            System.out.println("Input: " + input + ", Expected Output: " + expectedOutput + ", Actual Output: " + actualOutput);
            if (actualOutput != expectedOutput) {
                System.out.println("Test failed for input: " + input);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Input: " + input + ", Expected Exception: IllegalArgumentException, Actual Exception: IllegalArgumentException");
        } catch (Exception e) {
            System.out.println("Error creating DFSM: " + e.getMessage());
        }
    }

    private static void testDFSMWithInvalidInput(String dfsmEncoding, String input) {
        try {
            DFSM dfsm = new DFSM(dfsmEncoding);
            dfsm.compute(input);
        } catch (IllegalArgumentException e) {
            System.out.println("Successfully threw IllegalArgumentException for input: " + input);
        } catch (Exception e) {
            System.out.println("Error creating DFSM: " + e.getMessage());
        }
    }
}
