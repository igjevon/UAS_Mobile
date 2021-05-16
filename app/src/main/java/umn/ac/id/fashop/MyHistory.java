package umn.ac.id.fashop;

public class MyHistory {
    String nama_barang, tanggal_order, jumlah;
    Integer harga;

    public MyHistory() {
    }

    public MyHistory(String nama_barang, String tanggal_order, String jumlah, Integer harga) {
        this.nama_barang = nama_barang;
        this.tanggal_order = tanggal_order;
        this.jumlah = jumlah;
        this.harga = harga;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getTanggal_order() {
        return tanggal_order;
    }

    public void setTanggal_order(String tanggal_order) {
        this.tanggal_order = tanggal_order;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }
}
