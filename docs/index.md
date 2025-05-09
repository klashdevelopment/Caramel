# Caramel
Caramel is a multipurpose AIO api for PaperAPI plugins. ***Caramel can not and will not support anything but papermc & forks.***

![For Paper](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/supported/paper_vector.svg)
![For Purpur](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/supported/purpur_vector.svg)
![Not for Bukkit](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/unsupported/bukkit_vector.svg)
![Not for Spigot](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/unsupported/spigot_vector.svg)
![Not for Fabric](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/unsupported/fabric_vector.svg)
![Not for Forge](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/unsupported/forge_vector.svg)

## Quickstart
- Simply drop in the latest Caramel jarfile from [here](https://github.com/klashdevelopment/Caramel/releases/tag/latest-1.21)

## Quickstart (Developer)
- Make sure you have Paper API 1.21.4 in your pom
- Add Caramel to your maven:

```xml
<repository>
   <id>klashdevelopment</id>
   <url>https://raw.githubusercontent.com/klashdevelopment/mvn/main/repository/</url>
</repository>

<dependency>
   <groupId>dev.klash</groupId>
   <artifactId>Caramel</artifactId>
   <version>1.4.0</version>
   <scope>provided</scope>
</dependency>
```

- Add it as a dependency in your plugin.yml or paper-plugin.yml

```yaml
depend: [Caramel]
```


## Features
Caramel has a few main features.
- [Commands](https://pages.klash.dev/Caramel/commands)
- [Items](https://pages.klash.dev/Caramel/items) (WIP Docs)
- [Guis](https://pages.klash.dev/Caramel/guis)
- [Currencies](https://pages.klash.dev/Caramel/currencies)
- [Recipes](https://pages.klash.dev/Caramel/recipes)
- [YAML Configs](https://pages.klash.dev/Caramel/configs)
- [SQL Configs](https://pages.klash.dev/Caramel/sql)
