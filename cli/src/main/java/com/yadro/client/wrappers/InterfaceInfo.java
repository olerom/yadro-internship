package com.yadro.client.wrappers;

import com.yadro.server.pojos.NetworkingInterface;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Date: 01.12.17
 *
 * @author olerom
 */
public class InterfaceInfo {
    @NotNull
    private final String hwAddr;

    @NotNull
    private final String ipv4;

    @NotNull
    private final String mask;

    @NotNull
    private final String ipv6;

    @NotNull
    private final String mtu;

    public InterfaceInfo(@NotNull final String hwAddr,
                         @NotNull final String ipv4,
                         @NotNull final String mask,
                         @NotNull final String ipv6,
                         @NotNull final String mtu) {
        this.hwAddr = hwAddr;
        this.ipv4 = ipv4;
        this.mask = mask;
        this.ipv6 = ipv6;
        this.mtu = mtu;
    }

    public InterfaceInfo(@NotNull final NetworkingInterface prototype) {
        this.hwAddr = prototype.getHwAddr();
        List<String> inetAddr = prototype.getInetAddr();
        this.ipv4 = inetAddr.get(0);
        this.mask = prototype.getMask();
        this.ipv6 = inetAddr.size() > 1 ? inetAddr.get(1) : "Nope";
        this.mtu = prototype.getMtu();
    }

    @NotNull
    public String getHwAddr() {
        return hwAddr;
    }

    @NotNull
    public String getIpv4() {
        return ipv4;
    }

    @NotNull
    public String getMask() {
        return mask;
    }

    @NotNull
    public String getIpv6() {
        return ipv6;
    }

    @NotNull
    public String getMtu() {
        return mtu;
    }

    @Override
    public String toString() {
        return "Hw_addr: " + hwAddr +
                "\n\tIpv4: " + ipv4 +
                ", mask " + mask +
                "\n\tIpv6: " + ipv6 +
                "\n\tMTU: " + mtu;
    }
}
