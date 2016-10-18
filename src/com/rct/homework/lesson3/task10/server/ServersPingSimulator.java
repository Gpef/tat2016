package com.rct.homework.lesson3.task10.server;

import java.util.ArrayList;
import java.util.Random;

/**
 * Simulates servers polling.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 17.10.2016
 */
public class ServersPingSimulator {

    /**
     * Performs servers poll by calling ping method from
     * {@code Server} class
     *
     * @param servers servers to ping addresses
     */
    public void pingServers(ArrayList<Server> servers) {
        for (Server server : servers) {
            server.setPing(new Random().nextInt(490) + 10);
        }
    }
}
