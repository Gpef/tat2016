package com.rct.homework.lesson3.task10;

import com.rct.homework.lesson3.task10.export.HTMLExporter;
import com.rct.homework.lesson3.task10.server.Server;
import com.rct.homework.lesson3.task10.server.ServerPingSimulator;
import com.rct.homework.lesson3.task10.sources.ArgsServersSource;
import com.rct.homework.lesson3.task10.sources.FileServersSource;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * Main class of application
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 15.10.2016
 */
public class Main {

    private static final String inputPath = "ServersList.txt";
    private static final String outputPath = "ServersPings.html";

    /**
     * Entrance point to the program. Searches server addresses in args
     * (if there is some args) or in file. Simulates servers poll and
     * exports ping data to the .html file.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        try {
            ArrayList<Server> servers;
            if (args.length == 0) {
                servers = new FileServersSource(new File(inputPath)).getServers();
            } else {
                servers = new ArgsServersSource(args).getServers();
            }
            ServerPingSimulator pingSimulator = new ServerPingSimulator();
            for(Server server : servers){
                pingSimulator.pingServer(server);
            }
            HTMLExporter exporter = new HTMLExporter();
            exporter.export(servers, new File(outputPath));
        } catch (IOException | InvalidParameterException e) {
            System.out.println(e.getMessage());
        }
    }
}
