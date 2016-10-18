package com.rct.homework.lesson3.task10.export;

import com.rct.homework.lesson3.task10.server.Server;

/**
 * Represents entity that contains ready-to-export servers data,
 * like address, ping and alarm bool for table cell.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 18.10.2016
 */
public class PreparedData {

    final String address;
    final String ping;
    final boolean isAlarmed;

    public PreparedData(Server server, boolean alarmed){
        address = server.getIpv4Address();
        ping = String.valueOf(server.getPing());
        isAlarmed = alarmed;
    }
}
