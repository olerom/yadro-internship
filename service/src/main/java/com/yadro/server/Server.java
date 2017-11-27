package com.yadro.server;

import one.nio.http.HttpServer;
import one.nio.server.ServerConfig;

import java.io.IOException;

/**
 * Date: 27.11.17
 *
 * @author olerom
 */
public class Server extends HttpServer {
    public Server(ServerConfig config, Object... routers) throws IOException {
        super(config, routers);
    }
}
