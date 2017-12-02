package com.yadro.server.config;

import com.yadro.server.config.Configuration;
import one.nio.config.ConfigParser;
import one.nio.server.ServerConfig;
import org.jetbrains.annotations.NotNull;

/**
 * Date: 27.11.17
 *
 * @author olerom
 */
public class ConfigurationImpl implements Configuration {

    @NotNull
    private final static String CONF = "\n" +
            "selectors: 4\n" +
            "acceptors:\n" +
            " - port: {PORT}\n";

    final private int port;

    public ConfigurationImpl(final int port) {
        this.port = port;
    }

    @NotNull
    @Override
    public ServerConfig getServerConfig() {
        return ConfigParser.parse(CONF.replace("{PORT}", port + ""), ServerConfig.class);
    }
}