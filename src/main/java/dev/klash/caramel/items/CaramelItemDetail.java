package dev.klash.caramel.items;

import dev.klash.caramel.CaramelUtility;
import dev.klash.caramel.items.components.CIComponent;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.jetbrains.annotations.ApiStatus;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public record CaramelItemDetail(String id, Component itemName, List<String> lore, int defaultStack, int modelData, Material itemBase, Key itemModel, FoodProperties food, List<CIComponent> components) {
    public static class Builder {
        private String id;
        private Component itemName = CaramelUtility.colorcomp("No name specified");
        private List<String> lore = Collections.emptyList();
        private int defaultStack = 1;
        private int modelData = -1;
        private Material itemBase = Material.STICK;
        @ApiStatus.Experimental private Key itemModel = null;
        @ApiStatus.Experimental private FoodProperties food = null;
        @ApiStatus.Experimental private List<CIComponent> components = Collections.emptyList();

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder itemModel(Key itemModel) {
            this.itemModel = itemModel;
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

        @ApiStatus.Experimental
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

        @ApiStatus.Experimental
        public Builder components(List<CIComponent> components) {
            this.components = components;
            return this;
        }

        @ApiStatus.Experimental
        public Builder components(CIComponent... components) {
            this.components = Arrays.asList(components);
            return this;
        }

        public CaramelItemDetail build() {
            return new CaramelItemDetail(id, itemName, lore, defaultStack, modelData, itemBase, itemModel, food, components);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}