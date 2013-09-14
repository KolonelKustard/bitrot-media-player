package com.totalchange.bitRotMedia;

import java.awt.*;
import java.util.*;

import quicktime.*;
import quicktime.io.*;
import quicktime.qd.*;
import quicktime.std.*;
import quicktime.std.movies.*;
import quicktime.std.movies.media.*;
import quicktime.app.display.*;
import quicktime.app.players.*;
import java.awt.event.*;

/**
 * Title:        Bit Rot Media Player
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Kolonel Kustard
 * @version 1.0
 */

public class MovieWindow extends Frame {
    private Vector windowListeners = new Vector();
    private MovieListener movieListener;

    private QTCanvas canvas;
    private Movie movie;
    private String movieURL = null;

    public MovieWindow() {
        this(new Frame());
    }

    public MovieWindow(Frame owner) {
        //super(owner);

        try {
            jbInit();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        canvas = new QTCanvas();
        canvas.setSize(120, 80);

        add(canvas);
    }

    public MovieWindow(String movieURL) {
        this(new Frame());
        this.movieURL = movieURL;

        loadMovie();
    }

    public MovieWindow(String movieURL, Frame owner) {
        this(owner);
        this.movieURL = movieURL;

        loadMovie();
    }

    public void findMovie() {
        try {
            QTFile file = QTFile.standardGetFilePreview(QTFile.kStandardQTFileTypes);
            movieURL = "file://" + file.getAbsolutePath();

            // Tell the listener about the new movie...
            if (movieListener != null) {
                movieListener.setMovieFile(file);
            }

            // Set the title of the frame to match that of the movie file...
            this.setTitle(file.getName());

            loadMovie();
        }
        catch(QTException e) {
            e.printStackTrace();
        }
    }

    private void loadMovie() {
        Movie thisMovie;
        DataRef thisRef;

        QTDrawable thisPlayer;
        MovieController thisController;

        // If we actually have a URL, we'll put it on the canvas...
        if (movieURL != null) {
            try {
                thisRef = new DataRef(movieURL);
                thisMovie = Movie.fromDataRef(thisRef, StdQTConstants.newMovieActive);
                movie = thisMovie;

                thisController = new MovieController(thisMovie);
                thisController.setKeysEnabled(true);
                thisPlayer = new QTPlayer(thisController);

                // Tell the listener about the new movie...
                if (movieListener != null) {
                    movieListener.setMovieDuration(movie.getDuration());
                }

                thisController.setActionFilter(new MovieActionListener());

                canvas.setClient(thisPlayer, true);
            }
            catch(QTException e) {e.printStackTrace();}
        }
    }

    public int getCurrentTime() {
        return 0; //movie.get
    }

    public void addListener(MovieWindowListener listener) {
        windowListeners.addElement(listener);
    }

    public void removeListener(MovieWindowListener listener) {
        windowListeners.removeElement(listener);
    }

    public void setMovieListener(MovieListener listener) {
        movieListener = listener;
    }

    public void shutdown() {
        // Tell all windowListeners this is closing...
        for(int num = 0; num < windowListeners.size(); num++) {
            ((MovieWindowListener)(windowListeners.elementAt(num))).closing(this);
        }

        // Nullify all that is useless!
        canvas.removeClient();

        windowListeners = null;
        canvas = null;

        // Now destroy this window...
        setVisible(false);

        // And seeing as when this method ends, there'll be no pointer to this
        // object anymore, we'll start a garbage collection to come deal with
        // it.
        System.gc();
    }

    public static void main(String[] args) throws Exception {
        QTSession.open();

        MovieWindow movieWindow = new MovieWindow();

        movieWindow.findMovie();

        movieWindow.pack();
        movieWindow.setVisible(true);

        //QTSession.close();
    }

    private void jbInit() throws Exception {
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                theFocusGained(e);
            }
        });
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                theWindowClosing(e);
            }
        });
    }

    void theWindowClosing(WindowEvent e) {
        shutdown();
    }

    void theFocusGained(MouseEvent e) {
        for (int num = 0; num < windowListeners.size(); num++) {
            ((MovieWindowListener)(windowListeners.elementAt(num))).focusGained(this);
        }
    }

    /**
     * An inner class that provides callback services on the movie.
     */
    private class MovieActionListener extends ActionFilter {
        private int counter = 0;

        public boolean execute(MovieController mc, int num, float floater) {
            if ((num == mc.mcActionPlay) && (movieListener != null)) {
                try {
                    if (floater != 0) {
                        movieListener.startedPlaying(movie.getTime());
                    }
                    else {
                        movieListener.stoppedPlaying(movie.getTime());
                    }
                }
                catch(QTException e) {e.printStackTrace();}
            }

            return false;
        }
    }
}