package id.ac.umn.finalprojectfactory

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
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
import data.Laporan
import data.LaporanToko
import data.Product
import data.Url
import kotlinx.android.synthetic.main.activity_list_laporan.*
import org.json.JSONArray
import org.json.JSONObject

class ListLaporanActivity : AppCompatActivity(), Url, CustomParameter {

    var laporanList: ArrayList<Laporan> = ArrayList()
    var laporanListToko: ArrayList<LaporanToko> = ArrayList()
    lateinit var lAdapter: LaporanAdapter
    lateinit var tAdapter: LaporanAdapterToko
    lateinit var tipe: String
    lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_laporan)

        val dayStart: String = intent.getStringExtra("hariDari")
        val monthStart: String = intent.getStringExtra("bulanDari")
        val yearStart: String = intent.getStringExtra("tahunDari")
        val dayTo: String = intent.getStringExtra("hariSampai")
        val monthTo: String = intent.getStringExtra("bulanSampai")
        val yearTo: String = intent.getStringExtra("tahunSampai")

        val dateFrom: String = yearStart+"/"+monthStart+"/"+dayStart
        val dateTo: String = yearTo+"/"+monthTo+"/"+dayTo

        val layoutManager = LinearLayoutManager(this)
        recyclerview_laporan.layoutManager = layoutManager

        tipe = intent.getStringExtra("tipe")
        if(tipe.equals("pabrik")){
            url = reportPabrikParam(dateFrom,dateTo)
            fetchDataPabrik(url)
            lAdapter = LaporanAdapter(laporanList, this)
            recyclerview_laporan.adapter = lAdapter
        }else if(tipe.equals("toko")){
            url = reportTokoParam(dateFrom,dateTo)
            fetchDataToko(url)
            tAdapter = LaporanAdapterToko(laporanListToko, this)
            recyclerview_laporan.adapter = tAdapter
        }

        edt_Search.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                Filters(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    fun Filters(text:String){
        var filteredList: ArrayList<Laporan> = ArrayList()
        var filteredListToko: ArrayList<LaporanToko> = ArrayList()
        if(tipe.equals("pabrik")){
            for (lap: Laporan in laporanList ){
                if(lap.tanggal.toLowerCase().contains(text.toLowerCase())){
                    filteredList.add(lap)
                }
            }
            lAdapter.FilterList(filteredList);
        }else if(tipe.equals("toko")){
            for(laps: LaporanToko in laporanListToko){
                if(laps.tanggal.toLowerCase().contains(text.toLowerCase())){
                    filteredListToko.add(laps)
                }
            }
            tAdapter.FilterList(filteredListToko)
        }

    }
    override fun onStart() {
        super.onStart()
        if(tipe.equals("toko")){
            if(laporanListToko.size > 0){
                laporanListToko = ArrayList()
                fetchDataToko(url)
            }
        }
        else if(tipe.equals("pabrik")){
            if(laporanList.size > 0){
                laporanList = ArrayList()
                fetchDataPabrik(url)
            }
        }
    }

    fun fetchDataPabrik(url: String){
        val cache = DiskBasedCache(cacheDir, 1024*1024);
        val network = BasicNetwork(HurlStack())

        val requestQueue = RequestQueue(cache, network).apply{
            start()
        }
        Log.e("Test", url)
        val stringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener<String>{
                response ->
                    val res = response.toString()
                    val result: JSONObject = JSONObject(res)
                    val statusCode: String = result.getString("success");
                if(statusCode.equals("Success")){
                    val jsonArray: JSONArray = result.getJSONArray("data")
                    for(i: Int in 0 until (jsonArray.length())){
                        val theData: JSONObject = jsonArray.getJSONObject(i)

                        val laporan: Laporan = Laporan(
                            theData.getInt("transaksipabrikid"),
                            theData.getString("times"),
                            theData.getString("company"),
                            theData.getInt("total")
                        )

                        laporanList.add(laporan)
                    }
                    lAdapter.updateList(laporanList)
                }
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
        val stringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener<String>{
                    response ->
                val res = response.toString()
                val result: JSONObject = JSONObject(res)
                val statusCode: String = result.getString("success");
                if(statusCode.equals("Success")){
                    val jsonArray: JSONArray = result.getJSONArray("data")
                    for(i: Int in 0 until (jsonArray.length())){
                        val theData: JSONObject = jsonArray.getJSONObject(i)

                        val laporantoko: LaporanToko = LaporanToko(
                            theData.getInt("transaksitokoid"),
                            theData.getString("times"),
                            theData.getInt("status"),
                            theData.getInt("total")
                        )
                        laporanListToko.add(laporantoko)
                    }
                    tAdapter.updateList(laporanListToko)
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
