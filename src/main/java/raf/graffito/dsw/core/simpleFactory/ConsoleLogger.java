package raf.graffito.dsw.core.simpleFactory;

import raf.graffito.dsw.core.messageGenerator.Message;

public class ConsoleLogger implements ILogger {

    @Override
    public void update(Object notification) {
        if (notification instanceof Message msg) {
            System.out.println("[" + msg.getMessageType() + "][" + msg.getTimestamp() + "] " + msg.getMessage());
        }
    }

}
