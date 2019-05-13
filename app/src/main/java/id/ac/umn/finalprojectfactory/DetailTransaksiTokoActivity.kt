package id.ac.umn.finalprojectfactory

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
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
import kotlinx.android.synthetic.main.activity_detail_transaksi_toko.*
import kotlinx.android.synthetic.main.activity_detail_transaksi_toko.txtview_Tanggal
import kotlinx.android.synthetic.main.activity_detail_transaksi_toko.txtview_Totalharga
import org.json.JSONArray
import org.json.JSONObject
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.util.Base64


class DetailTransaksiTokoActivity : AppCompatActivity(), CustomParameter {

    var productList: ArrayList<Product> = ArrayList()
    lateinit var lAdapter: ProductAdapterDetailToko
    lateinit var id: String
    lateinit var tanggal: String
    var totalHarga: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_transaksi_toko)

        id = intent.getStringExtra("ID")
        tanggal = intent.getStringExtra("tanggal")
        totalHarga = intent.getStringExtra("totalHarga").toInt()
        val url: String = reportTokoParamDetail(id)
        fetchDataToko(url)

        val layoutManager = LinearLayoutManager(this)
        recyclerview_product_conf_toko.layoutManager = layoutManager
        lAdapter = ProductAdapterDetailToko(productList, this)
        recyclerview_product_conf_toko.adapter = lAdapter

        txtview_KodeTransaksiToko.text = id
        txtview_Tanggal.text = tanggal
        txtview_Totalharga.text = totalHarga.toString()

        val urlVer: String = transactionVerification(id)
        btnVerifikasi.setOnClickListener {
            verification(urlVer)
        }
    }

    fun verification(url: String){
        val cache = DiskBasedCache(cacheDir, 1024*1024);
        val network = BasicNetwork(HurlStack())

        val requestQueue = RequestQueue(cache, network).apply{
            start()
        }
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String>{
                    response ->
                Toast.makeText(this, "Verified", Toast.LENGTH_SHORT).show()
                finish()
            },
            Response.ErrorListener {
                    error ->
                Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
            }
        )
        requestQueue.add(stringRequest)
    }

    fun fetchDataToko(url: String){
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
                if(statusCode.equals("success")){
                    val jsonArray: JSONArray = result.getJSONArray("data")
                    val decodedString = Base64.decode(result.getString("image"), Base64.DEFAULT)
                    val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                    imgview_Verifikasi.setImageBitmap(decodedByte)
                    for(i: Int in 0 until (jsonArray.length())){
                        val theData: JSONObject = jsonArray.getJSONObject(i)

                        val product: Product = Product(
                            theData.getInt("jumlah"),
                            theData.getString("warna"),
                            theData.getInt("ukuran"),
                            theData.getString("produkid"),
                            theData.getInt("harga")
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