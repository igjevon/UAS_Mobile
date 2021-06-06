package umn.ac.id.fashop;

import android.net.Uri;

import java.io.Serializable;

public class MyTops implements Serializable {
    String nama_barang, ukuran, url_product_image1;
    Integer harga;

    public MyTops() {
    }

    public MyTops(String nama_barang, String ukuran, Integer harga, String url_product_image1
    ) {
        this.nama_barang = nama_barang;
        this.ukuran = ukuran;
        this.harga = harga;
        this.url_product_image1 = url_product_image1;
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

    public String getGambar_barang() {
        return url_product_image1;
    }

    public void setGambar_barang(String url_product_image1) {
        this.url_product_image1 = url_product_image1;
    }
}
