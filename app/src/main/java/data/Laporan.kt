package data

class Laporan {
    var transactionId: String
    var tanggal: String
    var namaCompany: String
    var totalHarga: Int

    constructor(transactionId: String, tanggal: String, namaCompany: String, total: Int) {
        this.transactionId = transactionId
        this.tanggal = tanggal
        this.namaCompany = namaCompany
        this.totalHarga = total
    }
}