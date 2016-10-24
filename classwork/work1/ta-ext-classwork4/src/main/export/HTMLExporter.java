package main.export;

import main.ColorUtils;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 21.10.2016
 */
public class HTMLExporter extends Exporter {

    private static final String COLOR_HEADER_CELL = "#FFFFFF";

    private final String FILE_HEADER
            = "<!DOCTYPE HTML>\n" +
            "<html>\n" +
            " <head>\n" +
            "  <meta charset=\"utf-8\">\n" +
            "  <title>Gradient table</title>\n" +
            " </head>\n" +
            " <body>\n" +
            "  <table align = center cellspacing = \"0\" border=\"0\" cols=\"1\">\n";

    private final String FILE_FOOTER
            = "  </table>\n" +
            " </body>\n" +
            "</html>";

    private final String TABLE_HEADER
            = "   <thead>\n" +
            "    <tr valign=\"top\" bgcolor =\"" + COLOR_HEADER_CELL + "\" height = \"30\"> \n" +
            "       <th width = \"300\">HEAD</th>\n" +
            "    </tr>\n" +
            "   </thead>\n" +
            "   <tbody>\n";

    @Override
    public void export(ArrayList<Integer> exportData, File outputFile) throws IOException {
        BufferedWriter output = new BufferedWriter(new FileWriter(outputFile));
        output.write(getHeader());
        output.write(getTableHeader());

        int dataAmount = exportData.size();
        int oneColorStep = 255 / dataAmount;

        Color cellColor = Color.decode(COLOR_HEADER_CELL);
        String nextString;
        for (Integer anExportData : exportData) {
            nextString = String.valueOf(anExportData);
            output.write(getCell(nextString, ColorUtils.getHexString(cellColor)));
            cellColor = ColorUtils.getDarkerColor(cellColor, oneColorStep);
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
     * @return html table header contains one column
     * with random data to paste into file
     */
    private String getTableHeader() {
        return TABLE_HEADER;
    }

    /**
     * Returns row ready to paste into table.
     *
     * @param cellData String to input into table cell
     * @param color    color of the cell
     * @return ready to paste table row
     */
    private String getCell(String cellData, String color) {
        return "    <tr bgcolor = \" " + color + "\">\n" +
                "     <td>" + cellData + "</td>\n" +
                "    </tr>\n";
    }
}
