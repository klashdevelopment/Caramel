package dev.klash.caramel.items;

public record FoodProperties(int saturation, int nutrition, boolean canAlwaysEat, float eatTime, FoodEffectProperties... effects) {
    public static class Builder {
        private int saturation = -1;
        private int nutrition = -1;
        private boolean canAlwaysEat = false;
        private int eatTime = 32;
        private FoodEffectProperties[] effects = new FoodEffectProperties[0];

        public Builder saturation(int saturation) {
            this.saturation = saturation;
            return this;
        }

        public Builder nutrition(int nutrition) {
            this.nutrition = nutrition;
            return this;
        }

        public Builder canAlwaysEat(boolean canAlwaysEat) {
            this.canAlwaysEat = canAlwaysEat;
            return this;
        }

        public Builder eatTime(int eatTime) {
            this.eatTime = eatTime;
            return this;
        }

        public Builder effects(FoodEffectProperties... effects) {
            this.effects = effects;
            return this;
        }

        public FoodProperties build() {
            if(nutrition == -1 || saturation == -1) {
                throw new IllegalStateException("Nutrition and saturation must be set on Caramel food properties");
            }
            return new FoodProperties(saturation, nutrition, canAlwaysEat, eatTime, effects);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
