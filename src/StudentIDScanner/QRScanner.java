
package StudentIDScanner;

import Library.Dashboard_Inventory;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class QRScanner extends JFrame implements Runnable,ThreadFactory, ActionListener{
    public String formattedText;
    private Dashboard_Inventory dashboard;
    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    
     JPanel panelcam = new JPanel();
     JLabel resultlabel = new JLabel("STUDENT QR ");
     JButton savebtn = new JButton("Save");
     JButton cancelbtn = new JButton("Cancel");
     public JTextArea resultField = new JTextArea();
    
    public QRScanner(Dashboard_Inventory dashboard){
        this.dashboard = dashboard;
        initWebcam();
        
        this.setLayout(null);
        this.setTitle("ID Scanner");
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);
        
        panelcam.setBounds(50, 50, 500,300);
        panelcam.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(panelcam);
        
        resultlabel.setBounds(50, 380, 100, 25);
        add(resultlabel);
        
        resultField.setBounds(50, 420, 500, 50);
        add(resultField);
        
        savebtn.setBounds(340, 500, 100, 30);
        add(savebtn);
        savebtn.addActionListener(this);
        
        cancelbtn.setBounds(450, 500, 100, 30);
        add(cancelbtn);
        cancelbtn.addActionListener(this);
        
    }
    
    private void initWebcam(){
       Dimension size = WebcamResolution.VGA.getSize();
       webcam = Webcam.getWebcams().get(0);
       webcam.setViewSize(size);
       
       panel = new WebcamPanel(webcam);
       panel.setPreferredSize(size);
       panel.setFPSDisplayed(true);
       
       panelcam.add(panel);
       
       executor.execute(this);
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100); // Add a small delay
            } catch (InterruptedException ex) {
                Logger.getLogger(QRScanner.class.getName()).log(Level.WARNING, "Thread interrupted", ex);
            }

            BufferedImage image = null;

            // Check if the webcam is open and active
            if (webcam != null && webcam.isOpen()) {
                image = webcam.getImage();
                if (image == null) {
                    Logger.getLogger(QRScanner.class.getName()).log(Level.WARNING, "Webcam returned null image");
                    continue; // Skip processing if no image is captured
                }
            } else {
                Logger.getLogger(QRScanner.class.getName()).log(Level.SEVERE, "Webcam is not open or available");
                break; // Exit the loop if the webcam is not open
            }

            // Process the image for QR code
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            try {
                Result result = new MultiFormatReader().decode(bitmap);
                if (result != null) {
                    resultField.setText(result.getText());
                }
            } catch (NotFoundException ex) {
                Logger.getLogger(QRScanner.class.getName()).log(Level.FINE, "No QR code found in the current frame");
            }
        } while (true);
    }


    @Override
    public Thread newThread(Runnable r) {
       
        Thread t = new Thread(r,"My Thread");
        t.setDaemon(true);
        return t;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(savebtn)){
            String QRValue = resultField.getText();
            
            if(isValidQRValue(QRValue)){
                if (!QRValue.isEmpty()) {
                    dashboard.scannedValue.setText(QRValue);
                    formattedText = QRValue.replace("Student No.:", "Student No.:\n")
                            .replace("Full Name:", "\nFull Name:\n")
                            .replace("Program:", "\nProgram:\n");
                    JOptionPane.showMessageDialog(this, "Value sent to Student Details Form!", "Info", JOptionPane.INFORMATION_MESSAGE);
                    webcam.close();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "No QR code scanned!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else{
                JOptionPane.showMessageDialog(this, "Invalid QR code scanned!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(e.getSource().equals(cancelbtn)){
            webcam.close();
            dispose();
        }
    }
    
    private boolean isValidQRValue(String qrValue){
        if(qrValue == null || qrValue.isEmpty()){
            return false;
        }
        
        return qrValue.contains("Student No.:")
                && qrValue.contains("Full Name:")
                && qrValue.contains("Program:");
    }
  }

