package com.hzc.coolcatmusic.utils;

import org.springframework.beans.factory.annotation.Value;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServiceUtil {
    @Value("${server.port}")
    public static int serverPort;

    public static String getUrl() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
            return "http://" + address.getHostAddress() + ":" + serverPort;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "";
    }
}
