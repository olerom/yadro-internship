package com.yadro;

import com.yadro.server.config.ConfigurationImpl;
import com.yadro.server.Server;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Date: 27.11.17
 *
 * @author olerom
 */
public class Service {

    @NotNull
    final Server server;

    public static void main(String[] args) throws IOException {
        new Service(8080).run();
    }

    public Service(final int port) throws IOException {
        this.server = new Server(new ConfigurationImpl(port));
    }

    public void run() {
        server.start();
    }
}
