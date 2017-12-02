package com.yadro.server.pojos;

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
    private final String mask;


    @NotNull
    @SerializedName("inet_addr")
    private final List<String> inetAddr;

    @NotNull
    @SerializedName("MTU")
    private final String mtu;

    public NetworkingInterface(@NotNull final String name,
                               @NotNull final String hwAddr,
                               @NotNull final List<String> inetAddr,
                               @NotNull final String mtu,
                               @NotNull final String mask) {
        this.name = name;
        this.hwAddr = hwAddr;
        this.inetAddr = inetAddr;
        this.mtu = mtu;
        this.mask = mask;
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

    @NotNull
    public String getMask() {
        return mask;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NetworkingInterface that = (NetworkingInterface) o;

        if (!name.equals(that.name)) return false;
        if (!hwAddr.equals(that.hwAddr)) return false;
        if (!mask.equals(that.mask)) return false;
        if (!inetAddr.equals(that.inetAddr)) return false;
        return mtu.equals(that.mtu);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + hwAddr.hashCode();
        result = 31 * result + mask.hashCode();
        result = 31 * result + inetAddr.hashCode();
        result = 31 * result + mtu.hashCode();
        return result;
    }
}
