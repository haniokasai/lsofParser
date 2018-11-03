package com.haniokasai.lsofParser;

import static com.haniokasai.lsofParser.Parse.*;

public class Main {
    public static boolean debug = false;
    public static void main(String args[]) {
        /*if(args.length<3)debug=false;
        if(debug | args.length > 3){
            System.out.println(islsofEnable());
            System.out.println(getlsofVersion());
            System.out.println(getIPv4PortFromPid(Integer.parseInt(args[0])));
            System.out.println(getPidFromPort(Integer.parseInt(args[1])));
        }*/
        System.out.println("lsof:"+String.valueOf(islsofEnable()));
        switch (args.length){
            case 0:
            case 1:
                System.out.println("<getport/getpid> int <debug>");
                break;
            case 3:
                debug=true;
            case 2:
                switch (args[0]){
                    case "getport":
                        System.out.println(getIPv4PortFromPid(Integer.parseInt(args[1])));
                        break;
                    case "getpid":
                        System.out.println(getPidFromIPv4Port(Integer.parseInt(args[1])));
                    break;
                }
                break;
            default:
                System.out.println("<getport/getpid> int (debug)<true/false/null>");
                break;
        }
    }
}
