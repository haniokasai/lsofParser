package com.haniokasai.lsofParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.haniokasai.lsofParser.ExecCommand.execCommand;

public class Parse {
    public static boolean islsofEnable(){
        final ArrayList <String> commandoutput = execCommand(new String[]{"lsof", "-v"});
        if(commandoutput == null)return false;
        //https://teratail.com/questions/11397
        /*
        # lsof -v
        lsof version information:
        revision: 4.89
        latest revision: ftp://lsof.itap.purdue.edu/pub/tools/unix/lsof/
        latest FAQ: ftp://lsof.itap.purdue.edu/pub/tools/unix/lsof/FAQ
        latest man page: ftp://lsof.itap.purdue.edu/pub/tools/unix/lsof/lsof_man
        compiler: cc
        compiler version: 5.2.1 20151022 (Ubuntu/Linaro 5.2.1-22ubuntu5)
        compiler flags: -g -O2 -fPIE -fstack-protector-strong -Wformat -Werror=format-security -D_FORTIFY_SOURCE=2 -DLINUXV=42003 -DGLIBCV=221 -DHASIPv6 -DNEEDS_NETINET_TCPH -DHASSELINUX -DHASUXSOCKEPT -D_FILE_OFFSET_BITS=64 -D_LARGEFILE64_SOURCE -DHAS_STRFTIME -DLSOF_VSTR="4.2.3" -O
        loader flags: -L./lib -llsof -Wl,-Bsymbolic-functions -fPIE -pie -Wl,-z,relro -Wl,-z,now -lselinux
        Anyone can list all files.
        /dev warnings are disabled.
        Kernel ID check is disabled.
         */
        return commandoutput.stream().anyMatch(x -> commandoutput.contains("lsof version"));
    }

    public static String getlsofVersion(){
        final ArrayList <String> commandoutput = execCommand(new String[]{"lsof", "-v"});
        if(commandoutput == null)return null;
        for (String line : commandoutput) {
            if (line.contains("revision")) {
                return line.replaceAll("revision:","").trim();
            }
        }
        return null;
    }

    //TODO processリストの方を作って、それにプロセス名、pid、tcp,udpなど書く。
    //TODO ProcessListOption型　COMMAND   PID     USER   FD   TYPE  DEVICE SIZE/OFF NODE NAME
    //lsof -i:80
    /*
    # lsof -i:80
    COMMAND   PID     USER   FD   TYPE  DEVICE SIZE/OFF NODE NAME
    nginx   14900     root    7u  IPv4 1684384      0t0  TCP *:http (LISTEN)
    nginx   14900     root    9u  IPv6 1684386      0t0  TCP *:http (LISTEN)
    nginx   20548 www-data    7u  IPv4 1684384      0t0  TCP *:http (LISTEN)
    nginx   20548 www-data    9u  IPv6 1684386      0t0  TCP *:http (LISTEN)
    nginx   20549 www-data    7u  IPv4 1684384      0t0  TCP *:http (LISTEN)
    nginx   20549 www-data    9u  IPv6 1684386      0t0  TCP *:http (LISTEN)
    nginx   20550 www-data    7u  IPv4 1684384      0t0  TCP *:http (LISTEN)
    nginx   20550 www-data    9u  IPv6 1684386      0t0  TCP *:http (LISTEN)
    nginx   20551 www-data    7u  IPv4 1684384      0t0  TCP *:http (LISTEN)
    nginx   20551 www-data    9u  IPv6 1684386      0t0  TCP *:http (LISTEN)

     */
    public static String getProcessFromPort(int port,ProcessListOption option){

    }

    //https://unix.stackexchange.com/a/157824
    //lsof -Pan -p PID -i
    /*
    # lsof -Pan -p 26463 -i
    lsof: WARNING: can't stat() fuse.gvfsd-fuse file system /run/user/114/gvfs
    Output information may be incomplete.
    COMMAND     PID    USER   FD   TYPE  DEVICE SIZE/OFF NODE NAME
    Main\x20T 26463 root    6u  IPv4 4324999      0t0  UDP *:31076
    Main\x20T 26463 root    7u  IPv6 4325000      0t0  UDP *:31077
     */
    public static String getProcessFromPid(int port,ProcessListOption option){

    }

}
