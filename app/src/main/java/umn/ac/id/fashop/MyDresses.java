package umn.ac.id.fashop;

public class MyDresses {
    String nama_barang, ukuran;
    Integer harga;

    public MyDresses() {
    }

    public MyDresses(String nama_barang, String ukuran, Integer harga) {
        this.nama_barang = nama_barang;
        this.ukuran = ukuran;
        this.harga = harga;
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
}
