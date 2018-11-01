package com.haniokasai.lsofParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class ExecCommand {
    public static boolean islsofEnable(){
        try {
            //http://n-agetsuma.hatenablog.com/entry/2014/02/12/215321
            ProcessBuilder pb = new ProcessBuilder("lsof","-v");
            Process lsof = pb.start();
            lsof.waitFor();

            //listを用意
            ArrayList<String> outputlines = new ArrayList<>();

            // 実行結果を取得するストリームの種別を出力
            System.out.println(pb.redirectInput());

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(lsof.getInputStream()))) {
                //結果の出力
                for(String line = br.readLine(); line != null; line = br.readLine()) {
                    outputlines.add(line);
                    if(Main.debug)System.out.println(line);
                }
            }
        } catch (IOException | InterruptedException e) {
            if(Main.debug)e.printStackTrace();
            return false;
        }
        return true;
    }
}
