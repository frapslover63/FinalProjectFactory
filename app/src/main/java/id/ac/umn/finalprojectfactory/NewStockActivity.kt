package id.ac.umn.finalprojectfactory

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
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
    lateinit var keterangan: Editable
    lateinit var harga: Editable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_stock)

        kode = edt_KodeSendal.text
        keterangan = edt_Keterangan.text
//        harga = edt_Harga.text

        btnAdd.setOnClickListener {
            var empty: Boolean = false
            var warning: String = ""
            if(kode.isEmpty() || keterangan.isEmpty()){
                empty = true
            }

            if(!empty){
                addData()
            }
            else{
                if(kode.isEmpty()){
                    warning += "Kode sendal, "
                }
                if(keterangan.isEmpty()){
                    warning += "Keterangan, "
                }
                warning += "masih kosong"
                Snackbar.make(it, warning, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    fun addData(){
        val cache = DiskBasedCache(cacheDir, 1024 * 1024)

        val network = BasicNetwork(HurlStack())

        val requestQueue : RequestQueue = RequestQueue(cache, network).apply { start() }

        val urlNewStock: String = callUrlNewProduct()+
                "?produkid=" + kode.toString()+
                "&Keterangan=" + keterangan.toString()

        val stringRequest = StringRequest(
            Request.Method.GET,
            urlNewStock,
            Response.Listener<String>() {
                val res = JSONObject(it)
                val status: String = res.getString("success")
                val data: String = res.getString("data")
                if(status == "Success"){
//                    var stringRequest1: StringRequest = addDetail()
//                    requestQueue.add(stringRequest1)
                    Toast.makeText(this, "New Stock Added", Toast.LENGTH_LONG).show()
                    finish()
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
}
