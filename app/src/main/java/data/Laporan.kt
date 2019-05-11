package data

class Laporan {
    var transactionId: Int
    var tanggal: String
    var namaCompany: String
    var totalHarga: Int


    constructor(transactionId: Int, tanggal: String, namaCompany: String, total: Int) {
        this.transactionId = transactionId
        this.tanggal = tanggal
        this.namaCompany = namaCompany
        this.totalHarga = total
    }
}