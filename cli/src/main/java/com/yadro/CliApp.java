package com.yadro;


import one.nio.http.HttpException;
import one.nio.pool.PoolException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Date: 30.11.17
 *
 * @author olerom
 */
public class CliApp {
    public static void main(String[] args) throws IOException, InterruptedException, PoolException, HttpException {
        final Client client = new Client("localhost:8080");
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            final String[] input = bufferedReader.readLine().split("\\s");

            switch (input[1]) {
                case "h":
                case "help":
                    System.out.println(client.getHelp());
                    break;

                case "list":
                    System.out.println("--server" + input[2]);

                    System.out.println("--port" + input[4]);
                    final int port = Integer.parseInt(input[5]);
                    final String server = input[3];
                    System.out.println(client.getInterfaceNames());

                    break;
                case "show":

                    System.out.println(client.getInterfaceInfo(input[2]));
                    break;

            }
        }
    }
}
