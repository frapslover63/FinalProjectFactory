package id.ac.umn.finalprojectfactory

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import data.Product
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import data.Url
import data.CustomParameter

class ListBarangActivity : AppCompatActivity(), Url, CustomParameter {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private var dataList: ArrayList<Product> = ArrayList()
    private var defaultDataList: ArrayList<Product> = ArrayList()

    //private lateinit var btnSearch: Button
    private lateinit var txtSearch: EditText

    private var param: String = AuthParam()
    //private lateinit var requestQueue: RequestQueue

    private inner class FetchData : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String?): String? {
            var urlConnection: HttpURLConnection? = null
            var bufferedReader: BufferedReader? = null

            var jsonString: String = ""
            try {
                val urlString: String = UrlGetStock() + params[0]
                val urlConnect: URL = URL(urlString)

                urlConnection = urlConnect.openConnection() as HttpURLConnection

                urlConnection.requestMethod = "GET"

                urlConnection.connect()

                //var lengthOfFile: Int = urlConnection.contentLength

                val inputStream: InputStream = urlConnection.inputStream

                if(inputStream.available() > 0){
                    return null
                }

                bufferedReader = BufferedReader(InputStreamReader(inputStream))

                val line: String = bufferedReader.readLine()

                jsonString = line

                Log.d("FETCHDATA", jsonString)

                val jsonObject: JSONObject = JSONObject(jsonString)

                val statusCode: String = jsonObject.getString("success")

                if(statusCode.equals("success")){
                    val jsonArray: JSONArray = jsonObject.getJSONArray("data")

                    for(i: Int in 0 until (jsonArray.length())){
                        val theData: JSONObject = jsonArray.getJSONObject(i)

                        val product: Product = Product(
                            theData.getInt("tokoid"),
                            theData.getInt("jumlah"),
                            theData.getString("warna"),
                            theData.getInt("ukuran"),
                            theData.getString("produkid")
                        )

                        dataList.add(product)
                        defaultDataList.add(product)
                    }
                    productAdapter.updateList(dataList)
                }
            }
            catch (e: MalformedURLException){
                Log.e("MALFORMED", "MalformedURLException" + e.message)
            }
            catch (e: IOException){
                Log.e("IO", "IOException" + e.message)
            }
            catch (e: JSONException){
                e.printStackTrace()
            }
            finally {
                urlConnection?.disconnect()

                if (bufferedReader != null) {
                    try {
                        bufferedReader.close()
                    } catch (e: IOException) {
                        Log.e("BUFFEREDIOEXCEPTION", "IOException" + e.message)
                    }
                }
            }
            return jsonString
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_barang)

        dataList = ArrayList()
        defaultDataList = ArrayList()
        FetchData().execute(param)

        productAdapter = ProductAdapter(dataList, this@ListBarangActivity)

        recyclerView = findViewById(R.id.recyclerview_Barang)
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this@ListBarangActivity)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = productAdapter

        txtSearch = findViewById(R.id.edt_Search)

        txtSearch.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                Filters(s.toString());
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {


            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    fun Filters(text:String){
        var filterProduct: ArrayList<Product> = ArrayList()
        for(item: Product in defaultDataList){
            if(item.produkId.toLowerCase().contains(text.toLowerCase())){
                filterProduct.add(item)
            }
        }

        productAdapter.FilterList(filterProduct)
    }
}
