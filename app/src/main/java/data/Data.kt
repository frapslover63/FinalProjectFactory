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
        return baseUrl()+"/transactionReport/detailreportPabrik.php"
    }

    fun callUrlLaporanPabrikDetail(): String{
        return baseUrl()+"/transactionReport/detailreportToko.php"
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
}


private fun baseUrl(): String{
    return "http://192.168.0.24";
}