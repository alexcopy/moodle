package com.moodle;

import com.moodle.DownloadManagerInt;

public class BrowserDownload implements DownloadManagerInt {

    private final long fileSize;
    private long receivedSize;
    private long paketGroesse;

    public BrowserDownload(long fileSize) {
        this.fileSize = fileSize;
        this.receivedSize = 0;
    }

    /**
     * getFileSize()
     * @return long
     */
    @Override
    public long gibGroesse() {
        return fileSize;
    }

    /**
     * getDownloadProgress()
     * @return double
     */
    @Override
    public double gibFortschritt() {
        double result;
        if (fileSize == 0) {
            result = 0;
        } else {
            result = (double) receivedSize / fileSize;
        }
        return result;
    }

    /**
     * isDownloadComplete()
     * @return bool
     */
    @Override
    public boolean istFertig() {
        return receivedSize == fileSize;
    }

    /**
     * getRemainingSize()
     * @return long
     */
    @Override
    public long gibVerbleibend() {
        return fileSize - receivedSize;
    }


    /**
     * markNewPacketAsReceived()
     * @param paketGroesse die Anzahl der neu empfangenen Bytes
     */
    @Override
    public void empfange(long paketGroesse) {
        if (paketGroesse < 0) {
            throw new IllegalArgumentException("Packet size cannot be negative");
        }
        if (paketGroesse > gibVerbleibend()) {
            throw new IllegalArgumentException("Packet size exceeds remaining size");
        }
        receivedSize += paketGroesse;
    }


    /**
     * loadNewPackage()
     * @param paketGroesse
     */
    public void ladeNeuesPaket(long paketGroesse) {
        this.paketGroesse = paketGroesse;
        if (istFertig()) {
            throw new IllegalStateException("Download is already complete");
        }
        empfange(paketGroesse);
    }
}

