package com.rct.homework.lesson3.task10.server;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provides methods to validate an IP address.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 18.10.2016
 */
public class InetAddressValidator {

    private static final String IPV4_REGEX = "^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$";

    private static final int IPV4_MAX_OCTET_VALUE = 255;

    /**
     * Validates an IPv4 address. Returns true if valid.
     *
     * @param inet4Address the IPv4 address to validate
     * @return true if the argument contains a valid IPv4 address
     */
    public boolean isValidInet4Address(String inet4Address) {
        Pattern pattern = Pattern.compile(IPV4_REGEX);
        Matcher matcher = pattern.matcher(inet4Address);
        if (matcher.matches()) {
            String[] groups = inet4Address.split("\\.");
            if (groups.length == 0) {
                return false;
            }

            for (String ipOctet : groups) {
                if (ipOctet == null || ipOctet.length() == 0) {
                    return false;
                }

                int ipOctetValue = 0;
                try {
                    ipOctetValue = Integer.parseInt(ipOctet);
                } catch (NumberFormatException e) {
                    return false;
                }

                if (ipOctetValue > IPV4_MAX_OCTET_VALUE) {
                    return false;
                }

                if (ipOctet.length() > 1 && ipOctet.startsWith("0")) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
