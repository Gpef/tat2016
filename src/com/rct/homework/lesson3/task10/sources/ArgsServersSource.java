package com.rct.homework.lesson3.task10.sources;

import com.rct.homework.lesson3.task10.server.Server;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * Represents ipv4 address command line arguments reader.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 19.10.2016
 */
public class ArgsServersSource extends ServersSource {

    private String[] args;

    public ArgsServersSource(String[] source) {
        args = source;
    }

    @Override
    public ArrayList<Server> getServers() throws IOException {
        if (args.length == 0) {
            throw new InvalidParameterException("No servers found");
        }

        ArrayList<Server> servers = new ArrayList<>();
        for (String address : args) {
            try {
                servers.add(new Server(address));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return servers;
    }
}
