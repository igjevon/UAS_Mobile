package umn.ac.id.fashop;

public class MyHistory {
    String nama_barang, ukuran, tanggal_order;
    Integer harga, jumlah;

    public MyHistory() {
    }

    public MyHistory(String nama_barang, String ukuran, String tanggal_order, Integer harga, Integer jumlah) {
        this.nama_barang = nama_barang;
        this.ukuran = ukuran;
        this.tanggal_order = tanggal_order;
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

    public String getTanggal_order() {
        return tanggal_order;
    }

    public void setTanggal_order(String tanggal_order) {
        this.tanggal_order = tanggal_order;
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
