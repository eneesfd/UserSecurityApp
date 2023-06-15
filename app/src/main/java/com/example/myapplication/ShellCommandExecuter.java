package com.example.myapplication;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;

public class ShellCommandExecuter {

    public static void runShellCommand (String command)
    {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("su", "-c", command);
            Process process = processBuilder.start();

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                Log.d("ShellCommand","Succes");
            } else {
                Log.d("ShellCommand","Failed");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
