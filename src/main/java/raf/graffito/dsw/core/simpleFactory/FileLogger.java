package raf.graffito.dsw.core.simpleFactory;


import raf.graffito.dsw.core.messageGenerator.Message;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements ILogger {
    private static final String LOG_FILE_PATH = "src/main/resources/log.txt";

    @Override
    public void update(Object notification) {
        if (notification instanceof Message msg) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
                writer.write("[" + msg.getMessageType() + "][" + msg.getTimestamp() + "] " + msg.getMessage());
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
