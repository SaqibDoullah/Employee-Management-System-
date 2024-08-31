// File: RestoreService.java
package com.warehouse.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class RestoreService {

    public void restoreDatabase(String backupFile, String destFile) {
        try (FileChannel sourceChannel = new FileInputStream(backupFile).getChannel();
             FileChannel destChannel = new FileOutputStream(destFile).getChannel()) {

            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            System.out.println("Database restore successful.");

        } catch (IOException e) {
            System.out.println("Database restore failed: " + e.getMessage());
        }
    }
}
