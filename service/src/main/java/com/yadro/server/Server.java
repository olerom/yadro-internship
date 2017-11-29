package com.yadro.server;

import com.google.gson.Gson;
import one.nio.http.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
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

    private final static String API_VERSION = "v1";

    @NotNull
    private final HashSet<String> availableInterfaces;

    public Server(@NotNull final Configuration configuration) throws IOException {
        super(configuration.getServerConfig());
        this.osNetwork = new OsNetwork();
        this.gson = new Gson();
        this.availableInterfaces = new HashSet<>();
    }

    @Path("/service/version")
    public void version(@NotNull final Request request,
                        @NotNull final HttpSession session) throws IOException {
        if (request.getMethod() == Request.METHOD_GET) {
            try {
                final String dummyVersionInfo = "{version:\"" + API_VERSION + "\"}";
                final Response response = Response.ok(dummyVersionInfo);
                session.sendResponse(response);
            } catch (Exception e) {
                handleInternalError(e.getMessage(), session);
            }
        }
    }

    @Path("/service/" + API_VERSION + "/interfaces")
    public void interfaces(@NotNull final Request request,
                           @NotNull final HttpSession session) throws IOException {
        if (request.getMethod() == Request.METHOD_GET) {
            try {
                final List<String> interfaces = getInterfaceNames();
                final Response response = Response.ok(gson.toJson(interfaces));
                session.sendResponse(response);
            } catch (Exception e) {
                handleInternalError(e.getMessage(), session);
            }
        }
    }


    @Override
    public void handleRequest(Request request, HttpSession session) throws IOException {
        final String path = request.getPath();
        final String methodPath = "/service/" + API_VERSION + "/interface/";
        if (path.startsWith(methodPath) && request.getMethod() == Request.METHOD_GET) {
            final String interfaceName = path.replaceAll(methodPath, "");

            try {
                if (availableInterfaces.contains(interfaceName) || getInterfaceNames().contains(interfaceName)) {
                    List<NetworkingInterface> info = osNetwork.getInfo();
                    info.removeIf((e) -> !e.getName().equals(interfaceName));

                    if (info.size() > 1) {
                        handleInternalError("Not unique interface name", session);
                        return;
                    }

                    final Response response = Response.ok(gson.toJson(info.get(0)));
                    session.sendResponse(response);
                } else {
                    handleError("interface " + interfaceName + " was not found", session, Response.NOT_FOUND);
                }
            } catch (Exception e) {
                handleInternalError(e.getMessage(), session);
            }
        } else {
            super.handleRequest(request, session);
        }
    }

    @NotNull
    private List<String> getInterfaceNames() throws IOException {
        final List<NetworkingInterface> info = osNetwork.getInfo();
        final List<String> interfaces = new ArrayList<>(info.size());

        availableInterfaces.clear();
        info.forEach((e) -> interfaces.add(e.getName()));
        availableInterfaces.addAll(interfaces);

        return interfaces;
    }

    private void handleInternalError(@NotNull final String message,
                                     @NotNull final HttpSession session) throws IOException {
        handleError(message, session, Response.INTERNAL_ERROR);
    }

    private void handleError(@NotNull final String message,
                             @NotNull final HttpSession session,
                             @NotNull final String resultCode) throws IOException {
        final String errorMsg = "{\"error\":\"" + message + "\"}";
        final Response response = new Response(resultCode, errorMsg.getBytes(StandardCharsets.UTF_8));
        session.sendResponse(response);
    }
}
