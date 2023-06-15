package com.example.myapplication;

import java.io.DataOutputStream;
import java.io.IOException;

public class ShellCommandExecuter {

    public static void runShellCommand (String command)
    {
        try {
            Process process = Runtime.getRuntime().exec("su");
            DataOutputStream outputStream = new DataOutputStream(process.getOutputStream());

            outputStream.writeBytes(command + "\n");
            outputStream.flush();
            outputStream.writeBytes("exit\n");
            outputStream.flush();

            process.waitFor();
            outputStream.close();
        }  catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
