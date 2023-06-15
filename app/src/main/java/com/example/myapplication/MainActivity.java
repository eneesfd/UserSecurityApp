package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    Button stopButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService();
            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService();
            }
        });
    }
    private void startService()
    {
        ShellCommandExecuter shellCommandExecuter = new ShellCommandExecuter();
        Intent serviceIntent = new Intent(getApplicationContext(), MyService.class);
        startService(serviceIntent);
        ShellCommandExecuter.runShellCommand("echo 1 > sys/class/gpio/gpio56/value");
    }

    private void stopService()
    {
        ShellCommandExecuter shellCommandExecuter = new ShellCommandExecuter();
        Intent serviceIntent = new Intent(getApplicationContext(), MyService.class);
        stopService(serviceIntent);
        Toast.makeText(this, "Servis Çalışmayı Durdurdu", Toast.LENGTH_SHORT).show();
        ShellCommandExecuter.runShellCommand("echo 1 > sys/class/gpio/gpio56/value");
    }
}