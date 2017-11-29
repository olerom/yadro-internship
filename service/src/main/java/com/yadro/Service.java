package com.yadro;

import com.yadro.server.ConfigurationImpl;
import com.yadro.server.Server;

import java.io.IOException;

/**
 * Date: 27.11.17
 *
 * @author olerom
 */
public class Service {

    public static void main(String[] args) throws IOException {
        final Server server = new Server(new ConfigurationImpl(8080));
        server.start();
    }
}
