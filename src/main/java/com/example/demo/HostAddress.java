package com.example.demo;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostAddress {
    public static void main(String[] args){
        System.out.println("host test");

        try {
            getHostInfo();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    public static void getHostInfo() throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();
        String ip=addr.getHostAddress().toString(); //获取本机ip
        String hostName=addr.getHostName().toString(); //获取本机计算机名称
        System.out.println(ip);
        System.out.println(hostName);


    }
}
