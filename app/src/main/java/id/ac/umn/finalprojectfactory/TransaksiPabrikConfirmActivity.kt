package id.ac.umn.finalprojectfactory

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import data.Transaction
import kotlinx.android.synthetic.main.activity_transaksi_pabrik_conf.*
import org.json.JSONObject
import data.CustomParameter

class TransaksiPabrikConfirmActivity : AppCompatActivity(), CustomParameter {

    lateinit var tAdapter: TransactionAdapter
    var transactionList: ArrayList<Transaction> = ArrayList()

    var empty: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaksi_pabrik_conf)

        val s: String = intent.getStringExtra("json")
        val gson: Gson = Gson()
        val data: ArrayList<Transaction> = gson.fromJson(s, object : TypeToken<ArrayList<Transaction>>() {}.type)

        // Set Data dari JSON
        data.forEach {
            transactionList.add(it)
        }

        val layoutManager = LinearLayoutManager(this)
        recyclerview_product_conf.layoutManager = layoutManager
        tAdapter = TransactionAdapter(transactionList, this)
        recyclerview_product_conf.adapter = tAdapter

        btnConfirm.setOnClickListener {

            if(edt_CompanyName.text.isNullOrEmpty() || transactionList.size <= 0){
                empty = true
                Snackbar.make(it, "Company Name, masih kosong!", Snackbar.LENGTH_SHORT).show()
            }

            if(!empty){
                Toast.makeText(it.context, "SendData", Toast.LENGTH_SHORT).show()
                val datas: String = Gson().toJson(transactionList)
                sendData(datas, edt_CompanyName.text.toString())
            }
        }

    }

    private fun sendProductData(id: String, data: String){
        val url = TransactionParam()

        val postBody = JSONObject();
        postBody.put("transactionid", id);
        postBody.put("jsonString", data);

        Log.d("Data", postBody.toString());

        val queue = Volley.newRequestQueue(this);
        val request = JsonObjectRequest(Request.Method.POST, url, postBody,
            Response.Listener {
                    response ->
                Log.d("Response2", response.toString() )
                val statusCode: String = response.getString("success")
                if(statusCode.equals("Success")){

                    val statusCodes: String = response.getString("success")
                    Toast.makeText(this, statusCodes, Toast.LENGTH_LONG).show()
                }
            }, Response.ErrorListener {
                    error : VolleyError -> Log.d("Error2", error.toString());
            }
        )
        queue.add(request)
    }

    private fun sendData(s: String, c: String){
        val cache = DiskBasedCache(cacheDir, 1024 * 1024);

        val network = BasicNetwork(HurlStack());

        val requestQueue = RequestQueue(cache, network).apply {
            start()
        }

        val url = StartTransactionParam() + "&Company=" + c;
        Log.e("URL", url)

        val request = StringRequest(Request.Method.GET, url,
            Response.Listener {
                    response ->
                Log.d("Response", response.toString() )
                val res = JSONObject(response.toString())
                val statusCode: String = res.getString("success")
                if(statusCode.equals("Success")){
                    val transactionId  = res.getString("data")
                    sendProductData(transactionId, s)
                    Log.d("TID",transactionId)
                }
            }, Response.ErrorListener {
                    error : VolleyError -> Log.d("Error1", error.toString())
            }
        )
        requestQueue.add(request);
    }
}
