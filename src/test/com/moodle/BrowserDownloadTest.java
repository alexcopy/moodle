package com.moodle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BrowserDownloadTest {

    private BrowserDownload download;

    @BeforeEach
    public void setup() {
        long fileSize = 1000;
        download = new BrowserDownload(fileSize);
    }

    @Test
    public void testGibGroesse() {
        long expectedSize = 1000;
        long actualSize = download.gibGroesse();
        Assertions.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testGibFortschritt() {
        double expectedProgress = 0.0;
        double actualProgress = download.gibFortschritt();
        Assertions.assertEquals(expectedProgress, actualProgress);

        download.empfange(500);
        expectedProgress = 0.5;
        actualProgress = download.gibFortschritt();
        Assertions.assertEquals(expectedProgress, actualProgress);

        download.empfange(300);
        expectedProgress = 0.8;
        actualProgress = download.gibFortschritt();
        Assertions.assertEquals(expectedProgress, actualProgress);
    }

    @Test
    public void testIstFertig() {
        Assertions.assertFalse(download.istFertig());

        download.empfange(1000);
        Assertions.assertTrue(download.istFertig());
    }

    @Test
    public void testGibVerbleibend() {
        long expectedRemaining = 1000;
        long actualRemaining = download.gibVerbleibend();
        Assertions.assertEquals(expectedRemaining, actualRemaining);

        download.empfange(500);
        expectedRemaining = 500;
        actualRemaining = download.gibVerbleibend();
        Assertions.assertEquals(expectedRemaining, actualRemaining);
    }

    @Test
    public void testEmpfange() {
        download.empfange(500);
        double expectedReceivedSize = 0.5;
        double actualReceivedSize = download.gibFortschritt();
        Assertions.assertEquals(expectedReceivedSize, actualReceivedSize);

        download.empfange(300);
        expectedReceivedSize = 0.8;
        actualReceivedSize = download.gibFortschritt();
        Assertions.assertEquals(expectedReceivedSize, actualReceivedSize);
    }

    @Test
    public void testPaketVollstaendigGespeichert() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> download.paketVollstaendigGespeichert(-100));

        Assertions.assertThrows(IllegalArgumentException.class, () -> download.paketVollstaendigGespeichert(1100));
        download.paketVollstaendigGespeichert(500);
        double expectedReceivedSize = 0.5;
        double actualReceivedSize = download.gibFortschritt();
        Assertions.assertEquals(expectedReceivedSize, actualReceivedSize);

        download.paketVollstaendigGespeichert(300);
        expectedReceivedSize = 0.8;
        actualReceivedSize = download.gibFortschritt();
        Assertions.assertEquals(expectedReceivedSize, actualReceivedSize);
    }
}
