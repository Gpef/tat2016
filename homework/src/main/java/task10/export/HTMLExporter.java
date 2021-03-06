package task10.export;

import task10.server.Server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Exports server's data to .html file. Row with max server's ping
 * will be colored to red.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 17.10.2016
 */
public class HTMLExporter extends Exporter {

    private static final String COLOR_HEADER_CELL = "#999999";
    private static final String COLOR_NORMAL_CELL = "#EFEFEF";
    private static final String COLOR_WARNING_CELL = "#E86441";

    private static final String COLOR_BORDERS = "#FFFFFF";

    private final String FILE_HEADER
            = "<!DOCTYPE HTML>\n" +
            "<html>\n" +
            " <head>\n" +
            "  <meta charset=\"utf-8\">\n" +
            "  <title>Servers List</title>\n" +
            " </head>\n" +
            " <body>\n" +
            "  <style type=\"text/css\">\n" +
            "    table {\n" +
            "        border-collapse: collapse;\n" +
            "    }\n" +
            "    table thead {\n" +
            "        border-bottom: 3px solid #ffffff;\n" +
            "    }\n" +
            "    table td {\n" +
            "        padding-left: 8px;\n" +
            "    }\n" +
            "  </style>\n" +
            "  <table cellspacing = \"1\" bordercolor=\"" + COLOR_BORDERS + "\" border=\"1\" cols=\"2\">\n";

    private final String FILE_FOOTER
            = "   </tbody>\n" +
            "  </table>\n" +
            " </body>\n" +
            "</html>";

    private final String TABLE_HEADER
            = "   <thead>\n" +
            "    <tr valign=\"top\" bgcolor =\"" + COLOR_HEADER_CELL + "\" align = center height = \"30\"> \n" +
            "       <th width = \"300\">Server</th>\n" +
            "       <th width = \"300\">Response, ms</th>\n" +
            "    </tr>\n" +
            "   </thead>\n" +
            "   <tbody>\n";

    /**
     * @param serversData list of servers to export into table
     * @param outputFile  file into all data will be written
     * @throws IOException if problems with file writing occurred
     *                     (error in permissions etc.)
     */
    @Override
    public void export(ArrayList<Server> serversData, File outputFile) throws IOException {
        BufferedWriter output = new BufferedWriter(new FileWriter(outputFile));
        output.write(getHeader());
        output.write(getTableHeader());

        int maxPing = serversData.get(0).getPing();
        for (Server server : serversData) {
            if (server.getPing() > maxPing) {
                maxPing = server.getPing();
            }
        }
        for (Server server : serversData) {
            output.write(getCell(server.getIpv4Address(),
                    String.valueOf(server.getPing()),
                    (server.getPing() == maxPing)));
        }

        output.write(getFooter());
        output.close();
    }

    /**
     * @return html file header
     */
    private String getHeader() {
        return FILE_HEADER;
    }

    /**
     * @return html file footer
     */
    private String getFooter() {
        return FILE_FOOTER;
    }

    /**
     * @return html table header contains two columns
     * (Server address and ping) to paste into file
     */
    private String getTableHeader() {
        return TABLE_HEADER;
    }

    /**
     * Returns row ready to paste into table. If ping of
     * server is the highest - this row will be painted red.
     *
     * @param isAlarmed {@code true} if cell must be painted because of
     *                  too high ping
     * @param ip        ip address of server
     * @param ping      server's ping
     * @return ready to paste table row
     */
    private String getCell(String ip, String ping, boolean isAlarmed) {
        return "    <tr bgcolor = \" " + (isAlarmed ? COLOR_WARNING_CELL : COLOR_NORMAL_CELL) + "\">\n" +
                "     <td>" + ip + "</td>\n" +
                "     <td>" + ping + "</td>\n" +
                "    </tr>\n";
    }
}
