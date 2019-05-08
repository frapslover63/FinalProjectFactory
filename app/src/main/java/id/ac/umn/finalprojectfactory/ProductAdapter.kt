package id.ac.umn.finalprojectfactory

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import data.Product
import kotlinx.android.synthetic.main.product_pabrik.view.*
import kotlinx.android.synthetic.main.product_toko.view.*
import kotlinx.android.synthetic.main.product_toko.view.txtview_Jumlah
import kotlinx.android.synthetic.main.product_toko.view.txtview_Keterangan
import kotlinx.android.synthetic.main.product_toko.view.txtview_Ukuran
import kotlinx.android.synthetic.main.product_toko.view.txtview_Warna
import kotlinx.android.synthetic.main.product_toko.view.txtview_produkid


class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private var dataList: ArrayList<Product>
    private var context: Context
    private var tipeDetail: String = ""

    constructor(dataList: ArrayList<Product>, context: Context){
        this.dataList = dataList
        this.context = context
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ProductAdapter.ProductViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(p0.context)
        val view: View = layoutInflater.inflate(R.layout.product_pabrik, p0, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun FilterList(filteredList: ArrayList<Product>){
        updateList(filteredList)
    }

    override fun onBindViewHolder(p0: ProductViewHolder, p1: Int) {
        p0.idItem.text = "Product ID : " + dataList.get(p1).produkId
        p0.warnaItem.text = "Warna : " + dataList.get(p1).warna
        p0.ukuranItem.text = "Ukuran : " + dataList.get(p1).ukuran
        p0.jumlahItem.text = "Jumlah : " + dataList.get(p1).jumlah
        var intent: Intent = Intent()

//        val pass: Bundle = Bundle()
//        pass.putString("produkid", dataList.get(p1).produkId)
//        pass.putString("warna", dataList.get(p1).warna)
//        pass.putString("ukuran", dataList.get(p1).ukuran.toString())
//        pass.putString("jumlah", dataList.get(p1).jumlah.toString())

        if(tipeDetail.equals("toko")){
            intent = Intent(context, DetailItemTokoActivity::class.java)
        }
        else if(tipeDetail.equals("pabrik")){
            intent = Intent(context, DetailItemPabrikActivity::class.java)
        }
        intent.putExtra("produkid", dataList.get(p1).produkId)
        intent.putExtra("warna", dataList.get(p1).warna)
        intent.putExtra("ukuran", dataList.get(p1).ukuran.toString())
        intent.putExtra("jumlah", dataList.get(p1).jumlah.toString())
        //intent.putExtras(pass)
        p0.Click(intent, context)
    }

    fun updateList(newList: ArrayList<Product>, tipe: String){
        tipeDetail = tipe
        dataList = ArrayList()
        dataList.addAll(newList)
        notifyDataSetChanged()
    }

    fun updateList(newList: ArrayList<Product>){
        dataList = ArrayList()
        dataList.addAll(newList)
        notifyDataSetChanged()
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val idItem: TextView = itemView.findViewById(R.id.txtview_produkid) as TextView
        val idItem: TextView = itemView.txtview_produkid as TextView
        val warnaItem: TextView = itemView.txtview_Warna as TextView
        val ukuranItem: TextView = itemView.txtview_Ukuran as TextView
        val jumlahItem: TextView = itemView.txtview_Jumlah as TextView
        val keteranganItem: TextView = itemView.txtview_Keterangan as TextView
        val parentLayout: LinearLayout = itemView.parent_layout as LinearLayout
        fun Click(intent: Intent, context: Context){
            parentLayout.setOnClickListener {
                context.startActivity(intent)
            }
        }

    }
}