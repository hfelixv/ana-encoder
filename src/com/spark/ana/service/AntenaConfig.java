package com.spark.ana.service;


import reader.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AntenaConfig {
    public static Reader reader = null;

//    public static boolean isAlarmOn = true;
    //antenna extension
//    private static Short numOfAntennas = 4;

    public static void initReader() {
        try {
            reader = new Reader();

            // is reader connected?
            System.out.println("Connecting");
            System.out.println("isConnected = " + reader.isConnected);

            // Get avaiable ports on your computer
            String[] ports = reader.getAvaiablePorts();

            // As a good practice we will reset the reader to clean up old configurations
            ReaderSettings settings = new ReaderSettings();
            reader.resetSettings();

            if (ports.length > 0) {
                // Configure Port
                // hfv: We assume reader connected to lowest port
                settings.Port = ports[0];

                // Configure read mode
                settings.SearchMode = SearchMode.DualTarget;

                // Configure session mode
                settings.Session = 0;

                // Configure Power
                settings.TxPower = 10;
                //settings.TxPower = 23; from multiEpc example

                // Apply settings
                // Apply the newly modified settings to reader
                System.out.println("Applying Settings");
                reader.applySettings(settings);

                //hfv
//                final String[] seenTag = new String[1];

                // Register the callbacks
                // Assign the OnComplete event listener.
                reader.setOnCompleteListener(new TagReportListenerImplementation());

//                reader.setOnCompleteListener(new OnTagReport() {
//                    @Override
//                    public void onReport(String data) {
//                        seenTag[0] = data;
//                        System.out.println(data);
//                    }
//                });

            } else {
                System.out.println("No ports found!");
            }
        }catch (Exception e) {
            System.out.println("Something happened when setting the reader");
            e.printStackTrace();
        }

    }


}
