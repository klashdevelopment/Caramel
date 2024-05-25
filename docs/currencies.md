# Currencies

### Create the class
```java
public static class MyCurrency extends CaramelCurrency {
    public MyCurrency() {
        super(Plugin, "Name", "id", 10); // Plugin, Name, ID, Default amount
    }
}
```
### Register
```java
CaramelCurrency currency;

public void onEnable() {
    Caramel.getInstance().currencies.register(currency = new MyCurrency());
}
public void onDisable() {
    Caramel.getInstance().currencies.getCurrencyList().remove(currency);
}
```

### Using it
Simply use the instance you've set up with these functions:
```java
// Currency methods
currency.setValue(UUID, double) -> double
currency.addValue(UUID, double) -> double
currency.getValue(UUID) -> double
currency.getSaveDataFile() -> CaramelBlankConfig
```
