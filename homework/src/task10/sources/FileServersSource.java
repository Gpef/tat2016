package task10.sources;

import task10.server.Server;

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
public class FileServersSource extends ServersSource{

    private File sourceFile;

    public FileServersSource(File source){
        sourceFile = source;
    }

    @Override
    public ArrayList<Server> getServers() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
        ArrayList<Server> servers = new ArrayList<>();
        String currentAddressLine;

        while ((currentAddressLine = reader.readLine()) != null) {
            try {
                servers.add(new Server(currentAddressLine));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        if (servers.size() == 0) {
            throw new InvalidParameterException("No servers found");
        }
        return servers;
    }
}
