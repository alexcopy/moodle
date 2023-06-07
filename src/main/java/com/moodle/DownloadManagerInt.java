package com.moodle;

public interface DownloadManagerInt {

    /**
     * Gibt die Größe der herunterzuladenden Datei in Bytes zurück.
     *
     * Возвращает размер загружаемого файла в байтах.
     * * @вернуть размер файла в байтах
     * @return die Größe der Datei in Bytes
     */
    long gibGroesse();

    /**
     * Gibt den aktuellen Downloadfortschritt als Zahl zwischen 0 und 1 zurück
     * Возвращает текущий прогресс загрузки в виде числа от 0 до 1.
     * (0 = 0%, 1 = 100%).
     *
     * @return der aktuelle Fortschritt
     */
    double gibFortschritt();

    /**
     * Gibt zurück, ob der Download abgeschlossen ist.
     ** Возвращает, завершена ли загрузка.
     *
     * @return, если загрузка завершена
     * @return ob der Download abgeschlossen ist
     */
    boolean istFertig();

    /**
     * Gibt die Anzahl der Bytes zurück, die noch heruntergeladen
     * Возвращает количество оставшихся загруженных байтов
     *  Нужно setup.
     * werden müssen.
     *
     * @return die Anzahl der verbleibenden Bytes
     * вернуть количество оставшихся байтов
     */
    long gibVerbleibend();

    /**
     * * Пометить новый пакет данных указанного размера как полученный.
     *       * Этот метод не следует вызывать до тех пор, пока не появится соответствующий
     *       * Пакет полностью сохранен.
     *       *
     *       * @param packetsize количество вновь полученных байтов
     *       * @requirepackageSize >= 0
     *       * @require packageSize <= getRemaining()
     *
     * Markiere ein neues Datenpaket mit der angegebenen Größe als empfangen.
     * Diese Methode sollte erst aufgerufen werden, wenn das entsprechende
     * Paket vollständig gespeichert wurde.
     *
     * @param paketGroesse die Anzahl der neu empfangenen Bytes
     * @require paketGroesse >= 0
     * @require paketGroesse <= gibVerbleibend()
     */
    void empfange(long paketGroesse);
}

