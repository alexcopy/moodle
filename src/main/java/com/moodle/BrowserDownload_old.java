package com.moodle;

import com.moodle.DownloadManagerInt;

public class BrowserDownload_old implements DownloadManagerInt {


    private final long fileSize;
    private long receivedSize;

    public BrowserDownload_old(long fileSize) {
        this.fileSize = fileSize;
        this.receivedSize = 0;
    }

    @Override
    public long gibGroesse() {
        return fileSize;
    }

    @Override
    public double gibFortschritt() {
        return (double) receivedSize / fileSize;
    }

    @Override
    public boolean istFertig() {
        return receivedSize >= fileSize;
    }

    @Override
    public long gibVerbleibend() {
        return fileSize - receivedSize;
    }

    @Override
    public void empfange(long paketGroesse) {
        if (paketGroesse < 0) {
            throw new IllegalArgumentException("Packet size cannot be negative");
        }
        if (paketGroesse > gibVerbleibend()) {
            throw new IllegalArgumentException("Packet size cannot be greater than remaining size");
        }
        receivedSize += paketGroesse;
    }
}
