package kasir;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
public class session {
    private static String username;
    private static String namaPegawai;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        session.username = username;
    }

    public static String getNamaPegawai() {
        return namaPegawai;
    }

    public static void setNamaPegawai(String namaPegawai) {
        session.namaPegawai = namaPegawai;
    }
}
