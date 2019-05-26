package data

interface Url{
    fun UrlGetStock(): String{
        return baseUrl()+"/getData/getstockPabrik.php";
    }

    fun callUrlTrigger(): String{
        return baseUrl()+"/transactionData/startTransactionPabrik.php";
    }

    fun callUrlTransaction(): String{
        return baseUrl()+"/transactionData/transactionPabrik.php";
    }

    fun callUrlNewProduct(): String{
        return baseUrl()+"/insertData/insertNewStock.php"
    }

    fun callUrlNewProductDetail(): String{
        return baseUrl()+"/insertData/insertStockDetail.php"
    }

    fun callUrlLaporanToko(): String{
        return baseUrl()+"/transactionReport/reportToko.php";
    }

    fun callUrlLaporanPabrik(): String{
        return baseUrl()+"/transactionReport/reportPabrik.php";
    }

    fun callUrlLaporanTokoDetail(): String{
        return baseUrl()+"/transactionReport/detailreportToko.php"
    }

    fun callUrlLaporanPabrikDetail(): String{
        return baseUrl()+"/transactionReport/detailreportPabrik.php"
    }

    fun callUrlPlusStock(): String{
        return baseUrl()+"/insertStock/plusStock.php"
    }

    fun callUrlVerification(): String{
        return baseUrl()+"/transactionReport/changeStatusVerification.php"
    }

    fun callUrlCurrentPrice(): String{
        return baseUrl()+"/getData/getcurrentPrice.php"
    }

    fun callUpdatePrice(): String{
        return baseUrl()+"/insertData/changePriceStore.php"
    }
}

interface CustomParameter : Url{
    fun AuthParam(): String{
        return "?userName=MarioWibu&password=A559C7CA6258E603E59125333FFF381496982D96CFB54B691A5E84DBFDF2B475";
    }

    fun StartTransactionParam(): String{
        return callUrlTrigger() + "?userName=MWB&Password=MWB";
    }

    fun TransactionParam(): String{
        return callUrlTransaction()
    }

    fun reportTokoParam(fromDate: String, toDate: String): String{
        return callUrlLaporanToko()+"?fromDate="+fromDate+"&toDate="+toDate;
    }

    fun reportPabrikParam(fromDate: String, toDate: String): String{
        return callUrlLaporanPabrik()+"?fromDate="+fromDate+"&toDate="+toDate;
    }

    fun reportTokoParamDetail(id: String): String{
        return callUrlLaporanTokoDetail() + "?id=" + id
    }

    fun reportPabrikParamDetail(id: String): String{
        return callUrlLaporanPabrikDetail() + "?id=" + id
    }

    fun barangpabrikplusDetail(id: String, size: String, color: String, count: String): String{
        return callUrlPlusStock()+"?tokoid=1&produkid="+id+"&Warna="+color+"&Ukuran="+size+"&Jumlah="+count
    }

    fun barangTokoplusDetail(id: String, size: String, color: String, count: String): String{
        return callUrlPlusStock()+"?tokoid=2&produkid="+id+"&Warna="+color+"&Ukuran="+size+"&Jumlah="+count
    }

    fun transactionVerification(id: String): String{
        return callUrlVerification()+ "?id=" + id
    }

    fun currentPrice(): String{
        return callUrlCurrentPrice()+"?userName=MWB&Password=MWB"
    }

    fun changecurrentPrice(price: Int): String{
        return callUpdatePrice()+"?harga="+price;
    }
}


private fun baseUrl(): String{
    return "http://192.168.1.6" +
            "";
}