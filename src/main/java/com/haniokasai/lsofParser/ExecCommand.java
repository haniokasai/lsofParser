package com.haniokasai.lsofParser;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ExecCommand {

    /**
     * @param cmdarray cmdをString[]で渡しなさい。
     * @return ArrayListに出力をまとめる、エラーが出ればnull
     */

    public  ArrayList<String> execCommand(String[] cmdarray){
        try {
            ProcessBuilder builder = new ProcessBuilder(cmdarray);
            builder.redirectErrorStream(true);

            Process process = builder.start();
            OutputStream stdin = process.getOutputStream();
            InputStream is = process.getInputStream();

            OutputLogger ol = new OutputLogger(is, process);
            ol.start();
            while(process.isAlive()){}
            if(Main.debug) System.out.println(ol.outputlines);
            return ol.outputlines;
        } catch (IOException e) {
            if(Main.debug)e.printStackTrace();
            return null;
        }

    }


}

class OutputLogger extends Thread {
    private final InputStream is;
    private final Process pr;
    public ArrayList<String> outputlines;

    public OutputLogger(InputStream is, Process pr) {
        this.is = is;
        this.pr = pr;
        outputlines = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
                for (; ; ) {
                    String line = br.readLine();
                    if (line == null) break;
                    //System.out.println("[" + title + "]" + line);
                    try {
                        outputlines.add(line);
                    } catch (NullPointerException e) {
                        if (Main.debug) e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            if (Main.debug) e.printStackTrace();
        }
    }
}