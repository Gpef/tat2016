package com.rct.homework.lesson3.task10.server;

/**
 * Represents server entity with it's ipv4 address and ping.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 16.10.2016
 */
public class Server {

    private String ipv4Address;
    private int ping;

    /**
     * Performs all build operations, firstly checks if Server with
     * specified ip can be built.
     */
    public abstract static class Builder {

        /**
         * Tries to build {@code Server} object with received ip
         *
         * @return {@code true} if object can be built and {@code false} otherwise
         */
        private static boolean canBuild(String ip) {
            if (new InetAddressValidator().isValidInet4Address(ip)) {
                return true;
            }
            return false;
        }

        /**
         * Method takes {@code String} ipv4 address.
         * By calling canBuild method checks if it's
         * valid address and if everything is good
         * creates object that represents server.
         * If ip address isn't valid - object won't be created and
         * message about error will be shown.
         *
         * @param ip ipv4 address of server
         */
        public static Server build(String ip) throws Exception {
            if (!canBuild(ip)) {
                throw new Exception("error parsing ip " + ip);
            }
            return new Server(ip);
        }
    }

    private Server(String ip) {
        ipv4Address = ip;
    }

    /**
     * @return {@code String} ipv4 address of server
     */
    public String getIpv4Address() {
        return ipv4Address;
    }

    /**
     * @return server delay time(ping)
     */
    public int getPing() {
        return ping;
    }

    public void setPing(int _ping) {
        ping = _ping;
    }
}
