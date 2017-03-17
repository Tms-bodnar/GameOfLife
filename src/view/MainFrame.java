/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.CellImporter;
import controller.FieldManipulator;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Cell;

/**
 * A GameOfLife program Conway élet játékának megvalósítása. A program a játék
 * szabályai szerint működik, egy 90X180-as, azaz több, mint 16.000 cellát
 * magában foglaló mezőn szimulálja a sejtek életciklusát.
 *
 * @author Bodnár Tamás <tms.bodnar@gmail.com> | www.kalandlabor.hu
 */
public class MainFrame extends javax.swing.JFrame {

    private CellImporter ci;
    private StringBuffer pattern;
    private boolean[][] field;
    private boolean[][] previousField;
    private CellPlace cp;
    private List<CellPlace> cpList = new ArrayList<>();
    private String patternName = "";
    private GridLayout gr = new GridLayout(90, 180);
    private FieldManipulator fm = new FieldManipulator(field);
    private Timer t = new Timer(0, actListener());
    private int numberOfgenerations = 0;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * A fieldDraw metódust a .lif file megnyitása után egysze hívjuk meg. A
     * paraméterként adott boolean[][] tömb alapján kiszámolja, hogy a JPanelen
     * (gridLayout layouttal) melyik cellába helyezzen el élő sejteket. Ha az
     * előző generáció elhelyezkedését szeretnénk megjeleníteni, akkor is ez a
     * metódus fut le.
     *
     * @param field
     */
    public void fieldDraw(boolean[][] field) {
        //ha volt előző mező, és előző CellPlace lista, töröljük az elemeket
        patternPanel.removeAll();
        cpList.clear();
        //változó a sebesség méréséhez
        long start = System.currentTimeMillis();
        patternPanel.setVisible(true);
        //bejárjuk a tömböt
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                //elhelyezünk egy-egy CellPlace-t
                cp = new CellPlace(i, j);
                if (field[i][j]) {
                    //ha a cella élő sejtet tartalmaz, zöldre színezzük                      
                    cp.setBackground(Color.green);
                } else {
                    cp.setBackground(Color.black);
                }
                //hozzáadjuk a CellPlace listához
                cpList.add(cp);
                //majd a JPanelünkhöz is
                patternPanel.add(cp);
            }

        }
        patternPanel.setLayout(gr);
        patternPanel.revalidate();
        patternPanel.repaint();
        //elkezdjük számolni a generációkat
        numberOfgenerations += 1;
        gensNumberjTextField.setText(Integer.toString(numberOfgenerations));
        //kiszámítjuk és kiírjuk a mező létrehozásának idejét(ez egyenlőre siralmasan lassú,
        //(kb16.000 ms), idő hiányában az optimalizálása elmaradt :-(
        long end = System.currentTimeMillis();
        speedjTextField.setText(Long.toString(end - start) + " ms");
    }

    /**
     * A newFieldDraw metódus a betöltött fájl újrarajzolását végzi.
     * összehasonlítja az előző és a friss mezőt, és csak a megváltozott
     * állapotú cellákat módosítja, amivel jelentős a sebességnövekedés
     *
     * @param field
     * @param previousField
     */
    public void newFieldDraw(boolean[][] field, boolean[][] previousField) {
        //Az időmérés kezdete
        long start = System.currentTimeMillis();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                //ha a régi és az új mező adott cellája megváltozott
                if (field[i][j] != previousField[i][j]) {
                    //és a cellában most élő sejt található
                    if (field[i][j]) {
                        for (CellPlace c : cpList) {
                            //az adott CellPlace színét megváltoztatjuk zöldre
                            if (c.x == i && c.y == j) {
                                c.setBackground(Color.green);
                            }
                        }
                        //ha cellában nincs sejt
                    } else if (!field[i][j]) {
                        for (CellPlace c : cpList) {
                            //az adott cellPlace színét megváltoztatjuk feketére
                            if (c.x == i && c.y == j) {
                                c.setBackground(Color.black);
                            }
                        }
                    }
                }
            }
        }
        //újrarajzolás, generáció számának növelése, kiírása    
        patternPanel.revalidate();
        patternPanel.repaint();
        numberOfgenerations += 1;
        gensNumberjTextField.setText(Integer.toString(numberOfgenerations));
        //a szükséges idő számítása és megjelenítése
        long end = System.currentTimeMillis();
        speedjTextField.setText(Long.toString(end - start) + " ms");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nextGenjButton = new javax.swing.JButton();
        openLifjButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        autoplayjButton = new javax.swing.JButton();
        stopjButton = new javax.swing.JButton();
        gensNumberjTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        speedjTextField = new javax.swing.JTextField();
        patternPanel = new javax.swing.JPanel();
        prevGenjButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        exitjMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Conway's Game Of Life");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(1350, 750));
        setMinimumSize(new java.awt.Dimension(1350, 750));

        nextGenjButton.setText("Next gen.");
        nextGenjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextGenjButtonActionPerformed(evt);
            }
        });

        openLifjButton.setText("Open .lif");
        openLifjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openLifjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1228, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 432, Short.MAX_VALUE)
        );

        autoplayjButton.setText("Autoplay");
        autoplayjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoplayjButtonActionPerformed(evt);
            }
        });

        stopjButton.setText("Stop");
        stopjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopjButtonActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("<html>\nNumber of <br/>\ngenerations:\n<html>");
        jLabel1.setToolTipText("");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel2.setText("<html>Performance <br/> speed:");

        patternPanel.setMaximumSize(new java.awt.Dimension(1200, 600));
        patternPanel.setMinimumSize(new java.awt.Dimension(1200, 600));

        javax.swing.GroupLayout patternPanelLayout = new javax.swing.GroupLayout(patternPanel);
        patternPanel.setLayout(patternPanelLayout);
        patternPanelLayout.setHorizontalGroup(
            patternPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1246, Short.MAX_VALUE)
        );
        patternPanelLayout.setVerticalGroup(
            patternPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        prevGenjButton.setText("Prev. gen.");
        prevGenjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevGenjButtonActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        exitjMenuItem.setText("Exit");
        exitjMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitjMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(exitjMenuItem);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(openLifjButton, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                .addComponent(nextGenjButton, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addComponent(stopjButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(gensNumberjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(speedjTextField)
                            .addComponent(prevGenjButton)
                            .addComponent(autoplayjButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(patternPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {autoplayjButton, gensNumberjTextField, jLabel1, nextGenjButton, openLifjButton, prevGenjButton, speedjTextField, stopjButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(patternPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(openLifjButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nextGenjButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(prevGenjButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(autoplayjButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stopjButton)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gensNumberjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(speedjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(409, 409, 409))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * A menü kilépés eleme
     *
     * @param evt
     */
    private void exitjMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitjMenuItemActionPerformed
        setVisible(false);
        System.exit(0);    }//GEN-LAST:event_exitjMenuItemActionPerformed
    /**
     * .lif file megnyitása és megjelenítése a CellImporter és a
     * Fieldmanipulátor osztályok metódusainak segítségével
     *
     * @param evt
     */
    private void openLifjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openLifjButtonActionPerformed
        //file választó ablak nyitása
        JFileChooser fc = new JFileChooser();
        //.lif fájlok szűrése
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.LIF", new String[]{"*.LIF", "*.lif", "lif"});
        fc.setFileFilter(filter);
        int choice = fc.showOpenDialog(this);
        if (choice == JFileChooser.APPROVE_OPTION) {
            File lifFile = fc.getSelectedFile();
            //elhelyezzük a fájl nevét egy változóban
            patternName = lifFile.getName();
            pattern = new StringBuffer();
            try {
                FileReader fr = new FileReader(lifFile);
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                //a beolvasott sorokat egy StringBufferben helyezzük el
                while (line != null) {
                    if (line.startsWith("*") || line.startsWith(".") || line.startsWith("#P")) {
                        pattern.append(line);
                        //a sorokat $ karakterrel tördeljük
                        pattern.append("$");
                    }
                    line = br.readLine();
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(rootPane, ex, "File error!", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, ex, "File error!", JOptionPane.ERROR_MESSAGE);
            }
        }
        //mivel új fájlt nyitottunk, a generációk számát nullázzuk
        numberOfgenerations = 0;
        //elhelyezzük a fájl nevét a proramunk keretén
        setTitle("Conway's Game Of Life: " + patternName);
        //példányosítunk egy CellImporter objektumot, meghívjuk a cellList metódust,
        //a ci objektum cellList-jét megkapjuk
        ci = new CellImporter();
        List<Cell> cellList = ci.cellList(pattern);
        setField(ci.getCellField());
        //megrajzoljuk a mezőt
        fieldDraw(field);


    }//GEN-LAST:event_openLifjButtonActionPerformed
    /**
     * A Next gen. gomb a következő generációt jeleníti meg.
     *
     * @param evt
     */
    private void nextGenjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextGenjButtonActionPerformed
        //belehelyezzük az adattagokba az előző mezőt és a friss mezőt
        previousField = fm.getPreviousField();
        field = fm.newField(field);
        //majd megrajzoljuk
        newFieldDraw(field, previousField);
    }//GEN-LAST:event_nextGenjButtonActionPerformed
    /**
     * egy ActionListener a folyamatos lejátszás Timer objektumának
     * konstruktorához
     *
     * @return
     */
    public ActionListener actListener() {
        //létrehozzuk az ActionListener objektumot
        ActionListener listener = new ActionListener() {
            @Override
            //felülírjuk az absztrakt metódusát az újrarajzoláshoz
            public void actionPerformed(ActionEvent e) {
                previousField = fm.getPreviousField();
                field = fm.newField(field);
                newFieldDraw(field, previousField);
            }
        };
        return listener;
    }

    /**
     * A Stop gombbal megállítjuk a Timer-t és megszakítjuk a folyamatos
     * lejátszást
     *
     * @param evt
     */
    private void stopjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopjButtonActionPerformed
        t.removeActionListener(actListener());
        t.stop();
    }//GEN-LAST:event_stopjButtonActionPerformed
    /**
     * az autoplay gombbal elindítjuk a Timer objektumot, rajta kersztül pedig
     * az újraajzolást. a késleltetés jelenleg 0
     *
     * @param evt
     */
    private void autoplayjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autoplayjButtonActionPerformed

        t.start();

    }//GEN-LAST:event_autoplayjButtonActionPerformed
    /**
     * a Prev. gen. gombbal megnézhetjük a mező előző népességét. csak egy
     * lépést mehetünk vissza, és a lassú fieldDraw metódust használja
     *
     * @param evt
     */
    private void prevGenjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevGenjButtonActionPerformed
        field = fm.getPreviousField();
        fieldDraw(field);
    }//GEN-LAST:event_prevGenjButtonActionPerformed
    /**
     * a CellPlace osztály a JPanel leszármazottja a mező celláinak és élő
     * sejtjeinek megjelenítésée használjuk,
     *
     */
    public class CellPlace extends JPanel {

        int x;
        int y;

        public CellPlace() {
        }

        public CellPlace(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    /**
     * boolean[][] field getter
     *
     * @return boolean[][] field
     */
    public boolean[][] getField() {
        return field;
    }

    public void setField(boolean[][] field) {
        this.field = field;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);

            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton autoplayjButton;
    private javax.swing.JMenuItem exitjMenuItem;
    private javax.swing.JTextField gensNumberjTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton nextGenjButton;
    private javax.swing.JButton openLifjButton;
    private javax.swing.JPanel patternPanel;
    private javax.swing.JButton prevGenjButton;
    private javax.swing.JTextField speedjTextField;
    private javax.swing.JButton stopjButton;
    // End of variables declaration//GEN-END:variables
}
