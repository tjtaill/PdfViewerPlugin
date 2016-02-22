package pdfviewer;

import org.gjt.sp.jedit.Buffer;
import org.gjt.sp.jedit.EBComponent;
import org.gjt.sp.jedit.EBMessage;
import org.gjt.sp.jedit.View;
import org.gjt.sp.jedit.gui.DefaultFocusComponent;


import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class DockableIceIcePdfViewer extends JPanel implements EBComponent, DefaultFocusComponent {

    private JPanel icePdfViewer;
    private View view;
    private int pages;


    public DockableIceIcePdfViewer(View view) {
        this.view = view;

        setLayout( new BorderLayout());
        add(icePdfViewer, BorderLayout.CENTER);
    }

    public void openPdf(Buffer buffer) {
        try {
            String[] parts = buffer.getPath().split("\\.", 2);
            InputStream inputStream = new FileInputStream( new File(parts[0] + "." + "pdf") );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void syncPdfLocation(org.gjt.sp.jedit.textarea.TextArea textArea) {
        int currentLine = textArea.getCaretLine();
        int lastLine = textArea.getLineCount();
        float percent = (float)currentLine / lastLine ;
        float targetPage = percent * pages;
        int goToPage = (int)Math.floor( targetPage );
    }

    public void syncTextLocation(org.gjt.sp.jedit.textarea.TextArea textArea) {
        int lines = textArea.getLineCount();
        int pages = 1;
        int currentPage = 1;

        float percent = (float) (currentPage + 1)/pages;
        float targetLine = percent * lines;
        int line = (int)Math.floor( targetLine );
        textArea.setCaretPosition(textArea.getLineStartOffset(line - 1));
    }

    @Override
    public void focusOnDefaultComponent() {
        icePdfViewer.requestFocus();
    }

    @Override
    public void handleMessage(EBMessage ebMessage) {

    }
}
