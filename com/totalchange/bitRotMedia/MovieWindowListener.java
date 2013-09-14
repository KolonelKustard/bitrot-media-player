package com.totalchange.bitRotMedia;

/**
 * Title:        Bit Rot Media Player
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Kolonel Kustard
 * @version 1.0
 */

public interface MovieWindowListener {
    public void closing(MovieWindow closingWindow);
    public void focusGained(MovieWindow focusedWindow);
}