package com.rct.homework.lesson3.task10.export;

import com.rct.homework.lesson3.task10.server.Server;

import java.util.ArrayList;

/**
 * Provides methods to prepare raw servers list to handy for export form.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 18.10.2016
 */
public class DataComposer {


    /**
     * Prepares raw servers list to export
     *
     * @param rawServersData raw servers data to export
     * @return prepared to export data
     */
    public ArrayList<PreparedData> compose(ArrayList<Server> rawServersData) {
        ArrayList<PreparedData> preparedData = new ArrayList<>();
        int maxPing = rawServersData.get(0).getPing();
        for (Server server : rawServersData) {
            if (server.getPing() > maxPing) {
                maxPing = server.getPing();
            }
        }
        for (Server server : rawServersData) {
            preparedData.add(new PreparedData(server, (server.getPing() == maxPing)));
        }
        return preparedData;
    }
}
