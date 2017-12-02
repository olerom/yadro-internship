package com.yadro.server.config;

import one.nio.server.ServerConfig;
import org.jetbrains.annotations.NotNull;

/**
 * Date: 27.11.17
 *
 * @author olerom
 */

public interface Configuration {
    @NotNull
    ServerConfig getServerConfig();
}