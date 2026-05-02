package com.airtribe.meditrack.util;

import com.airtribe.meditrack.interfaces.Searchable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataStore<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<T> items;

    public DataStore() {
        this.items = new ArrayList<>();
    }

    public void add(T item) {
        items.add(item);
    }

    public boolean remove(T item) {
        return items.remove(item);
    }

    public List<T> getAll() {
        return new ArrayList<>(items);
    }

    /**
     * Replaces the current data with loaded data
     */
    public void setAll(List<T> loadedItems) {
        this.items = new ArrayList<>(loadedItems);
    }

    /**
     * Search items using the Searchable interface
     */
    public List<T> search(String query) {
        return items.stream()
                .filter(item -> {
                    if (item instanceof Searchable) {
                        return ((Searchable<?>) item).match(query);
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }
}
