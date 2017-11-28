package com.yadro.server;

import one.nio.http.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Date: 27.11.17
 *
 * @author olerom
 */
public class Server extends HttpServer {

    public Server(@NotNull final Configuration configuration) throws IOException {
        super(configuration.getServerConfig());
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
        final OsNetwork osNetwork = new OsNetwork();
        final Response response = Response.ok(osNetwork.getInfo().toString());
        session.sendResponse(response);
    }
}
