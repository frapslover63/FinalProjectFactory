package data

class LaporanToko {
    var transactionId: Int
    var tanggal: String
    var status: Int
    var totalHarga: Int


    constructor(transactionId: Int, tanggal: String, status: Int, totalHarga: Int) {
        this.transactionId = transactionId
        this.tanggal = tanggal
        this.status = status
        this.totalHarga = totalHarga
    }
}