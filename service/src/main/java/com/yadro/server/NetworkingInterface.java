package com.yadro.server;

import com.google.gson.annotations.SerializedName;
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
    @SerializedName("hw_addr")
    private final String hwAddr;

    @NotNull
    @SerializedName("inet_addr")
    private final List<String> inetAddr;

    @NotNull
    @SerializedName("MTU")
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

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public String getHwAddr() {
        return hwAddr;
    }

    @NotNull
    public List<String> getInetAddr() {
        return inetAddr;
    }

    @NotNull
    public String getMtu() {
        return mtu;
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
