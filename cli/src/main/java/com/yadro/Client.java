package com.yadro;

import com.google.gson.Gson;
import com.yadro.server.InterfaceNamesWrapper;
import com.yadro.server.NetworkingInterface;
import one.nio.http.HttpClient;
import one.nio.http.HttpException;
import one.nio.http.Response;
import one.nio.net.ConnectionString;
import one.nio.pool.PoolException;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 30.11.17
 *
 * @author olerom
 */
public class Client {

    // Так как в one-nio это пул
    @NotNull
    private final HashMap<String, HttpClient> clients;

    @NotNull
    private final Gson gson;

    public Client() {
        this.clients = new HashMap<>();
        this.gson = new Gson();
    }

    @NotNull
    public String getHelp() {
        return "NAME:\n" +
                " cli_net - client displays network interfaces and info.\n\n" +
                "USAGE:\n" +
                " cli_net [global options] command [command options] [arguments...]\n\n" +
                "VERSION:\n" +
                "0.0.0\n\n" +
                "COMMANDS:\n" +
                "help, h Shows a list of commands or help for one command ...\n\n" +
                "GLOBAL OPTIONS\n" +
                "-- version Shows version information";
    }

    @NotNull
    public String getInterfaceNames(@NotNull final String server,
                                    final int port) throws InterruptedException, IOException, HttpException, PoolException {
        final Response response = getNeededClient(server, port).get("/service/v1/interfaces");
        final InterfaceNamesWrapper interfaceNamesWrapper = gson.fromJson(response.getBodyUtf8(), InterfaceNamesWrapper.class);
        final List<String> names = interfaceNamesWrapper.getInterfaceNames();

        if (names.size() == 0) {
            return "There r no interfaces";
        }

        final StringBuilder interfaces = new StringBuilder();
        for (int i = 0; i < names.size() - 1; i++) {
            interfaces.append(names.get(i)).append(", ");
        }
        interfaces.append(names.get(names.size() - 1));

        return interfaces.toString();
    }


    @NotNull
    public String getInterfaceInfo(@NotNull final String interfaceName,
                                   @NotNull final String server,
                                   final int port) throws InterruptedException, IOException, HttpException, PoolException {

        final Response response = getNeededClient(server, port).get("/service/v1/interface/" + interfaceName);
        final NetworkingInterface networkingInterface = gson.fromJson(response.getBodyUtf8(), NetworkingInterface.class);

        final InterfaceInfo interfaceInfo = new InterfaceInfo(networkingInterface);

        return interfaceName + ":\t" + interfaceInfo.toString();
    }

    @NotNull
    private HttpClient getNeededClient(@NotNull final String server,
                                       final int port) {
        final String key = server + ":" + port;
        if (!clients.containsKey(key)) {
            clients.put(key, new HttpClient(new ConnectionString(key)));
        }
        return clients.get(key);
    }


}
