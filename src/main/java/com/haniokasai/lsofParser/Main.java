package com.haniokasai.lsofParser;

import static com.haniokasai.lsofParser.Parse.*;

public class Main {
    public static boolean debug = true;
    public static void main(String args[]) {
        if(debug){
            System.out.println(islsofEnable());
            System.out.println(getlsofVersion());
            System.out.println(getIPv4PortFromPid(Integer.parseInt(args[0])));
            System.out.println(getPidFromPort(Integer.parseInt(args[1])));
        }
    }
}
