# Configs

## READ ME FIRST
If you'd like to make a config **without** having a template file in your resources folder (configs created at-runtime with changing filenames), use a `CaramelBlankConfig` instead of a `CaramelConfig` class!

## Creating one
First, make a variable for it:
```java
CaramelConfig coolConfig;
```

Then in your onEnable, initialize it:
```java
coolConfig = new CaramelConfig(this, "coolest-config.yml");
// If using blank configs, use .saveBlankConfig() instead.
coolConfig.saveDefaultConfig();
```

And use it:
```java
coolConfig.getData() // returns a FileConfiguration
coolConfig.getData().getString("my.path");
coolConfig.getData().setString("my.path", "Setting!");
```
