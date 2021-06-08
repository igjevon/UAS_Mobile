package umn.ac.id.fashop;

public class MyHistory {
    String nama_barang, ukuran;
    Integer harga, jumlah;

    public MyHistory() {
    }

    public MyHistory(String nama_barang, String ukuran, Integer harga, Integer jumlah) {
        this.nama_barang = nama_barang;
        this.ukuran = ukuran;
        this.harga = harga;
        this.jumlah = jumlah;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }
}
