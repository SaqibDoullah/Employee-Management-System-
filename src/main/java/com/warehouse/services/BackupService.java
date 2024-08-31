// File: BackupService.java
package com.warehouse.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class BackupService {

    public void backupDatabase(String sourceFile, String destFile) {
        try (FileChannel sourceChannel = new FileInputStream(sourceFile).getChannel();
             FileChannel destChannel = new FileOutputStream(destFile).getChannel()) {

            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            System.out.println("Database backup successful.");

        } catch (IOException e) {
            System.out.println("Database backup failed: " + e.getMessage());
        }
    }
}
