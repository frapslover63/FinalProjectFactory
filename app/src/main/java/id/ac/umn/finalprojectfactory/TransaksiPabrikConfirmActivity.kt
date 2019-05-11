package id.ac.umn.finalprojectfactory

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import data.Transaction
import kotlinx.android.synthetic.main.activity_transaksi_pabrik_conf.*

class TransaksiPabrikConfirmActivity : AppCompatActivity() {

    lateinit var tAdapter: TransactionAdapter
    var transactionList: ArrayList<Transaction> = ArrayList()

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
            var empty:Boolean = false
            if(edt_CompanyName.text.isNullOrEmpty()){
                empty = true
                Snackbar.make(it, "Company Name, masih kosong!", Snackbar.LENGTH_SHORT).show()
            }

            if(!empty){
                // Submit Transaction, Volley
            }
        }

    }
}
