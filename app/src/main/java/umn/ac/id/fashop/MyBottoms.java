package umn.ac.id.fashop;

public class MyBottoms {
    String nama_barang, ukuran, url_product_image1;
    Integer harga;

    public MyBottoms() {
    }

    public MyBottoms(String nama_barang, String ukuran, String url_product_image1, Integer harga) {
        this.nama_barang = nama_barang;
        this.ukuran = ukuran;
        this.url_product_image1 = url_product_image1;
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

    public String getUrl_product_image1() {
        return url_product_image1;
    }

    public void setUrl_product_image1(String url_product_image1) {
        this.url_product_image1 = url_product_image1;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }
}
