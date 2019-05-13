package id.ac.umn.finalprojectfactory

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
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
import org.json.JSONArray
import org.json.JSONObject
import data.Url
import data.CustomParameter
import data.DetailProduct
import kotlinx.android.synthetic.main.activity_list_barang.*

class ListBarangActivity : AppCompatActivity(), Url, CustomParameter {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private var dataList: ArrayList<DetailProduct> = ArrayList()
    private var defaultDataList: ArrayList<DetailProduct> = ArrayList()

    private lateinit var txtSearch: EditText

    private lateinit var param: String
    lateinit var tipe: Intent

    fun fetchData(url: String){

        val cache = DiskBasedCache(cacheDir, 1024* 1024);

        val network = BasicNetwork(HurlStack());

        val requestQueue = RequestQueue(cache, network).apply{
            start()
        }

        val request = StringRequest(Request.Method.GET, url,
            Response.Listener  {
                    response->
                val res = JSONObject(response.toString());
                val statusCode: String = res.getString("success")
                if(statusCode.equals("success")){
                    val jsonArray: JSONArray = res.getJSONArray("data")
                    for(i: Int in 0 until (jsonArray.length())){
                        val theData: JSONObject = jsonArray.getJSONObject(i)

                        val product: DetailProduct = DetailProduct(
                            theData.getString("produkid"),
                            theData.getInt("ukuran"),
                            theData.getString("warna"),
                            theData.getInt("jumlah"),
                            theData.getString("keterangan"),
                            theData.getInt("tokoid")
                        )
                        if(tipe.getStringExtra("tipe").equals("toko") && product.tokoId == 2){
                            dataList.add(product)
                            defaultDataList.add(product)
                        }
                        else if(tipe.getStringExtra("tipe").equals("pabrik") && product.tokoId == 1){
                            dataList.add(product)
                            defaultDataList.add(product)
                        }
                    }
                    productAdapter.updateList(dataList, tipe.getStringExtra("tipe"))
                }
            } ,
            Response.ErrorListener { error->
                Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
            }
        )
        requestQueue.add(request)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_barang)

        param = UrlGetStock() + AuthParam()
        Log.e("test", param)
        tipe = intent

        if(intent.getStringExtra("tipe").equals("toko")){
            title_liststock.setText(R.string.list_stock_toko)
        }
        else if(intent.getStringExtra("tipe").equals("pabrik")){
            title_liststock.setText(R.string.list_stock_pabrik)
        }

        dataList = ArrayList()
        defaultDataList = ArrayList()
        fetchData(param)

        productAdapter = ProductAdapter(dataList, this@ListBarangActivity)

        recyclerView = findViewById(R.id.recyclerview_product_conf)
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this@ListBarangActivity)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = productAdapter

//        txtSearch = findViewById(R.id.edt_Search)

        edt_Search.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                Filters(s.toString());
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {


            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        fabNext.setOnClickListener{
            val intent = Intent(this@ListBarangActivity, NewStockOption::class.java);
            startActivity(intent);
        }
    }

    override fun onStart() {
        super.onStart()
        if(dataList.size > 0){
            dataList = ArrayList()
            defaultDataList = ArrayList()
            fetchData(param)
        }
    }

    fun Filters(text:String){
        val filteredList: ArrayList<DetailProduct> = ArrayList()
        if(tipe.getStringExtra("tipe").equals("pabrik")){
            for (dat: DetailProduct in defaultDataList){
                if(dat.productId.toLowerCase().contains(text.toLowerCase())){
                    filteredList.add(dat)
                }
            }
            productAdapter.FilterList(filteredList);
        }
        else if(tipe.getStringExtra("tipe").equals("toko")){
            for(dats: DetailProduct in dataList){
                if(dats.productId.toLowerCase().contains(text.toLowerCase())){
                    filteredList.add(dats)
                }
            }
            productAdapter.FilterList(filteredList)
        }
    }
}
