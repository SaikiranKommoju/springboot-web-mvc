package com.codility;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringPathChangerTest {

    private final StringPathChanger pathChanger = new StringPathChanger();

    @Test
    public void testNavigateToRoot() {
        String result = pathChanger.changeDirectoryString("/dev/task", "cd/");
        assertEquals("/", result);
    }

    @Test
    public void testNavigateUp() {
        String result = pathChanger.changeDirectoryString("/dev/task", "cd ..");
        assertEquals("/dev", result);
    }

    @Test
    public void testNavigateToSubdirectory() {
        String result = pathChanger.changeDirectoryString("/dev/task", "cd java");
        assertEquals("/dev/task/java", result);
    }

    @Test
    public void testNavigateUpAndToSibling() {
        String result = pathChanger.changeDirectoryString("/dev/task", "cd ../solution");
        assertEquals("/dev/solution", result);
    }

    @Test
    public void testNavigateUpTwice() {
        String result = pathChanger.changeDirectoryString("/dev/task", "cd ../..");
        assertEquals("/", result);
    }

    @Test
    public void testNavigateToSubdirectoryWithTrailingSlash() {
        String result = pathChanger.changeDirectoryString("/dev/task", "cd java/");
        assertEquals("/dev/task/java", result);
    }

    @Test
    public void testInvalidCommandThrowsException() {
        assertThrows(IllegalStateException.class, () -> {
            pathChanger.changeDirectoryString("/dev/task", "ls");
        });
    }

    @Test
    public void testAbsolutePath() {
        String result = pathChanger.changeDirectoryString("/dev/task", "cd /home/user");
        assertEquals("/home/user", result);
    }

    @Test
    public void testCurrentDirectory() {
        String result = pathChanger.changeDirectoryString("/dev/task", "cd .");
        assertEquals("/dev/task", result);
    }

    @Test
    public void testNavigateUpBeyondRoot() {
        String result = pathChanger.changeDirectoryString("/dev", "cd ../../..");
        assertEquals("/", result);
    }
}

