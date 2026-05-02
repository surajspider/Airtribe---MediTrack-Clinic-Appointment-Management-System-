package com.airtribe.meditrack.interfaces;

public interface Searchable<T> {
    // Basic search method to be implemented
    boolean match(String query);

    // Default method to provide some basic functionality
    default void logSearchQuery(String query) {
        System.out.println("Searching for: " + query);
    }
}
