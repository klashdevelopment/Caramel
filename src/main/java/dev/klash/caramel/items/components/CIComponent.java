package dev.klash.caramel.items.components;

import io.papermc.paper.datacomponent.DataComponentType;

public abstract class CIComponent {
    public static class CIComponentNonvalued extends CIComponent {
        private DataComponentType.NonValued component;
        private CIComponentNonvalued(DataComponentType.NonValued component) {
            this.component = component;
        }
        public DataComponentType.NonValued getComponent() {
            return component;
        }
    }
    public static class CIComponentValued<T> extends CIComponent {
        private DataComponentType.Valued<T> component;
        private T value;
        private CIComponentValued(DataComponentType.Valued<T> component, T value) {
            this.component = component;
            this.value = value;
        }
        public DataComponentType.Valued<T> getComponent() {
            return component;
        }
        public T getValue() {
            return value;
        }
    }
    public static CIComponent of(DataComponentType.NonValued component) {
        return new CIComponentNonvalued(component);
    }
    public static <T> CIComponent of(DataComponentType.Valued<T> component, T value) {
        return new CIComponentValued<T>(component, value);
    }
}
