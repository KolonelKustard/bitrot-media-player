package com.totalchange.bitRotMedia;

import java.io.*;

/**
 * Title:        Bit Rot Media Player
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Kolonel Kustard
 * @version 1.0
 */

public class VideoRotter implements MovieListener {
    private static final long HEADER_SIZE = 5000;
    private static final long INCREMENTOR = 4000;
    private static final long DEVIANCE = 1000;

    private File file = null;
    private long fileLength;

    private int duration = -1;
    private int startPoint = -1;
    private boolean wasPlaying = false;

    public void setMovieFile(File movieFile) {
        try {
            file = movieFile;
            fileLength = file.length();
        }
        catch(Exception e) {e.printStackTrace();}
    }

    public void setMovieDuration(int duration) {
        this.duration = duration;
    }

    public void startedPlaying(int timecode) {
        startPoint = timecode;
        wasPlaying = true;
    }

    public void stoppedPlaying(int timecode) {
        // Make sure we're in business!
        if ((file != null) && (duration > -1)) {

            // Only want to monkey with the file if it WAS playing, but isn't
            // any more.
            if (wasPlaying) {

                // Now just degrade from starting point to stopping point...
                degrade(startPoint, timecode, duration);
            }
        }

        wasPlaying = false;
    }

    /**
     * This is the bulk of the degradation code.  It takes the percentage of the
     * file as designated from start time, stop time and length of movie, and
     * it degrades it.  Does this by 'dropping' bits.  If video plays enough,
     * then entire bits will have dropped to 0.
     */
    private void degrade(int startTime, int stopTime, int lengthTime) {
        long fileStartPoint, fileStopPoint;
        float percent;

        percent = (float)startTime / (float)lengthTime;
        fileStartPoint = (long)(percent * fileLength);

        percent = (float)stopTime / (float)lengthTime;
        fileStopPoint = (long)(percent * fileLength);

        // Pause the thread for a sec to let movie stop playing properly...
        try {
            Thread.currentThread().sleep(3000);
        }
        catch(Exception e) {}

        // Now we know our start and stop times in the file, we can drop bits
        // out of that portion...
        try {
            RandomAccessFile rFile = new RandomAccessFile(file, "rw");
            boolean stillGoing = true;
            long nextPos = fileStartPoint;
            int byteRead, byteWrite;

            while(stillGoing) {
                // Check position does not clash with header or end of file...
                if ((nextPos > HEADER_SIZE) && (nextPos < fileLength)) {

                    // Seek to the next position in the file and read the byte.
                    rFile.seek(nextPos);
                    byteRead = rFile.read();

                    // If the byte read is already 0, we ignore it...
                    if (byteRead > 0) {
                        // 'Drop' a bit...
                        byteWrite = byteRead - 1;

                        // Re-write byte...
                        rFile.seek(nextPos);
                        rFile.write(byteWrite);
                    }
                }
                // Increment to next position.  Uses a randomly changed value so
                // we don't rot the same bit's repeatedly.
                nextPos += (INCREMENTOR + (java.lang.Math.random() * DEVIANCE));

                // If incremented beyond stop point, quit the loop...
                if (nextPos > fileStopPoint) {
                    stillGoing = false;
                }
            }

            rFile.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(java.lang.Math.random() * DEVIANCE);
    }
}