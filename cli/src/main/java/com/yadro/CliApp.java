package com.yadro;


import jdk.nashorn.internal.ir.CatchNode;
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
    public static void main(String[] args) {
        final Client client = new Client();
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String[] input = new String[0];
            try {
                input = bufferedReader.readLine().split("\\s");
            } catch (IOException e) {
                System.out.println("Restart app");
                System.exit(1);
            }

            if (input[0].equals("cli_net")) {
                switch (input[1]) {
                    case "h":
                    case "help":
                        System.out.println(client.getHelp());
                        break;

                    case "list":
                        if (input.length != 6) {
                            System.out.println("Not enough arguments: 7 expected");
                            break;
                        }

                        if (!input[2].equals("--server") || !input[4].equals("--port")) {
                            System.out.println("Don't forget about flags");
                            break;
                        }

                        final String server0 = input[3];
                        try {
                            final int port0 = Integer.parseInt(input[5]);
                            System.out.println(client.getInterfaceNames(server0, port0));
                        } catch (NumberFormatException e) {
                            System.out.println("Port is not a digit value");
                        } catch (InterruptedException | IOException | HttpException | PoolException e) {
                            System.out.println("Make sure that server or port arguments were correct");
                        }

                        break;
                    case "show":
                        if (input.length != 7) {
                            System.out.println("Not enough arguments: 7 expected");
                            break;
                        }

                        if (!input[3].equals("--server") || !input[5].equals("--port")) {
                            System.out.println("Don't forget about flags");
                            break;
                        }

                        final String interfaceName = input[2];
                        final String server1 = input[4];

                        try {
                            final int port1 = Integer.parseInt(input[6]);
                            System.out.println(client.getInterfaceInfo(interfaceName, server1, port1));
                        } catch (NumberFormatException e) {
                            System.out.println("Port is not a digit value");
                        } catch (InterruptedException | IOException | HttpException | PoolException e) {
                            System.out.println("Make sure that server or port arguments were correct");
                        }
                        break;
                    case "q":
                    case "quit":
                        System.out.println("Quitting");
                        return;

                    default:
                        System.out.println("Make sure that ur commands are correct. Look for help: cli_net h");
                }
            } else {
                System.out.println("Unsupported util");
            }
        }
    }
}
