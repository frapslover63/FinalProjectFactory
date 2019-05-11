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
}


private fun baseUrl(): String{
    return "http://192.168.0.24";
}