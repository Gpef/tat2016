package com.rct.homework.lesson3.task10.export;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Exports {@code PreparedData} prepared to export server's data to .html file
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 17.10.2016
 */
public class HTMLExporter extends Exporter {

    private static final String COLOR_HEADER_CELL = "#999999";
    private static final String COLOR_NORMAL_CELL = "#EFEFEF";
    private static final String COLOR_BORDERS = "#FFFFFF";

    private static final String COLOR_WARNING = "#E86441";
    private static final String COLOR_COMMON = "#EFEFEF";

    private final String FILE_HEADER
            = "<!DOCTYPE HTML>\n" +
            "<html>\n" +
            " <head>\n" +
            "  <meta charset=\"utf-8\">\n" +
            "  <title>Files in directory</title>\n" +
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

    @Override
    public void export(ArrayList<PreparedData> serversData, File outputFile) throws IOException {
        BufferedWriter output = new BufferedWriter(new FileWriter(outputFile));
        output.write(getHeader());
        output.write(getTableHeader());
        for (PreparedData preparedData : serversData) {
            output.write(getCell(preparedData));
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
     * @param preparedData data to write into string
     * @return ready to paste table cell
     */
    private String getCell(PreparedData preparedData) {
        return "    <tr>\n" +
                "     <td bgcolor = \" " + COLOR_NORMAL_CELL + "\">" + preparedData.address + "</td>\n" +
                "     <td bgcolor = \" " + (preparedData.isAlarmed ? COLOR_WARNING : COLOR_COMMON) + "\">" + preparedData.ping + "</td>\n" +
                "    </tr>\n";
    }
}
