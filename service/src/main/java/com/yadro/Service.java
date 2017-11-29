package com.yadro;

import com.google.gson.Gson;
import com.yadro.server.ConfigurationImpl;
import com.yadro.server.NetworkingInterface;
import com.yadro.server.OsNetwork;
import com.yadro.server.Server;

import java.io.IOException;
import java.util.List;

/**
 * Date: 27.11.17
 *
 * @author olerom
 */
public class Service {

    public static void main(String[] args) throws IOException {
        final Server server = new Server(new ConfigurationImpl(8080));
        server.start();

//        final OsNetwork osNetwork = new OsNetwork();
//        final Gson gson = new Gson();
//
//        final List<NetworkingInterface> networkingInterfaces = osNetwork.getInfo();
//
//        for (NetworkingInterface networkingInterface : networkingInterfaces) {
//            if (networkingInterface.getName().equals("lo")) {
//                String string = gson.toJson(networkingInterface);
//                System.out.println(string);
//            }
//        }

    }
}
