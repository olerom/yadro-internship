package com.yadro.server;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;

/**
 * Date: 27.11.17
 *
 * @author olerom
 */
public class OsNetwork {

    public List<NetworkingInterface> getInfo() throws IOException {
        Process ifconfig = Runtime.getRuntime().exec("ifconfig");
        InputStream inputStream = ifconfig.getInputStream();

        StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer, "UTF8");
        String theString = writer.toString();


        List<NetworkingInterface> parse = new NetworkingInterfaceParser().parse(theString);

        return parse;
    }

}
