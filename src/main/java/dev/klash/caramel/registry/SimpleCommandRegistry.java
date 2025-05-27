package dev.klash.caramel.registry;

import dev.klash.caramel.Caramel;
import dev.klash.caramel.CaramelCommandList;
import dev.klash.caramel.commands.CaramelCommand;

public class SimpleCommandRegistry extends DuoRegistry<CaramelCommand> {

    private CaramelCommandList list;

    @Override
    public void taskA(CaramelCommand item) {
        list.register(item);
    }

    @Override
    public void taskB(CaramelCommand item) {
        list.getCommandList().remove(item);
    }

    public SimpleCommandRegistry(CaramelCommandList list, CaramelCommand... commands) {
        super(commands);
        this.list = list;
    }
    public void register() {
        callA();
    }
    public void unregister() {
        callB();
    }
}
