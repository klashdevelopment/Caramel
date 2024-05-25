# SQL Databases
Caramel provides an AIO SQL database system.

## Core SQL connection
Create a new CaramelSQL variable, like this:
```java
sql = new CaramelSQL(
    "host",
    "port",
    "database",
    "username",
    "password"
);
```
Then, connect to it using `attemptConnect`. This function **does return a boolean** if it connects or fails.
```java
sql.attemptConnect()
```
If you'd like, you can always `disconnect` it.
```java
sql.disconnect();
```
You can also access if it is connected or not via the `isConnected()` function.

To access the connection, simply use `getConnection()`.

## CaramelSQLHelper
SQLHelper helps you make tables and modify all sorts of stuff when it comes to UUIDs.
```java
helper = new CaramelSQLHelper(sql);
```
#### Tables
Make one!
```java
helper.createTable("TableName", "UUID VARCHAR(100), ExampleString VARCHAR(100), ExampleInt INT(100), PRIMARY KEY(UUID)");
```

#### Functions
Make sure to create entries for new players. `createUUIDEntry` will make a new entry **only** if the player doesnt exist.
```java
helper.createUUIDEntry("TableName", player.getUniqueId());
```
After this, you can use `getValue(table, uuid, column, typeClass)` or the simpler `getStringValue(table, uuid, column)` and `getIntValue(table, uuid, column)`.

For setting, you can use `setIntValue(table, uuid, column, value)` and `setStringValue(table, uuid, column, value)`.

If you need to see if a uuid is already in the table, use `playerExists(table, uuid)`.
