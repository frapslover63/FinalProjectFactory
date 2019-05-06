package id.ac.umn.finalprojectfactory

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.*
import kotlinx.android.synthetic.main.activity_new_stock.*
import org.json.JSONObject
import data.Url

class NewStockActivity : AppCompatActivity(), Url {

    lateinit var kode: Editable
    lateinit var ukuran: Editable
    lateinit var warna: Editable
    lateinit var jumlah: Editable
    lateinit var keterangan: Editable
    lateinit var harga: Editable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_stock)

        kode = edt_KodeSendal.text
        ukuran = edt_Ukuran.text
        warna = edt_Warna.text
        jumlah = edt_Jumlah.text
        keterangan = edt_Keterangan.text
        harga = edt_Harga.text

        btnAdd.setOnClickListener {
            var empty: Boolean = false
            var warning: String = ""
            if(kode.isEmpty() || ukuran.isEmpty() || warna.isEmpty() || jumlah.isEmpty() || keterangan.isEmpty() || harga.isEmpty()){
                empty = true
            }

            if(!empty){
                addData()
            }
            else{
                if(kode.isEmpty()){
                    warning += "Kode sendal, "
                }
                if(ukuran.isEmpty()){
                    warning += "Ukuran, "
                }
                if(warna.isEmpty()){
                    warning += "Warna, "
                }
                if(jumlah.isEmpty()){
                    warning += "Jumlah, "
                }
                if(keterangan.isEmpty()){
                    warning += "Keterangan, "
                }
                if(harga.isEmpty()){
                    warning += "Harga, "
                }
                warning += "masih kosong"
                Toast.makeText(this@NewStockActivity, warning, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun addData(){
        val cache = DiskBasedCache(cacheDir, 1024 * 1024)

        val network = BasicNetwork(HurlStack())

        val requestQueue : RequestQueue = RequestQueue(cache, network).apply { start() }

        val urlNewStock: String = callUrlNewProduct()+
                "?produkid=" + kode.toString()+
                "&Keterangan=" + keterangan.toString()+
                "&HargaJual=" + harga.toString()

        val stringRequest = StringRequest(
            Request.Method.GET,
            urlNewStock,
            Response.Listener<String>() {
                val res = JSONObject(it)
                val status: String = res.getString("success")
                val data: String = res.getString("data")
                if(status == "success"){
                    var stringRequest1: StringRequest = addDetail()
                    requestQueue.add(stringRequest1)
                }
                else{
                    Log.e("ResponseUnknown", data)
                    Toast.makeText(this@NewStockActivity, data, Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener {

            }
        )
        requestQueue.add(stringRequest)
    }

    fun addDetail(): StringRequest{
        val urlStockDetail: String = callUrlNewProductDetail()+
                "?produkid=" + kode.toString()+
                "&Warna=" + warna.toString()+
                "&Ukuran=" + ukuran.toString()

        val detailRequest = StringRequest(
            Request.Method.GET,
            urlStockDetail,
            Response.Listener<String>() {
                val res1 = JSONObject(it)
                val status: String = res1.getString("success")
                val data: String = res1.getString("data")
                if(status.equals("success")){
                    Toast.makeText(this@NewStockActivity, "Product Added Successfully", Toast.LENGTH_SHORT).show()
                }
                else{
                    Log.e("ResponseUnknown", data)
                    Toast.makeText(this@NewStockActivity, data, Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener {

            }
        )
        return detailRequest
    }
}
