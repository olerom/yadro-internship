package com.yadro.server;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 01.12.17
 *
 * @author olerom
 */
public class NetworkingInterfaceParserTest {

    @NotNull
    private NetworkingInterfaceParser parser;

    @Before
    public void setup() {
        parser = new NetworkingInterfaceParser();
    }

    @Test
    public void parseTest() {
        final String lineToBeParsed = "wlp3s0    Link encap:Ethernet  HWaddr 24:0a:64:58:6a:9b  \n" +
                "inet addr:192.168.0.113  Bcast:192.168.0.255  Mask:255.255.255.0\n" +
                "inet6 addr: fe80::da90:ee57:9b34:23fa/64 Scope:Link\n" +
                "UP BROADCAST RUNNING MULTICAST  MTU:1500  Metric:1\n" +
                "RX packets:1242546 errors:0 dropped:0 overruns:0 frame:0\n" +
                "TX packets:635298 errors:0 dropped:0 overruns:0 carrier:0\n" +
                "collisions:0 txqueuelen:1000 \n" +
                "RX bytes:1583030749 (1.5 GB)  TX bytes:81963796 (81.9 MB)\n";

        final List<String> inetAddr = new ArrayList<String>(2) {{
            add("192.168.0.113");
            add("fe80::da90:ee57:9b34:23fa/64");
        }};

        final NetworkingInterface expected = new NetworkingInterface("wlp3s0", "24:0a:64:58:6a:9b", inetAddr, "1500", "255.255.255.0");
        final NetworkingInterface actual = parser.parse(lineToBeParsed).get(0);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseNoIpv6Test() {
        final String lineToBeParsed = "wlp3s0    Link encap:Ethernet  HWaddr 24:0a:64:58:6a:9b  \n" +
                "inet addr:192.168.0.113  Bcast:192.168.0.255  Mask:255.255.255.0\n" +
                "Scope:Link\n" +
                "UP BROADCAST RUNNING MULTICAST  MTU:1500  Metric:1\n" +
                "RX packets:1242546 errors:0 dropped:0 overruns:0 frame:0\n" +
                "TX packets:635298 errors:0 dropped:0 overruns:0 carrier:0\n" +
                "collisions:0 txqueuelen:1000 \n" +
                "RX bytes:1583030749 (1.5 GB)  TX bytes:81963796 (81.9 MB)\n";

        final List<String> inetAddr = new ArrayList<String>(1) {{
            add("192.168.0.113");
        }};

        final NetworkingInterface expected = new NetworkingInterface("wlp3s0", "24:0a:64:58:6a:9b", inetAddr, "1500", "255.255.255.0");
        final NetworkingInterface actual = parser.parse(lineToBeParsed).get(0);

        Assert.assertEquals(expected, actual);
    }
}
