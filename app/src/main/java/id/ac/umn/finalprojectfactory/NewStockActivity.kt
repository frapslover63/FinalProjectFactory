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
                "&Keterangan" + keterangan.toString()+
                "&HargaJual" + harga.toString().toInt()

        val urlStockDetail: String = callUrlNewProductDetail()+
                "?produkid=" + kode.toString()+
                "&Warna" + warna.toString()+
                "&Ukuran" + ukuran.toString().toInt()

        val stringRequest = StringRequest(
            Request.Method.GET,
            urlNewStock,
            Response.Listener<String>() {
                val res = JSONObject(it)
                val status: JSONObject = res.getJSONObject("success")
                val data: JSONObject = res.getJSONObject("data")
                if(status.getString("success").equals("success")){
                    val detailRequest = StringRequest(
                        Request.Method.GET,
                        urlStockDetail,
                        Response.Listener<String>() {
                            val res1 = JSONObject(it)
                            val status1: JSONObject = res1.getJSONObject("success")
                            val data1: JSONObject = res1.getJSONObject("data")
                            if(status1.getString("success").equals("success")){
                                Toast.makeText(this@NewStockActivity, "Product Added Successfully", Toast.LENGTH_SHORT).show()
                            }
                            else{
                                Log.e("ResponseUnknown", data1.toString())
                                Toast.makeText(this@NewStockActivity, data1.toString(), Toast.LENGTH_SHORT).show()
                            }
                        },
                        Response.ErrorListener {

                        }
                    )
                    requestQueue.add(detailRequest)
                }
                else{
                    Log.e("ResponseUnknown", data.toString())
                    Toast.makeText(this@NewStockActivity, data.toString(), Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener {

            }
        )
        requestQueue.add(stringRequest)
    }
}
