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
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.StringRequest
import data.Url
import kotlinx.android.synthetic.main.activity_new_model.*
import kotlinx.android.synthetic.main.activity_new_model.btnAdd
import kotlinx.android.synthetic.main.activity_new_model.edt_KodeSendal
import kotlinx.android.synthetic.main.activity_new_stock.*
import org.json.JSONObject

class NewModelActivity : AppCompatActivity(), Url {

    lateinit var kode: Editable
    lateinit var ukuran: Editable
    lateinit var warna: Editable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_model);

        kode = edt_KodeSendal.text;
        ukuran = edt_Ukuran.text;
        warna = edt_Warna.text;

        btnAdd.setOnClickListener {
            var empty: Boolean = false
            var warning: String = ""
            if(kode.isEmpty() || ukuran.isEmpty() || warna.isEmpty()){
                empty = true
            }

            if(!empty){
                addData();
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
                warning += "masih kosong"
                Snackbar.make(it, warning, Snackbar.LENGTH_SHORT).show()
            }
        }
    }


    fun addData() {
        val cache = DiskBasedCache(cacheDir, 1024 * 1024)

        val network = BasicNetwork(HurlStack())

        val requestQueue: RequestQueue = RequestQueue(cache, network).apply { start() }

        val urlStockDetail: String = callUrlNewProductDetail() +
                "?produkid=" + kode.toString() +
                "&Warna=" + warna.toString() +
                "&Ukuran=" + ukuran.toString()

        val stringRequest = StringRequest(
            Request.Method.GET,
            urlStockDetail,
            Response.Listener<String>() {
                val res = JSONObject(it)
                val status: String = res.getString("success")
                val data: String = res.getString("data")
                if (status == "Success") {
                    Toast.makeText(this, "New Model Added", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Log.e("ResponseUnknown", data)
                    Toast.makeText(this@NewModelActivity, data, Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener {

            }
        )
        requestQueue.add(stringRequest);
    }
}


