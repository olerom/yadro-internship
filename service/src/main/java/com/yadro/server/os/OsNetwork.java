package com.yadro.server.os;

import com.yadro.server.pojos.NetworkingInterface;
import com.yadro.server.parser.NetworkingInterfaceParser;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;

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

    @NotNull
    public List<NetworkingInterface> getInfo() throws IOException {
        final Process ifconfig = Runtime.getRuntime().exec("ifconfig");
        final InputStream inputStream = ifconfig.getInputStream();

        final StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer, "UTF8");
        final String info = writer.toString();

        return new NetworkingInterfaceParser().parse(info);
    }

}
