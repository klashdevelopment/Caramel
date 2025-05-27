package dev.klash.caramel.registry;

import java.util.ArrayList;
import java.util.List;

public abstract class DuoRegistry<T> {
    public List<T> registry;
    public DuoRegistry(T ...items) {
        registry = new ArrayList<>(List.of(items));
    }
    public abstract void taskA(T item);
    public abstract void taskB(T item);
    public final void callA() {
        for (T item : registry) {
            taskA(item);
        }
    }
    public final void callB() {
        for (T item : registry) {
            taskB(item);
        }
    }
}
