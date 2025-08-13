/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package kasir;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import kasir.FormLogin;

/**
 *
 * @author user
 */
public class kasir extends javax.swing.JFrame {

    /**
     * Creates new form kasir
     */
    public kasir() {
        initComponents();
        
        txtNamaPegawai.setText(session.getNamaPegawai());
        String no = generateNoTransaksi();
        txtNoTransaksi.setText(no); // atau jTextField kamu

        
        jDiscount.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
    public void changedUpdate(javax.swing.event.DocumentEvent e) {
        hitungTotal();
    }
    public void removeUpdate(javax.swing.event.DocumentEvent e) {
        hitungTotal();
    }
    public void insertUpdate(javax.swing.event.DocumentEvent e) {
        hitungTotal();
    }
});
        
        jPajak.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
    public void changedUpdate(javax.swing.event.DocumentEvent e) {
        hitungTotal();
    }
    public void removeUpdate(javax.swing.event.DocumentEvent e) {
        hitungTotal();
    }
    public void insertUpdate(javax.swing.event.DocumentEvent e) {
        hitungTotal();
    }
});
        
        jService.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
    public void changedUpdate(javax.swing.event.DocumentEvent e) {
        hitungTotal();
    }
    public void removeUpdate(javax.swing.event.DocumentEvent e) {
        hitungTotal();
    }
    public void insertUpdate(javax.swing.event.DocumentEvent e) {
        hitungTotal();
    }
});

    }
    
    public String generateNoTransaksi() {
    String prefix = "TRX";
    int urutan = 1;
    String noTransaksi = "";

    try (Connection conn = dbkoneksi.getConnection()) {
        String sql = "SELECT idtransaksi FROM transaksi ORDER BY idtransaksi DESC LIMIT 1";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String lastID = rs.getString("idtransaksi").substring(3); // buang 'TRX'
            urutan = Integer.parseInt(lastID) + 1;
        }

        noTransaksi = String.format("%s%05d", prefix, urutan); // TRX00001, TRX00002, ...
    } catch (Exception e) {
        e.printStackTrace();
    }

    return noTransaksi;
}

    
    public void tambahMenuKeTabel(int id, String nama, int harga, int jumlah, int total) {
    DefaultTableModel model = (DefaultTableModel) tblMenu.getModel();
    model.addRow(new Object[]{id, nama, harga, jumlah, total});
    hitungTotal();
    }

    
    public void hitungTotal() {
    DefaultTableModel model = (DefaultTableModel) tblMenu.getModel();
    int subtotal = 0;

    // Hitung subtotal dari kolom total
    for (int i = 0; i < model.getRowCount(); i++) {
        subtotal += (int) model.getValueAt(i, 4); // kolom "Total"
    }

    jSubtotal.setText(String.valueOf(subtotal));

    // Ambil diskon persen dari input
    double disc = getPersen(jDiscount);
    double potongan = subtotal * disc;
    jAdiscount.setText(String.valueOf((int) potongan));

    // Hitung grand total
    double pajak = getPersen(jPajak); 
    double nilaiPajak = subtotal * pajak;
    jApajak.setText(String.valueOf((int) nilaiPajak));
// jika ada
    double service = getPersen(jService);
    double nilaiService = subtotal * service;
    jAservice.setText(String.valueOf((int) nilaiService));

    double grand = subtotal - potongan + nilaiPajak + nilaiService;
    jGrandTotal.setText(String.valueOf((int) grand));
    jTotal.setText(String.valueOf((int) grand));
}


private double getPersen(JTextField txt) {
    try {
        return Double.parseDouble(txt.getText()) / 100.0;
    } catch (Exception e) {
        return 0;
    }
}

    public void hitungKembalian() {
    try {
        int grandTotal = Integer.parseInt(jGrandTotal.getText());
        int tunai = Integer.parseInt(jTunai.getText());

        int kembali = tunai - grandTotal;
        jKembalian.setText(String.valueOf(kembali));
    } catch (Exception e) {
        jKembalian.setText("0");
    }
    
    
}
    public void resetForm() {
    txtNoTransaksi.setText(generateNoTransaksi());
    jSubtotal.setText("");
    jDiscount.setText("");
    jAdiscount.setText("");
    jPajak.setText("");
    jService.setText("");
    jApajak.setText("");
    jAservice.setText("");
    jTotal.setText("");
    jGrandTotal.setText("");
    jTunai.setText("");
    jKembalian.setText("");
    

    DefaultTableModel model = (DefaultTableModel) tblMenu.getModel();
    model.setRowCount(0); // hapus semua baris
}

    
    



    
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tNoTransaksi = new javax.swing.JLabel();
        txtNoTransaksi = new javax.swing.JTextField();
        tTanggal = new javax.swing.JLabel();
        dTglTransaksi = new com.toedter.calendar.JDateChooser();
        tNama = new javax.swing.JLabel();
        txtNamaPelanggan = new javax.swing.JTextField();
        tPelayan = new javax.swing.JLabel();
        txtNamaPegawai = new javax.swing.JTextField();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMenu = new javax.swing.JTable();
        mButtonChoose = new javax.swing.JButton();
        mButtonInven = new javax.swing.JButton();
        mButtonEdit = new javax.swing.JButton();
        mButtonDel = new javax.swing.JButton();
        tKeterangan = new javax.swing.JLabel();
        tSubtotal = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jKeterangan = new javax.swing.JTextArea();
        jSubtotal = new javax.swing.JTextField();
        tDiscount = new javax.swing.JLabel();
        jDiscount = new javax.swing.JTextField();
        jAdiscount = new javax.swing.JTextField();
        tPajak = new javax.swing.JLabel();
        tService = new javax.swing.JLabel();
        jPajak = new javax.swing.JTextField();
        jService = new javax.swing.JTextField();
        jApajak = new javax.swing.JTextField();
        jAservice = new javax.swing.JTextField();
        percentLogo = new javax.swing.JLabel();
        percentLogo2 = new javax.swing.JLabel();
        percentLogo3 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        tGrandTotal = new javax.swing.JLabel();
        jGrandTotal = new javax.swing.JTextField();
        tTunai = new javax.swing.JLabel();
        jTunai = new javax.swing.JTextField();
        tKredit = new javax.swing.JLabel();
        jKredit = new javax.swing.JTextField();
        tKembalian = new javax.swing.JLabel();
        jKembalian = new javax.swing.JTextField();
        btSave = new javax.swing.JButton();
        btCancel = new javax.swing.JButton();
        tTotal = new javax.swing.JLabel();
        jTotal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1280, 768));

        tNoTransaksi.setText("No. Transaksi :");

        txtNoTransaksi.setEditable(false);
        txtNoTransaksi.setColumns(10);
        txtNoTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoTransaksiActionPerformed(evt);
            }
        });
        txtNoTransaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNoTransaksiKeyTyped(evt);
            }
        });

        tTanggal.setText("Tanggal :");

        dTglTransaksi.setMinimumSize(new java.awt.Dimension(100, 26));
        dTglTransaksi.setPreferredSize(new java.awt.Dimension(150, 30));

        tNama.setText("Nama Pelanggan :");

        txtNamaPelanggan.setColumns(10);
        txtNamaPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaPelangganActionPerformed(evt);
            }
        });
        txtNamaPelanggan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNamaPelangganKeyTyped(evt);
            }
        });

        tPelayan.setText("Nama Pelayan :");

        txtNamaPegawai.setColumns(10);
        txtNamaPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaPegawaiActionPerformed(evt);
            }
        });

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblMenu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Nama", "Harga", "Jumlah", "Total"
            }
        ));
        jScrollPane1.setViewportView(tblMenu);
        if (tblMenu.getColumnModel().getColumnCount() > 0) {
            tblMenu.getColumnModel().getColumn(0).setResizable(false);
            tblMenu.getColumnModel().getColumn(1).setResizable(false);
            tblMenu.getColumnModel().getColumn(2).setResizable(false);
            tblMenu.getColumnModel().getColumn(3).setResizable(false);
            tblMenu.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 630, 280));

        mButtonChoose.setText("[F12] Pilih menu");
        mButtonChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mButtonChooseActionPerformed(evt);
            }
        });
        mButtonChoose.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mButtonChooseKeyPressed(evt);
            }
        });
        jPanel1.add(mButtonChoose, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        mButtonInven.setText("[F11] Kelola Inventory");
        mButtonInven.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mButtonInvenActionPerformed(evt);
            }
        });
        jPanel1.add(mButtonInven, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        mButtonEdit.setText("[F2] Edit");
        mButtonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mButtonEditActionPerformed(evt);
            }
        });
        jPanel1.add(mButtonEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, -1));

        mButtonDel.setText("[Del] Delete");
        mButtonDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mButtonDelActionPerformed(evt);
            }
        });
        jPanel1.add(mButtonDel, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, -1, -1));

        tKeterangan.setText("Keterangan :");
        jPanel1.add(tKeterangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 260, -1, -1));

        tSubtotal.setText("Subtotal :");
        jPanel1.add(tSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 20, -1, -1));

        jKeterangan.setColumns(20);
        jKeterangan.setRows(5);
        jScrollPane3.setViewportView(jKeterangan);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 280, 199, -1));

        jSubtotal.setEditable(false);
        jSubtotal.setColumns(5);
        jSubtotal.setMinimumSize(new java.awt.Dimension(70, 26));
        jPanel1.add(jSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 20, 65, -1));

        tDiscount.setText("Discount :");
        tDiscount.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
                tDiscountAncestorMoved(evt);
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
            }
        });
        jPanel1.add(tDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 80, -1, -1));

        jDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDiscountActionPerformed(evt);
            }
        });
        jPanel1.add(jDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 80, 65, -1));

        jAdiscount.setEditable(false);
        jAdiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAdiscountActionPerformed(evt);
            }
        });
        jPanel1.add(jAdiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 80, 65, -1));

        tPajak.setText("Pajak :");
        jPanel1.add(tPajak, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 140, -1, -1));

        tService.setText("Service  :");
        jPanel1.add(tService, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 200, -1, -1));

        jPajak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPajakActionPerformed(evt);
            }
        });
        jPanel1.add(jPajak, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 140, 65, -1));

        jService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jServiceActionPerformed(evt);
            }
        });
        jPanel1.add(jService, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 200, 65, -1));

        jApajak.setEditable(false);
        jApajak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jApajakActionPerformed(evt);
            }
        });
        jPanel1.add(jApajak, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 140, 65, -1));

        jAservice.setEditable(false);
        jAservice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAserviceActionPerformed(evt);
            }
        });
        jPanel1.add(jAservice, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 200, 65, -1));

        percentLogo.setText("%");
        jPanel1.add(percentLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 80, -1, -1));

        percentLogo2.setText("%");
        jPanel1.add(percentLogo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 140, -1, -1));

        percentLogo3.setText("%");
        jPanel1.add(percentLogo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 200, -1, -1));
        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, -40, -1, -1));

        tGrandTotal.setText("Grand total :");
        jPanel1.add(tGrandTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 20, -1, -1));

        jGrandTotal.setEditable(false);
        jGrandTotal.setColumns(5);
        jGrandTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jGrandTotalActionPerformed(evt);
            }
        });
        jPanel1.add(jGrandTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 20, -1, -1));

        tTunai.setText("[F9] Tunai :");
        jPanel1.add(tTunai, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 80, -1, -1));

        jTunai.setColumns(5);
        jTunai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTunaiActionPerformed(evt);
            }
        });
        jTunai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTunaiKeyReleased(evt);
            }
        });
        jPanel1.add(jTunai, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 80, -1, -1));

        tKredit.setText("Kredit : ");
        jPanel1.add(tKredit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 140, -1, -1));

        jKredit.setColumns(5);
        jPanel1.add(jKredit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 140, -1, -1));

        tKembalian.setText("Kembalian :");
        jPanel1.add(tKembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 200, -1, -1));

        jKembalian.setEditable(false);
        jKembalian.setColumns(5);
        jPanel1.add(jKembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 200, -1, -1));

        btSave.setText("Save");
        btSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveActionPerformed(evt);
            }
        });
        jPanel1.add(btSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 300, -1, -1));

        btCancel.setText("Cancel");
        btCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelActionPerformed(evt);
            }
        });
        jPanel1.add(btCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 300, -1, -1));

        jTabbedPane2.addTab("[F3] Menu", jPanel1);

        tTotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tTotal.setText("Total :");

        jTotal.setEditable(false);
        jTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTotalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1268, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tNama)
                            .addComponent(tNoTransaksi)
                            .addComponent(tPelayan))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNamaPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(tTanggal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dTglTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tTotal)
                            .addComponent(jTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(343, 343, 343))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(tTotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tNoTransaksi)
                                        .addComponent(txtNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tTanggal))
                                    .addComponent(dTglTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tNama)
                                    .addComponent(txtNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tPelayan, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtNamaPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        txtNoTransaksi.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNoTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoTransaksiActionPerformed
        
    }//GEN-LAST:event_txtNoTransaksiActionPerformed

    private void txtNoTransaksiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoTransaksiKeyTyped
       
    }//GEN-LAST:event_txtNoTransaksiKeyTyped

    private void txtNamaPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaPelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaPelangganActionPerformed

    private void txtNamaPelangganKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaPelangganKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaPelangganKeyTyped

    private void txtNamaPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaPegawaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaPegawaiActionPerformed

    private void jTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTotalActionPerformed

    private void btCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCancelActionPerformed

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
        // TODO add your handling code here:
        String idTransaksi = txtNoTransaksi.getText(); // misalnya: TRX00005
        String tanggal = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String namapelanggan = txtNamaPelanggan.getText();   
        String namapegawai = txtNamaPegawai.getText();
        int pajak = Integer.parseInt(jApajak.getText());   
        int service = Integer.parseInt(jAservice.getText());
        int subtotal = Integer.parseInt(jSubtotal.getText());
        int diskon = Integer.parseInt(jAdiscount.getText());
        int grandtotal = Integer.parseInt(jGrandTotal.getText());

    try (Connection conn = dbkoneksi.getConnection()) {

        // 1. Simpan ke tabel transaksi
        String sqlTransaksi = "INSERT INTO transaksi (idtransaksi, tgltransaksi, namapelanggan, namapegawai, subtotal, pajak, service, diskon, grandtotal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps1 = conn.prepareStatement(sqlTransaksi);
        ps1.setString(1, idTransaksi);
        ps1.setString(2, tanggal);
        ps1.setString(3, namapelanggan);
        ps1.setString(4, namapegawai);
        ps1.setInt(5, subtotal);
        ps1.setInt(6, pajak);
        ps1.setInt(7, service);
        ps1.setInt(8, diskon);
        ps1.setInt(9, grandtotal);
        ps1.executeUpdate();

        // 2. Simpan ke tabel detailtransaksi (loop setiap baris di tblMenu)
        DefaultTableModel model = (DefaultTableModel) tblMenu.getModel();

        String sqlDetail = "INSERT INTO detailtransaksi (idtransaksi, idmenu, namamenu, harga, jumlah, total) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps2 = conn.prepareStatement(sqlDetail);

        for (int i = 0; i < model.getRowCount(); i++) {
            int idmenu = Integer.parseInt(model.getValueAt(i, 0).toString());
            String nama = model.getValueAt(i, 1).toString();
            int harga = Integer.parseInt(model.getValueAt(i, 2).toString());
            int jumlah = Integer.parseInt(model.getValueAt(i, 3).toString());
            int total = Integer.parseInt(model.getValueAt(i, 4).toString());

    // ... lanjutkan simpan ke database



            ps2.setString(1, idTransaksi);
            ps2.setInt(2, idmenu);
            ps2.setString(3, nama);
            ps2.setInt(4, harga);
            ps2.setInt(5, jumlah);
            ps2.setInt(6, total);
            ps2.executeUpdate();
        }

        JOptionPane.showMessageDialog(null, "Transaksi berhasil disimpan!");
        resetForm();

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Gagal menyimpan transaksi: " + ex.getMessage());
    }

    }//GEN-LAST:event_btSaveActionPerformed

    private void jTunaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTunaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTunaiActionPerformed

    private void jGrandTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jGrandTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jGrandTotalActionPerformed

    private void jAserviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAserviceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jAserviceActionPerformed

    private void jApajakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jApajakActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jApajakActionPerformed

    private void jServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jServiceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jServiceActionPerformed

    private void jPajakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPajakActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPajakActionPerformed

    private void jAdiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAdiscountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jAdiscountActionPerformed

    private void jDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDiscountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDiscountActionPerformed

    private void tDiscountAncestorMoved(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_tDiscountAncestorMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_tDiscountAncestorMoved

    private void mButtonDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mButtonDelActionPerformed
        // TODO add your handling code here:
        int row = tblMenu.getSelectedRow();
        if (row != -1) {
        ((DefaultTableModel) tblMenu.getModel()).removeRow(row);
        hitungTotal();
    }
    }//GEN-LAST:event_mButtonDelActionPerformed

    private void mButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mButtonEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mButtonEditActionPerformed

    private void mButtonInvenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mButtonInvenActionPerformed
        // TODO add your handling code here:
        new kelolaMenu().setVisible(true);
    }//GEN-LAST:event_mButtonInvenActionPerformed

    private void mButtonChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mButtonChooseActionPerformed
        // TODO add your handling code here:
        pilMenuForm panelMenu = new pilMenuForm(kasir.this);
        panelMenu.setVisible(true);
    }//GEN-LAST:event_mButtonChooseActionPerformed

    private void mButtonChooseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mButtonChooseKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_mButtonChooseKeyPressed

    private void jTunaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTunaiKeyReleased
        // TODO add your handling code here:
        hitungKembalian();
    }//GEN-LAST:event_jTunaiKeyReleased

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
            java.util.logging.Logger.getLogger(kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new kasir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btSave;
    private com.toedter.calendar.JDateChooser dTglTransaksi;
    private javax.swing.JTextField jAdiscount;
    private javax.swing.JTextField jApajak;
    private javax.swing.JTextField jAservice;
    private javax.swing.JTextField jDiscount;
    private javax.swing.JTextField jGrandTotal;
    private javax.swing.JTextField jKembalian;
    private javax.swing.JTextArea jKeterangan;
    private javax.swing.JTextField jKredit;
    private javax.swing.JTextField jPajak;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jService;
    private javax.swing.JTextField jSubtotal;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField jTotal;
    private javax.swing.JTextField jTunai;
    private javax.swing.JButton mButtonChoose;
    private javax.swing.JButton mButtonDel;
    private javax.swing.JButton mButtonEdit;
    private javax.swing.JButton mButtonInven;
    private javax.swing.JLabel percentLogo;
    private javax.swing.JLabel percentLogo2;
    private javax.swing.JLabel percentLogo3;
    private javax.swing.JLabel tDiscount;
    private javax.swing.JLabel tGrandTotal;
    private javax.swing.JLabel tKembalian;
    private javax.swing.JLabel tKeterangan;
    private javax.swing.JLabel tKredit;
    private javax.swing.JLabel tNama;
    private javax.swing.JLabel tNoTransaksi;
    private javax.swing.JLabel tPajak;
    private javax.swing.JLabel tPelayan;
    private javax.swing.JLabel tService;
    private javax.swing.JLabel tSubtotal;
    private javax.swing.JLabel tTanggal;
    private javax.swing.JLabel tTotal;
    private javax.swing.JLabel tTunai;
    private javax.swing.JTable tblMenu;
    private javax.swing.JTextField txtNamaPegawai;
    private javax.swing.JTextField txtNamaPelanggan;
    private javax.swing.JTextField txtNoTransaksi;
    // End of variables declaration//GEN-END:variables
}
