package id.ac.umn.finalprojectfactory

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.widget.EditText
import android.widget.TextView
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
import kotlinx.android.synthetic.main.activity_ubah_harga.*
import org.json.JSONObject

class UbahHargaActivity : AppCompatActivity(), CustomParameter{

    var price: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubah_harga)

        fetchPrice()

        btn_updatePrice.setOnClickListener{
            val asString: EditText = edt_HargaBaru
            if(asString.text.trim().length<=0){
                Snackbar.make(it, "Field Jumlah Tidak Boleh Kosong!", Snackbar.LENGTH_SHORT).show()
            }
            else{
                val newPrice: Int = asString.text.toString().toInt()
                sendData(newPrice)
            }
        }
    }

    fun fetchPrice(){
        val url: String = currentPrice();

        val cache = DiskBasedCache(cacheDir, 1024* 1024);

        val network = BasicNetwork(HurlStack());

        val requestQueue = RequestQueue(cache, network).apply{
            start()
        }

        Log.e("URL", url)

        val request = StringRequest(Request.Method.GET, url,
            Response.Listener  { response->
                Log.d("Respon", response.toString());
                val res = JSONObject(response.toString());
                val statusCode: String = res.getString("success")
                if(statusCode.equals("Success")){
                    val data: String = res.getString("data");
                    txtview_HargaJualToko.text = "Harga Toko Saat ini : " +data
                }
            } ,
            Response.ErrorListener { error->
                Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
            }

        )
        requestQueue.add(request)
    }

    fun sendData(price: Int){
        val url: String = changecurrentPrice(price)

        val cache = DiskBasedCache(cacheDir, 1024* 1024);

        val network = BasicNetwork(HurlStack());

        val requestQueue = RequestQueue(cache, network).apply{
            start()
        }

        Log.e("URL", url)

        val request = StringRequest(Request.Method.GET, url,
            Response.Listener  { response->
                Log.d("Respon", response.toString());
                val res = JSONObject(response.toString());
                val statusCode: String = res.getString("success")
                if(statusCode.equals("Success")){
                    Toast.makeText(this, "Request Complete", Toast.LENGTH_LONG).show()
                }
            } ,
            Response.ErrorListener { error->
                Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
            }

        )
        requestQueue.add(request)
    }

}
