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

public interface MovieListener {
    public void setMovieFile(File movieFile);
    public void setMovieDuration(int duration);
    public void startedPlaying(int timecode);
    public void stoppedPlaying(int timecode);
}