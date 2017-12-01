package com.yadro;


import one.nio.http.HttpException;
import one.nio.pool.PoolException;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Date: 30.11.17
 *
 * @author olerom
 */
public class CliApp {

    @NotNull
    private Client client;

    public static void main(String[] args) {
        new CliApp(new Client()).run();
    }

    public CliApp(@NotNull Client client) {
        this.client = client;
    }

    public void run() {
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
                        handleHelp();
                        break;

                    case "list":
                        handleList(input);
                        break;

                    case "show":
                        handleShow(input);
                        break;

                    case "q":
                    case "quit":
                        handleQuit();
                        break;

                    default:
                        System.out.println("Make sure that ur commands are correct. Look for help: cli_net h");
                }
            } else {
                System.out.println("Unsupported util");
            }
        }
    }

    private void handleQuit() {
        System.out.println("Quitting");
        System.exit(0);
    }

    private void handleShow(@NotNull final String[] input) {
        if (input.length != 7) {
            System.out.println("Not enough arguments: 7 expected");
            return;
        }

        if (!input[3].equals("--server") || !input[5].equals("--port")) {
            System.out.println("Don't forget about flags");
            return;
        }

        final String interfaceName = input[2];
        final String server = input[4];

        try {
            final int port = Integer.parseInt(input[6]);
            System.out.println(client.getInterfaceInfo(interfaceName, server, port));
        } catch (NumberFormatException e) {
            System.out.println("Port is not a digit value");
        } catch (InterruptedException | IOException | HttpException | PoolException e) {
            System.out.println("Make sure that server or port arguments were correct");
        }
    }

    private void handleList(@NotNull final String[] input) {
        if (input.length != 6) {
            System.out.println("Not enough arguments: 7 expected");
            return;
        }

        if (!input[2].equals("--server") || !input[4].equals("--port")) {
            System.out.println("Don't forget about flags");
            return;
        }

        final String server = input[3];
        try {
            final int port = Integer.parseInt(input[5]);
            System.out.println(client.getInterfaceNames(server, port));
        } catch (NumberFormatException e) {
            System.out.println("Port is not a digit value");
        } catch (InterruptedException | IOException | HttpException | PoolException e) {
            System.out.println("Make sure that server or port arguments were correct");
        }
    }

    private void handleHelp() {
        System.out.println(client.getHelp());
    }
}
