package com.yadro.server;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 29.11.17
 *
 * @author olerom
 */
public class NetworkingInterfaceParser {

    private static final String DELIMETER = "@";
    private static final String SPACED_DELIMETER = " " + DELIMETER + " ";

    @NotNull
    public List<NetworkingInterface> parse(@NotNull final String base) {
        final String[] shittySplit = base.replaceAll("\n\n", SPACED_DELIMETER).split("\\s");

        final List<NetworkingInterface> networkingInterfaces = new ArrayList<>();
        int i = 0;
        final int length = shittySplit.length;

        while (i < length) {
            final String name = shittySplit[i];

            boolean loopback = false;
            for (; i < length; i++) {
                if (shittySplit[i].equals("Loopback")) {
                    loopback = true;
                    break;
                } else if (shittySplit[i].equals("HWaddr")) {
                    break;
                }
            }

            final String hwAddr = loopback ? "Loopback" : shittySplit[i + 1];
            final List<String> inetAddr = new ArrayList<>();
            String mask = "";
            for (; i < length; i++) {
                if (shittySplit[i - 1].equals("inet") && shittySplit[i].startsWith("addr")) {
                    inetAddr.add(shittySplit[i].split(":")[1]);
                } else if (shittySplit[i].startsWith("Mask:")) {
                    mask = shittySplit[i].split(":")[1];
                } else if (shittySplit[i - 1].equals("inet6") && shittySplit[i].equals("addr:")) {
                    inetAddr.add(shittySplit[i + 1]);
                } else if (shittySplit[i].startsWith("MTU")) {
                    break;
                }
            }

            final String mtu = shittySplit[i].split(":")[1];
            networkingInterfaces.add(new NetworkingInterface(name, hwAddr, inetAddr, mtu, mask));

            for (; i < length; i++) {
                if (shittySplit[i].equals(DELIMETER)) {
                    i++;
                    break;
                }
            }
        }

        return networkingInterfaces;
    }

}
