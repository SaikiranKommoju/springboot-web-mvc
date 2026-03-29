package com.codility;

import java.util.ArrayList;
import java.util.List;

public class StringPathChanger {

    public String changeDirectoryString(String currentDirectory, String command) {
        // Check if command starts with "cd"
        if (!command.startsWith("cd")) {
            throw new IllegalStateException("Command must start with 'cd'");
        }

        // Extract the path after "cd"
        String path = command.substring(2).trim();

        // Handle "cd/" - navigate to root
        if (path.isEmpty() || path.equals("/")) {
            return "/";
        }

        // Remove trailing slashes from the path argument
        while (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }

        // If path is empty after removing trailing slashes, return root
        if (path.isEmpty()) {
            return "/";
        }

        // Start building the result path
        List<String> pathComponents = new ArrayList<>();

        // Parse current directory into components (skip empty strings)
        String[] currentParts = currentDirectory.split("/");
        for (String part : currentParts) {
            if (!part.isEmpty()) {
                pathComponents.add(part);
            }
        }

        // If the path is absolute (starts with /), start fresh
        if (path.startsWith("/")) {
            pathComponents.clear();
            path = path.substring(1); // Remove leading slash
        }

        // Process the path argument
        if (!path.isEmpty()) {
            String[] pathParts = path.split("/");
            for (String part : pathParts) {
                if (part.isEmpty()) {
                    // Skip empty parts (from multiple slashes)
                    continue;
                } else if (part.equals("..")) {
                    // Go up one directory
                    if (!pathComponents.isEmpty()) {
                        pathComponents.remove(pathComponents.size() - 1);
                    }
                } else if (part.equals(".")) {
                    // Current directory - do nothing
                    continue;
                } else {
                    // Add directory to path
                    pathComponents.add(part);
                }
            }
        }

        // Build final path
        if (pathComponents.isEmpty()) {
            return "/";
        }

        StringBuilder result = new StringBuilder();
        for (String component : pathComponents) {
            result.append("/").append(component);
        }

        return result.toString();
    }
}
