/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package kasir;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;
/**
 *
 * @author user
 */
public class KelolaMenu extends javax.swing.JFrame {
    private JTable tblMenu;
    private JTextField txtNama, txtHarga;
    private JButton btnTambah, btnEdit, btnHapus;

    private DefaultTableModel model;

    /**
     * Creates new form KelolaMenu
     */
    public KelolaMenu() {
        setTitle("Kelola Menu");
        setSize(500, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblNama = new JLabel("Nama Menu:");
        lblNama.setBounds(20, 20, 100, 25);
        add(lblNama);

        txtNama = new JTextField();
        txtNama.setBounds(120, 20, 200, 25);
        add(txtNama);

        JLabel lblHarga = new JLabel("Harga:");
        lblHarga.setBounds(20, 60, 100, 25);
        add(lblHarga);

        txtHarga = new JTextField();
        txtHarga.setBounds(120, 60, 200, 25);
        add(txtHarga);

        btnTambah = new JButton("Tambah");
        btnTambah.setBounds(350, 20, 100, 25);
        add(btnTambah);

        btnEdit = new JButton("Edit");
        btnEdit.setBounds(350, 60, 100, 25);
        add(btnEdit);

        btnHapus = new JButton("Hapus");
        btnHapus.setBounds(350, 100, 100, 25);
        add(btnHapus);

        // Tabel
        model = new DefaultTableModel(new Object[]{"ID", "Nama Menu", "Harga"}, 0);
        tblMenu = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tblMenu);
        scrollPane.setBounds(20, 140, 440, 200);
        add(scrollPane);

        tampilkanData();

        // Aksi Tambah
        btnTambah.addActionListener(e -> tambahMenu());

        // Aksi Edit
        btnEdit.addActionListener(e -> editMenu());

        // Aksi Hapus
        btnHapus.addActionListener(e -> hapusMenu());

        // Ketika klik di tabel, isi ke form
        tblMenu.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tblMenu.getSelectedRow();
                if (row != -1) {
                    txtNama.setText(tblMenu.getValueAt(row, 1).toString());
                    txtHarga.setText(tblMenu.getValueAt(row, 2).toString());
                }
            }
        });

    }
    private void tampilkanData() {
        model.setRowCount(0);
        try (Connection conn = dbkoneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM menu")) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("idmenu"),
                    rs.getString("namamenu"),
                    rs.getInt("hargamenu")
                });
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal load data: " + ex.getMessage());
        }
    }

    private void tambahMenu() {
        String nama = txtNama.getText();
        String hargaStr = txtHarga.getText();

        if (nama.isEmpty() || hargaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Isi semua field!");
            return;
        }

        try (Connection conn = dbkoneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO menu(namamenu, hargamenu) VALUES (?, ?)")) {

            stmt.setString(1, nama);
            stmt.setInt(2, Integer.parseInt(hargaStr));
            stmt.executeUpdate();

            tampilkanData();
            txtNama.setText("");
            txtHarga.setText("");
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Gagal tambah: " + ex.getMessage());
        }
    }

    private void editMenu() {
        int row = tblMenu.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih menu yang akan diedit.");
            return;
        }

        int id = (int) tblMenu.getValueAt(row, 0);
        String nama = txtNama.getText();
        String hargaStr = txtHarga.getText();

        try (Connection conn = dbkoneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE menu SET namamenu=?, hargamenu=? WHERE idmenu=?")) {

            stmt.setString(1, nama);
            stmt.setInt(2, Integer.parseInt(hargaStr));
            stmt.setInt(3, id);
            stmt.executeUpdate();

            tampilkanData();
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Gagal edit: " + ex.getMessage());
        }
    }

    private void hapusMenu() {
        int row = tblMenu.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih menu yang akan dihapus.");
            return;
        }

        int id = (int) tblMenu.getValueAt(row, 0);

        int konfirmasi = JOptionPane.showConfirmDialog(this, "Hapus menu ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (konfirmasi == JOptionPane.YES_OPTION) {
            try (Connection conn = dbkoneksi.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("DELETE FROM menu WHERE idmenu=?")) {

                stmt.setInt(1, id);
                stmt.executeUpdate();

                tampilkanData();
                txtNama.setText("");
                txtHarga.setText("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Gagal hapus: " + ex.getMessage());
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(KelolaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KelolaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KelolaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KelolaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KelolaMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
