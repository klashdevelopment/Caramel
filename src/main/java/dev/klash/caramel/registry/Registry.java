package dev.klash.caramel.registry;

import java.util.ArrayList;
import java.util.List;

public abstract class Registry<T> {
    public List<T> registry;
    public Registry(T ...items) {
        registry = new ArrayList<>(List.of(items));
    }
    public abstract void task(T item);
    public final void call() {
        for (T item : registry) {
            task(item);
        }
    }
}
