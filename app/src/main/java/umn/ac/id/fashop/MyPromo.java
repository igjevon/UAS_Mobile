package umn.ac.id.fashop;

public class MyPromo {
    String nama_barang, url_product_image1;
    Integer harga;

    public MyPromo() {
    }

    public MyPromo(String nama_barang, String url_product_image1, Integer harga) {
        this.nama_barang = nama_barang;
        this.url_product_image1 = url_product_image1;
        this.harga = harga;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
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
