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

import java.util.List;
import java.io.IOException;

/**
 * Date: 30.11.17
 *
 * @author olerom
 */
public class Client {

    @NotNull
    private final HttpClient httpClient;

    @NotNull
    private final Gson gson;

    public Client(String connectionString) {
        this.httpClient = new HttpClient(new ConnectionString(connectionString));
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
    public String getInterfaceNames() throws InterruptedException, IOException, HttpException, PoolException {
        final Response response = httpClient.get("/service/v1/interfaces");
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
    public String getInterfaceInfo(@NotNull final String interfaceName) throws InterruptedException, IOException, HttpException, PoolException {
        final Response response = httpClient.get("/service/v1/interface/" + interfaceName);
        final NetworkingInterface networkingInterface = gson.fromJson(response.getBodyUtf8(), NetworkingInterface.class);

        final InterfaceInfo info = new InterfaceInfo(networkingInterface);

        return info.toString();

    }


}
