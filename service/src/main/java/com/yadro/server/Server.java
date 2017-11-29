package com.yadro.server;

import com.google.gson.Gson;
import one.nio.http.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 27.11.17
 *
 * @author olerom
 */
public class Server extends HttpServer {

    @NotNull
    private final OsNetwork osNetwork;

    @NotNull
    private final Gson gson;

    public Server(@NotNull final Configuration configuration) throws IOException {
        super(configuration.getServerConfig());
        this.osNetwork = new OsNetwork();
        this.gson = new Gson();
    }

    @Path("/service/version")
    public void version(@NotNull final Request request,
                        @NotNull final HttpSession session) throws IOException {
        if (request.getMethod() == Request.METHOD_GET) {
            final String dummyVersionInfo = "Code:200\n\nContent: {version:\"v1\"}";
            final Response response = Response.ok(dummyVersionInfo);
            session.sendResponse(response);
        } else {
//            TODO 500
            final String dummyVersionInfo = "Code:200\n\nContent: {version:\"v1\"}";
            final Response response = Response.ok(dummyVersionInfo);
            session.sendResponse(response);
        }
    }

    @Path("/service/v1/interfaces")
    public void interfaces(@NotNull final Request request,
                           @NotNull final HttpSession session) throws IOException {

        final List<String> interfaces = getInterfaceNames();
        final Gson gson = new Gson();
        gson.toJson(interfaces);

        final Response response = Response.ok(osNetwork.getInfo().toString());
        session.sendResponse(response);
    }


    @Override
    public void handleRequest(Request request, HttpSession session) throws IOException {
        final String path = request.getPath();
        final String methodPath = "/service/v1/interface/";
        if (path.startsWith(methodPath)) {

            String interfaceName = path.replaceAll(methodPath, "");

            if (getInterfaceNames().contains(interfaceName)) {
                System.out.println("YAY");


                List<NetworkingInterface> info = osNetwork.getInfo();
                info.removeIf((e) -> !e.getName().equals(interfaceName));
                System.out.println(info.size());
                System.out.println(gson.toJson(info.get(0)));

                final Response response = Response.ok(gson.toJson(info.get(0)));
                session.sendResponse(response);
            } else {
                final Response response = Response.ok(
                        "Code 404\n\nContent: {\"error\":\"interface "
                                + interfaceName + " was not found\"" + "}");
                session.sendResponse(response);
            }
        } else {
            super.handleRequest(request, session);
        }
    }

    @NotNull
    private List<String> getInterfaceNames() throws IOException {
        final List<NetworkingInterface> info = osNetwork.getInfo();
        final List<String> interfaces = new ArrayList<>(info.size());

        info.forEach((e) -> interfaces.add(e.getName()));

        return interfaces;
    }
}
