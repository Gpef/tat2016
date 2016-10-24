package task10.server;

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

    public Server(String ip) throws Exception {
        if (!new InetAddressValidator().isValidInet4Address(ip)) {
            throw new Exception("error parsing ip " + ip);
        }
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
