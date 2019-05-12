package id.ac.umn.finalprojectfactory

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.StringRequest
import data.CustomParameter
import data.Product
import kotlinx.android.synthetic.main.activity_detail_transaksi_pabrik.*
import org.json.JSONArray
import org.json.JSONObject

class DetailTransaksiTokoActivity : AppCompatActivity(), CustomParameter {

    var productList: ArrayList<Product> = ArrayList()
    lateinit var lAdapter: ProductAdapter
    lateinit var id: String
    lateinit var tanggal: String
    var totalHarga: Int = 0
    var total: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_transaksi_toko)

        id = intent.getStringExtra("ID")
        tanggal = intent.getStringExtra("tanggal")
        totalHarga = intent.getStringExtra("totalHarga").toInt()
        val url: String = reportTokoParamDetail(id)
        fetchDataToko(url, tanggal)
        lAdapter = ProductAdapter(productList, this)
        recyclerview_product_conf.adapter = lAdapter

        txtview_Tanggal.text = tanggal
        txtview_Totalharga.text = total.toString()
    }

    fun fetchDataToko(url: String, tanggal: String){
        val cache = DiskBasedCache(cacheDir, 1024*1024);
        val network = BasicNetwork(HurlStack())

        val requestQueue = RequestQueue(cache, network).apply{
            start()
        }
        Log.e("Test", url)
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String>{
                    response ->
                val res = response.toString()
                val result: JSONObject = JSONObject(res)
                val statusCode: String = result.getString("success");
                if(statusCode.equals("Success")){
                    val jsonArray: JSONArray = result.getJSONArray("data")
                    for(i: Int in 0 until (jsonArray.length())){
                        val theData: JSONObject = jsonArray.getJSONObject(i)

                        val product: Product = Product(
                            theData.getInt("jumlah"),
                            theData.getString("warna"),
                            theData.getInt("ukuran"),
                            theData.getString("produkid")
                        )
                        productList.add(product)
                    }
                    lAdapter.updateList(productList)
                }
            },
            Response.ErrorListener {
                    error ->
                Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
            }
        )
        requestQueue.add(stringRequest)
    }
}
