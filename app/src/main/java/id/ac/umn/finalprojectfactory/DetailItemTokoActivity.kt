package id.ac.umn.finalprojectfactory

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.StringRequest
import data.CustomParameter
import kotlinx.android.synthetic.main.activity_detail_item_pabrik.*
import kotlinx.android.synthetic.main.activity_detail_item_toko.*
import kotlinx.android.synthetic.main.activity_detail_item_toko.txtview_KodeSendal
import kotlinx.android.synthetic.main.activity_detail_item_toko.txtview_Ukuran
import kotlinx.android.synthetic.main.activity_detail_item_toko.txtview_Warna
import org.json.JSONObject

class DetailItemTokoActivity : AppCompatActivity(), CustomParameter {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_item_toko)

        val id : String = intent.getStringExtra("produkid")
        val size : String = intent.getStringExtra("ukuran")
        val color: String = intent.getStringExtra("warna")
        val count: String = intent.getStringExtra("jumlah")

        txtview_KodeSendal.text = id;
        txtview_Ukuran.text = size;
        txtview_Warna.text = color;
        txtview_CurrentStockToko.text = count;

        btnSetStock.setOnClickListener{
            val asString: EditText = edt_Jumlahtoko
            if(asString.text.trim().length<=0){

            }
            else{
                val jumlah: Int = asString.text.toString().toInt()
                sendData(id, size, color, jumlah)
            }
        }
    }

    fun sendData(id: String, size: String, color: String, jumlah: Int){

        val url: String = barangTokoplusDetail(id, size, color, jumlah.toString())

        val cache = DiskBasedCache(cacheDir, 1024* 1024);

        val network = BasicNetwork(HurlStack());

        val requestQueue = RequestQueue(cache, network).apply{
            start()
        }

        Log.e("URL", url)

        val request = StringRequest(Request.Method.GET, url,
            Response.Listener  { response->
                Log.d("Reespon", response.toString());
                val res = JSONObject(response.toString());
                val statusCode: String = res.getString("success")
                if(statusCode.equals("Success")){
                    Toast.makeText(this, "Request Complete", Toast.LENGTH_SHORT).show()
                    txtview_CurrentStockToko.text = (txtview_CurrentStockToko.text.toString().toInt() + edt_Jumlahtoko.text.toString().toInt()).toString()
                }
            } ,
            Response.ErrorListener { error->
                Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
            }

        )
        requestQueue.add(request)

    }
}
