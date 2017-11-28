package com.yadro.server;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Date: 29.11.17
 *
 * @author olerom
 */
public class NetworkingInterface {
    @NotNull
    private final String name;
    @NotNull
    private final String hwAddr;
    @NotNull
    private final List<String> inetAddr;
    @NotNull
    private final String mtu;

    public NetworkingInterface(@NotNull final String name,
                               @NotNull final String hwAddr,
                               @NotNull final List<String> inetAddr,
                               @NotNull final String mtu) {
        this.name = name;
        this.hwAddr = hwAddr;
        this.inetAddr = inetAddr;
        this.mtu = mtu;
    }

    @Override
    public String toString() {
        return "NetworkingInterface{" +
                "name='" + name + '\'' +
                ", hwAddr='" + hwAddr + '\'' +
                ", inetAddr='" + inetAddr + '\'' +
                ", mtu='" + mtu + '\'' +
                '}';
    }
}
