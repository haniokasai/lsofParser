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
}
