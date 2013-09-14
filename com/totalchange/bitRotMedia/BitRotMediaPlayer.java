package com.totalchange.bitRotMedia;

import java.awt.*;
import java.util.*;

import quicktime.*;

/**
 * Title:        Bit Rot Media Player
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Kolonel Kustard
 * @version 1.0
 */

public class BitRotMediaPlayer implements MovieWindowListener {
    private MainWindow window;
    private Vector rottingPlayers = new Vector();
    private MovieWindow focusedMovieWindow;

    // These 2 variables decide on positioning new windows...
    private int lastX, lastY;

    public BitRotMediaPlayer() {
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        try {
            QTSession.open();
        }
        catch(QTException e) {e.printStackTrace();}

        window = new MainWindow(this);
        window.setSize(320, 20);
        window.setResizable(false);
        window.show();

        // Set lastX + lastY;
        lastX = (int)screenSize.getWidth() / 2;
        lastY = (int)screenSize.getHeight() / 2;
    }

    // Makes a new window placing it in the last known coordinates.  It's up
    // to the caller of the method as to whether the window is shown or not...
    public MovieWindow addNewPlayer() {
        MovieWindow newWindow = new MovieWindow(window);

        // Make a rotting component that can listen to it...
        newWindow.setMovieListener(new VideoRotter());

        // Add it to our Vector
        rottingPlayers.addElement(newWindow);

        // Add listener...
        newWindow.addListener(this);

        // Size and position it...
        newWindow.pack();

        if (focusedMovieWindow != null) {
            lastX = focusedMovieWindow.getX() + 10;
            lastY = focusedMovieWindow.getY() + 10;
        }

        newWindow.setLocation(lastX, lastY);

        // Set it to be of focus...
        focusedMovieWindow = newWindow;

        return newWindow;
    }

    public void openFile() {
        // If there are no open windows, we make a new one...
        if (rottingPlayers.size() < 1) {
            addNewPlayer();
        }

        // Now use the focused window to open movie...
        focusedMovieWindow.findMovie();

        // Size the window to the new movie...
        focusedMovieWindow.pack();

        // Make sure it's visible...
        focusedMovieWindow.setVisible(true);
    }

    public void focusGained(MovieWindow movieWindow) {
        focusedMovieWindow = movieWindow;
    }

    public void closing(MovieWindow movieWindow) {
        rottingPlayers.removeElement(movieWindow);

        // If it's the focused one, we'll need to make the last opened window
        // the focused window, and if this is the last window, we need to
        // nullify focusedMovieWindow.
        if (rottingPlayers.size() < 1) {
            focusedMovieWindow = null;
        }
        else if (movieWindow.equals(focusedMovieWindow)) {
            focusedMovieWindow = (MovieWindow)rottingPlayers.elementAt(rottingPlayers.size() - 1);
        }
    }

    public void shutdown() {
        QTSession.close();
        System.exit(0);
    }

    public void closeFocusedWindow() {
        // Shut down the focused window...
        focusedMovieWindow.shutdown();
    }
}