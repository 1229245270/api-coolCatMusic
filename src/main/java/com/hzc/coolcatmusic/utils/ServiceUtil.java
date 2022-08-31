package com.hzc.coolcatmusic.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServiceUtil {

    public static String getUrl(int post) {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
            return "http://" + address.getHostAddress() + ":" + post;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "";
    }
}
