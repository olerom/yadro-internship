package com.yadro;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Date: 02.12.17
 *
 * @author olerom
 */
public class Example {
    public static void main(String[] args) throws IOException {
        final Service service = new Service(8080);
        final ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(service::run);

        final CliApp cliApp = new CliApp();
        cliApp.run();
    }
}
