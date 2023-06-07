package com.moodle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BrowserDownloadTest {

    private static final long FILE_SIZE = 1000;

    private BrowserDownload download;

    @BeforeEach
    public void setUp() {
        download = new BrowserDownload(FILE_SIZE);
    }

    @Test
    public void testGibGroesse() {
        long actualSize = download.gibGroesse();
        Assertions.assertEquals(FILE_SIZE, actualSize);
    }

    @Test
    public void testGibFortschritt() {
        double actualProgress = download.gibFortschritt();
        Assertions.assertEquals(0, actualProgress);
    }

    @Test
    public void testIstFertig() {
        boolean isFinished = download.istFertig();
        Assertions.assertFalse(isFinished);
    }

    @Test
    public void testGibVerbleibend() {
        long remainingSize = download.gibVerbleibend();
        Assertions.assertEquals(FILE_SIZE, remainingSize);
    }

    @Test
    public void testEmpfange_ValidPacketSize() {
        long packetSize = 500;
        download.empfange(packetSize);
        long remainingSize = download.gibVerbleibend();
        Assertions.assertEquals(FILE_SIZE - packetSize, remainingSize);
    }

    @Test
    public void testEmpfange_NegativePacketSize() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> download.empfange(-100));
    }

    @Test
    public void testEmpfange_ExceedingPacketSize() {
        long packetSize = FILE_SIZE + 100;
        Assertions.assertThrows(IllegalArgumentException.class, () -> download.empfange(packetSize));
    }

    @Test
    public void testLadeNeuesPaket_ValidPacketSize() {
        long packetSize = 500;
        download.ladeNeuesPaket(packetSize);
        long remainingSize = download.gibVerbleibend();
        Assertions.assertEquals(FILE_SIZE - packetSize, remainingSize);
    }

    @Test
    public void testLadeNeuesPaket_NegativePacketSize() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> download.ladeNeuesPaket(-100));
    }

    @Test
    public void testLadeNeuesPaket_ExceedingPacketSize() {
        long packetSize = FILE_SIZE + 100;
        Assertions.assertThrows(IllegalArgumentException.class, () -> download.ladeNeuesPaket(packetSize));
    }

    @Test
    public void testLadeNeuesPaket_WhenDownloadComplete() {
        long packetSize = FILE_SIZE;
        download.ladeNeuesPaket(packetSize);
        Assertions.assertThrows(IllegalStateException.class, () -> download.ladeNeuesPaket(packetSize));
    }
}
