package dev.klash.caramel.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;

import java.util.List;

public record CaramelItemDetail(String id, Component itemName, List<String> lore, int defaultStack, int modelData, Material itemBase, FoodProperties food) {

    public static class Builder {
        private String id;
        private Component itemName;
        private List<String> lore;
        private int defaultStack;
        private int modelData;
        private Material itemBase;
        private FoodProperties food = null;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder itemName(Component itemName) {
            this.itemName = itemName;
            return this;
        }

        public Builder lore(List<String> lore) {
            this.lore = lore;
            return this;
        }

        public Builder defaultStack(int defaultStack) {
            this.defaultStack = defaultStack;
            return this;
        }

        public Builder food(FoodProperties food) {
            this.food = food;
            return this;
        }

        public Builder modelData(int modelData) {
            this.modelData = modelData;
            return this;
        }

        public Builder itemBase(Material itemBase) {
            this.itemBase = itemBase;
            return this;
        }

        public CaramelItemDetail build() {
            return new CaramelItemDetail(id, itemName, lore, defaultStack, modelData, itemBase, food);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}