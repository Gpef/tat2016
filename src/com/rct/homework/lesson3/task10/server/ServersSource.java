package com.rct.homework.lesson3.task10.server;

import java.io.*;
import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * Represents ipv4 address file reader.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 15.10.2016
 */
public class ServersSource {

    /**
     * Performs addresses search in specified file. Adds all valid addresses
     * to {@code ArrayList} and finally returns this list.
     *
     * @param source file to search
     * @return {@code ArrayList} of addresses if there is at least 1 address in file
     * @throws InvalidParameterException if file doesn't contain any server addresses
     * @throws FileNotFoundException     if {@code File} source isn't exists
     */
    public ArrayList<Server> getServersFromFile(File source) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(source));
        ArrayList<Server> servers = new ArrayList<>();
        String currentAddressLine;

        while ((currentAddressLine = reader.readLine()) != null) {
            try {
                servers.add(Server.Builder.build(currentAddressLine));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        if (servers.size() == 0) {
            throw new InvalidParameterException("No servers found");
        }
        return servers;
    }

    /**
     * Performs addresses search in command line arguments. Adds all valid addresses
     * to {@code ArrayList} and finally returns this list.
     *
     * @param args command line arguments
     * @return {@code ArrayList} of addresses if there is at least 1 address in args,
     * @throws InvalidParameterException if args doesn't contain any server addresses
     */
    public ArrayList<Server> getServersFromArgs(String[] args) {
        if (args.length == 0) {
            throw new InvalidParameterException("No servers found");
        }

        ArrayList<Server> servers = new ArrayList<>();
        for (String address : args) {
            try {
                servers.add(Server.Builder.build(address));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return servers;
    }
}
