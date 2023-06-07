package com.moodle;

import com.moodle.DownloadManagerInt;
public class BrowserDownload implements DownloadManagerInt {

    private final long fileSize;
    private long receivedSize;

    public BrowserDownload(long fileSize) {
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
        return receivedSize == fileSize;
    }

    @Override
    public long gibVerbleibend() {
        return fileSize - receivedSize;
    }

    @Override
    public void empfange(long paketGroesse) {
        receivedSize += paketGroesse;
    }

    public void paketVollstaendigGespeichert(long paketGroesse) {
        if (paketGroesse < 0) {
            throw new IllegalArgumentException("Invalid packet size: " + paketGroesse);
        }
        if (paketGroesse > gibVerbleibend()) {
            throw new IllegalArgumentException("Packet size exceeds remaining size");
        }
        empfange(paketGroesse);
    }

}
