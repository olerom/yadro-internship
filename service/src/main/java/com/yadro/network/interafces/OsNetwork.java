package com.yadro.network.interafces;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;

/**
 * Date: 27.11.17
 *
 * @author olerom
 */
public class OsNetwork {

    public int getInfo() {
        try {
            Process ifconfig = Runtime.getRuntime().exec("ifconfig");
            InputStream inputStream = ifconfig.getInputStream();

            StringWriter writer = new StringWriter();
            IOUtils.copy(inputStream, writer, "UTF8");
            String theString = writer.toString();
            System.out.println(theString);
        } catch (IOException e) {
            System.out.println(":(");
            e.printStackTrace();
        }

        return 0;
    }

}
