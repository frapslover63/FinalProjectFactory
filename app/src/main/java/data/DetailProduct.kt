package data

class DetailProduct {
    var productId: String;
    var ukuran: Int;
    var warna: String;
    var jumlah: Int;
    var keterangan: String;
    var tokoId: Int;

    constructor(productid: String,ukuran: Int, warna: String, jumlah: Int, keterangan: String, tokoid: Int) {
        this.productId = productid;
        this.ukuran = ukuran;
        this.warna = warna;
        this.jumlah = jumlah;
        this.keterangan = keterangan;
        this.tokoId = tokoid
    }
}