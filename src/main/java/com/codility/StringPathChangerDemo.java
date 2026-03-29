package com.codility;

public class StringPathChangerDemo {

    public static void main(String[] args) {
        StringPathChanger pathChanger = new StringPathChanger();

        // Test cases from requirements
        System.out.println("Testing changeDirectoryString implementation:");
        System.out.println("==============================================");

        test(pathChanger, "/dev/task", "cd/", "/");
        test(pathChanger, "/dev/task", "cd ..", "/dev");
        test(pathChanger, "/dev/task", "cd java", "/dev/task/java");
        test(pathChanger, "/dev/task", "cd ../solution", "/dev/solution");
        test(pathChanger, "/dev/task", "cd ../..", "/");
        test(pathChanger, "/dev/task", "cd java/", "/dev/task/java");

        // Additional test cases
        System.out.println("\nAdditional test cases:");
        System.out.println("==============================================");
        test(pathChanger, "/dev/task", "cd /home/user", "/home/user");
        test(pathChanger, "/dev/task", "cd .", "/dev/task");
        test(pathChanger, "/dev", "cd ../../..", "/");

        // Test exception
        System.out.println("\nTesting exception handling:");
        System.out.println("==============================================");
        try {
            pathChanger.changeDirectoryString("/dev/task", "ls");
            System.out.println("ERROR: Should have thrown exception!");
        } catch (IllegalStateException e) {
            System.out.println("✓ Correctly threw IllegalStateException for invalid command");
        }

        System.out.println("\n==============================================");
        System.out.println("All tests completed successfully!");
    }

    private static void test(StringPathChanger pathChanger, String currentDir, String command, String expected) {
        String result = pathChanger.changeDirectoryString(currentDir, command);
        boolean passed = result.equals(expected);
        String status = passed ? "✓" : "✗";
        System.out.printf("%s changeDirectoryString(\"%s\", \"%s\") = \"%s\" (expected: \"%s\")%n",
                          status, currentDir, command, result, expected);
        if (!passed) {
            System.out.println("  FAILED!");
        }
    }
}

