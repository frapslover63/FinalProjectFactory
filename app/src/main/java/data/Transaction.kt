package data

class Transaction : Product {

    var harga: Int

    constructor(idToko: Int, jumlah: Int, warna: String, ukuran: Int, produkId: String, harga: Int) : super(idToko, jumlah, warna, ukuran, produkId){
        this.harga = harga
    }
}