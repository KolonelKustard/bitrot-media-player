package com.totalchange.bitRotMedia;

import java.awt.*;
import java.awt.event.*;

/**
 * Title:        Bit Rot Media Player
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Kolonel Kustard
 * @version 1.0
 */

public class MainWindow extends Frame {
    private BitRotMediaPlayer parent;
    MenuBar menuBarMain = new MenuBar();
    Menu menuFile = new Menu();
    Menu menuEdit = new Menu();
    Menu menuMovie = new Menu();
    Menu menuFavorites = new Menu();
    Menu menuWindow = new Menu();
    Menu menuHelp = new Menu();
    MenuItem menuItemNewPlayer = new MenuItem();
    MenuItem menuItemOpenMovie = new MenuItem();
    MenuItem menuItemOpenImageSequence = new MenuItem();
    MenuItem menuItemOpenURL = new MenuItem();
    MenuItem menuItemClose = new MenuItem();
    MenuItem menuItemSave = new MenuItem();
    MenuItem menuItemSaveAs = new MenuItem();
    MenuItem menuItemImport = new MenuItem();
    MenuItem menuItemExport = new MenuItem();
    MenuItem menuItemPresentMovie = new MenuItem();
    MenuItem menuItemPageSetup = new MenuItem();
    MenuItem menuItemPrint = new MenuItem();
    MenuItem menuItemExit = new MenuItem();
    MenuItem menuItemUndo = new MenuItem();
    MenuItem menuItemCut = new MenuItem();
    MenuItem menuItemCopy = new MenuItem();
    MenuItem menuItemPaste = new MenuItem();
    MenuItem menuItemClear = new MenuItem();
    MenuItem menuItemSelectAll = new MenuItem();
    MenuItem menuItemSelectNone = new MenuItem();
    MenuItem menuItemExtractTracks = new MenuItem();
    MenuItem menuItemDeleteTracks = new MenuItem();
    MenuItem menuItemEnableTracks = new MenuItem();
    MenuItem menuItemFind = new MenuItem();
    MenuItem menuItemFindAgain = new MenuItem();
    Menu menuPreferences = new Menu();
    MenuItem menuItemGeneral = new MenuItem();
    MenuItem menuItemConnectionSpeed = new MenuItem();
    MenuItem menuItemStreamingProxy = new MenuItem();
    MenuItem menuItemRegistration = new MenuItem();
    BorderLayout borderLayout1 = new BorderLayout();

    public MainWindow(BitRotMediaPlayer parent) {
        this();
        this.parent = parent;
    }

    public MainWindow() {
        try {
            jbInit();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    private void jbInit() throws Exception {
        this.setBackground(SystemColor.menu);
        this.setTitle("BitRot Media Player");
        this.setMenuBar(menuBarMain);
        this.setResizable(false);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        this.setLayout(borderLayout1);
        menuFile.setLabel("File");
        menuEdit.setLabel("Edit");
        menuMovie.setLabel("Movie");
        menuFavorites.setLabel("Favorites");
        menuWindow.setLabel("Window");
        menuHelp.setLabel("Help");
        menuItemNewPlayer.setLabel("New Player");
        menuItemNewPlayer.setShortcut(new MenuShortcut(78));
        menuItemNewPlayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newPlayer(e);
            }
        });
        menuItemOpenMovie.setLabel("Open Movie...");
        menuItemOpenMovie.setShortcut(new MenuShortcut(79));
        menuItemOpenMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
        menuItemOpenImageSequence.setLabel("Open Image Sequence...");
        menuItemOpenImageSequence.setEnabled(false);
        menuItemOpenURL.setLabel("Open URL...");
        menuItemOpenURL.setEnabled(false);
        menuItemOpenURL.setShortcut(new MenuShortcut(85));
        menuItemClose.setLabel("Close");
        menuItemClose.setShortcut(new MenuShortcut(85));
        menuItemClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                closeFocusedWindow(e);
            }
        });
        menuItemSave.setLabel("Save");
        menuItemSave.setEnabled(false);
        menuItemSave.setShortcut(new MenuShortcut(83));
        menuItemSaveAs.setLabel("Save As...");
        menuItemSaveAs.setEnabled(false);
        menuItemImport.setLabel("Import...");
        menuItemImport.setEnabled(false);
        menuItemExport.setLabel("Export...");
        menuItemExport.setEnabled(false);
        menuItemExport.setShortcut(new MenuShortcut(69));
        menuItemPresentMovie.setLabel("Present Movie...");
        menuItemPresentMovie.setEnabled(false);
        menuItemPresentMovie.setShortcut(new MenuShortcut(77));
        menuItemPageSetup.setLabel("Page Setup...");
        menuItemPageSetup.setEnabled(false);
        menuItemPrint.setLabel("Print...");
        menuItemPrint.setEnabled(false);
        menuItemPrint.setShortcut(new MenuShortcut(80));
        menuItemExit.setLabel("Exit");
        menuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                quit(e);
            }
        });
        menuItemUndo.setLabel("Undo");
        menuItemUndo.setEnabled(false);
        menuItemUndo.setShortcut(new MenuShortcut(90));
        menuItemCut.setLabel("Cut");
        menuItemCut.setEnabled(false);
        menuItemCut.setShortcut(new MenuShortcut(88));
        menuItemCopy.setLabel("Copy");
        menuItemCopy.setEnabled(false);
        menuItemCopy.setShortcut(new MenuShortcut(67));
        menuItemPaste.setLabel("Paste");
        menuItemPaste.setEnabled(false);
        menuItemPaste.setShortcut(new MenuShortcut(86));
        menuItemClear.setLabel("Clear");
        menuItemClear.setEnabled(false);
        menuItemClear.setShortcut(new MenuShortcut(KeyEvent.VK_DELETE));
        menuItemSelectAll.setLabel("Select All");
        menuItemSelectAll.setEnabled(false);
        menuItemSelectAll.setShortcut(new MenuShortcut(65));
        menuItemSelectNone.setLabel("Select None");
        menuItemSelectNone.setEnabled(false);
        menuItemSelectNone.setShortcut(new MenuShortcut(66));
        menuItemExtractTracks.setLabel("Extract Tracks...");
        menuItemExtractTracks.setEnabled(false);
        menuItemDeleteTracks.setLabel("Delete Tracks...");
        menuItemDeleteTracks.setEnabled(false);
        menuItemEnableTracks.setLabel("Enable Tracks...");
        menuItemEnableTracks.setEnabled(false);
        menuItemFind.setLabel("Find...");
        menuItemFind.setEnabled(false);
        menuItemFind.setShortcut(new MenuShortcut(70));
        menuItemFindAgain.setLabel("Find Again");
        menuItemFindAgain.setEnabled(false);
        menuItemFindAgain.setShortcut(new MenuShortcut(71));
        menuPreferences.setLabel("Preferences");
        menuItemGeneral.setLabel("General...");
        menuItemGeneral.setEnabled(false);
        menuItemConnectionSpeed.setLabel("Connection Speed");
        menuItemConnectionSpeed.setEnabled(false);
        menuItemStreamingProxy.setLabel("Streaming Proxy");
        menuItemStreamingProxy.setEnabled(false);
        menuItemRegistration.setLabel("Registration");
        menuItemRegistration.setEnabled(false);
        menuBarMain.setFont(new java.awt.Font("SansSerif", 0, 12));
        menuBarMain.setHelpMenu(menuHelp);
        menuBarMain.add(menuFile);
        menuBarMain.add(menuEdit);
        menuBarMain.add(menuMovie);
        menuBarMain.add(menuFavorites);
        menuBarMain.add(menuWindow);
        menuBarMain.add(menuHelp);
        menuFile.add(menuItemNewPlayer);
        menuFile.add(menuItemOpenMovie);
        menuFile.add(menuItemOpenImageSequence);
        menuFile.add(menuItemOpenURL);
        menuFile.add(menuItemClose);
        menuFile.add(menuItemSave);
        menuFile.add(menuItemSaveAs);
        menuFile.addSeparator();
        menuFile.add(menuItemImport);
        menuFile.add(menuItemExport);
        menuFile.addSeparator();
        menuFile.add(menuItemPresentMovie);
        menuFile.addSeparator();
        menuFile.add(menuItemPageSetup);
        menuFile.add(menuItemPrint);
        menuFile.addSeparator();
        menuFile.add(menuItemExit);
        menuEdit.add(menuItemUndo);
        menuEdit.addSeparator();
        menuEdit.add(menuItemCut);
        menuEdit.add(menuItemCopy);
        menuEdit.add(menuItemPaste);
        menuEdit.add(menuItemClear);
        menuEdit.addSeparator();
        menuEdit.add(menuItemSelectAll);
        menuEdit.add(menuItemSelectNone);
        menuEdit.addSeparator();
        menuEdit.add(menuItemExtractTracks);
        menuEdit.add(menuItemDeleteTracks);
        menuEdit.add(menuItemEnableTracks);
        menuEdit.addSeparator();
        menuEdit.add(menuItemFind);
        menuEdit.add(menuItemFindAgain);
        menuEdit.addSeparator();
        menuEdit.add(menuPreferences);
        menuPreferences.add(menuItemGeneral);
        menuPreferences.add(menuItemConnectionSpeed);
        menuPreferences.add(menuItemStreamingProxy);
        menuPreferences.add(menuItemRegistration);
    }

    private void openFile() {
        parent.openFile();
    }

    void this_windowClosing(WindowEvent e) {
        parent.shutdown();
    }

    void newPlayer(ActionEvent e) {
        parent.addNewPlayer().setVisible(true);
    }

    void closeFocusedWindow(ActionEvent e) {
        parent.closeFocusedWindow();
    }

    void quit(ActionEvent e) {
        parent.shutdown();
    }

}