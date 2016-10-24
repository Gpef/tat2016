package com.rct.homework.lesson3.taks09;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 15.10.2016
 */
public class HtmlImporter extends Importer {

    private final String HEAD_COLOR = "#cecfce";
    private final String COLOR_1 = "#efefef";
    private final String COLOR_2 = "#f7f7f7";
    private final String BORDER_COLOR = "#ffffff";

    private final String FILE_HEADER = "<!DOCTYPE HTML>\n" +
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
            "        border-bottom: 4px solid #ffffff;\n" +
            "    }\n" +
            "    table td {\n" +
            "        padding-left: 9px;\n" +
            "    }\n" +
            "  </style>\n" +
            "  <table cellspacing = \"1\" bordercolor=\"" + BORDER_COLOR + "\" border=\"1\" cols=\"4\">\n";
    private final String FILE_FOOTER = "   </tbody>\n" +
            "  </table>\n" +
            " </body>\n" +
            "</html>";
    private final String TABLE_HEAD = "   <thead>\n" +
            "    <tr valign=\"top\" bgcolor =\"" + HEAD_COLOR + "\" align = center height = \"30\"> \n" +
            "       <th width = \"130\">ИМЯ</th>\n" +
            "       <th width = \"130\">ТИП</th>\n" +
            "       <th width = \"130\">ДАТА СОЗДАНИЯ</th>\n" +
            "       <th width = \"130\">РАЗМЕР (в Kb)</th>\n" +
            "    </tr>\n" +
            "   </thead>\n" +
            "   <tbody>\n";

    @Override
    public void getImport(ArrayList<File> folders, ArrayList<File> files, File outputFile) throws IOException {
        BufferedWriter output = new BufferedWriter(new FileWriter(outputFile));
        output.write(FILE_HEADER);
        output.write(TABLE_HEAD);

        ArrayList<File> allFiles = new ArrayList<>();
        allFiles.addAll(folders);
        allFiles.addAll(files);
        String cellColor = COLOR_1;

        for (File file : allFiles) {
            if (cellColor.equals(COLOR_1)) {
                cellColor = COLOR_2;
            } else {
                cellColor = COLOR_1;
            }
            output.write(getCell(file, cellColor));
        }
        output.write(FILE_FOOTER);
        output.close();
    }

    /**
     * Returns ready to insert into table raw of data
     * about folder\file (name, size, type and creation date)
     *
     * @param file file to put data from
     * @return ready to insert raw
     */
    private String getCell(File file, String cellColor) throws IOException {
        BasicFileAttributes attributes = Files.readAttributes(Paths.get(file.getCanonicalPath()), BasicFileAttributes.class);
        String fileName = file.getName();
        String type = "";
        long size = 0;
        if (file.isFile()) {
            type = "FILE";
            size = FileSearcher.bytesToKib(file.length());
        }
        if (file.isDirectory()) {
            type = "DIR";
            size = FileSearcher.bytesToKib(FileSearcher.getFolderSize(file));
        }
        FileTime date = attributes.creationTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM.dd.yyyy");
        String creationDate = simpleDateFormat.format(date.toMillis());

        return "    <tr bgcolor = \" " + cellColor + "\">\n" +
                "     <td>" + fileName + "</td>\n" +
                "     <td>" + type + "</td>\n" +
                "     <td>" + creationDate + "</td>\n" +
                "     <td>" + size + "</td>\n" +
                "    </tr>\n";
    }

}
