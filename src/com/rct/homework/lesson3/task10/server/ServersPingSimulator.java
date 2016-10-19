package com.rct.homework.lesson3.task10.server;

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
     * Performs server poll simulation
     *
     * @param server server to ping addresses
     */
    public void pingServer(Server server) {
        server.setPing(new Random().nextInt(490) + 10);
    }
}
