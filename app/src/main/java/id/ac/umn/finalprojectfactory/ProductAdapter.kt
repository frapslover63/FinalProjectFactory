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
import data.DetailProduct
import data.Product
import kotlinx.android.synthetic.main.product_pabrik.view.*
import kotlinx.android.synthetic.main.product_toko.view.*
import kotlinx.android.synthetic.main.product_toko.view.txtview_Jumlah
import kotlinx.android.synthetic.main.product_toko.view.txtview_Keterangan
import kotlinx.android.synthetic.main.product_toko.view.txtview_Ukuran
import kotlinx.android.synthetic.main.product_toko.view.txtview_Warna
import kotlinx.android.synthetic.main.product_toko.view.txtview_produkid


class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private var dataList: ArrayList<DetailProduct>
    private var context: Context
    private var tipeDetail: String = ""

    constructor(dataList: ArrayList<DetailProduct>, context: Context){
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

    fun FilterList(filteredList: ArrayList<DetailProduct>){
        updateList(filteredList)
    }

    override fun onBindViewHolder(p0: ProductViewHolder, p1: Int) {
        p0.idItem.text = dataList.get(p1).productId
        p0.warnaItem.text = dataList.get(p1).warna
        p0.ukuranItem.text = dataList.get(p1).ukuran.toString()
        p0.jumlahItem.text = dataList.get(p1).jumlah.toString()
        p0.keteranganItem.text = dataList.get(p1).keterangan
        var intent: Intent = Intent()

        if(tipeDetail.equals("toko")){
            intent = Intent(context, DetailItemTokoActivity::class.java)
        }
        else if(tipeDetail.equals("pabrik")){
            intent = Intent(context, DetailItemPabrikActivity::class.java)
        }
        intent.putExtra("produkid", dataList.get(p1).productId)
        intent.putExtra("warna", dataList.get(p1).warna)
        intent.putExtra("ukuran", dataList.get(p1).ukuran.toString())
        intent.putExtra("jumlah", dataList.get(p1).jumlah.toString())
        p0.Click(intent, context)
    }

    fun updateList(newList: ArrayList<DetailProduct>, tipe: String){
        if(newList.size > 0){
            tipeDetail = tipe
            dataList = ArrayList()
            dataList.addAll(newList)
            notifyDataSetChanged()
        }
    }

    fun updateList(newList: ArrayList<DetailProduct>){
        if(newList.size >= 0){
            dataList = ArrayList()
            dataList.addAll(newList)
            notifyDataSetChanged()
        }
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idItem: TextView = itemView.txtview_produkid
        val warnaItem: TextView = itemView.txtview_Warna
        val ukuranItem: TextView = itemView.txtview_Ukuran
        val jumlahItem: TextView = itemView.txtview_Jumlah
        val keteranganItem: TextView = itemView.txtview_Keterangan
        val parentLayout: LinearLayout = itemView.parent_layout
        fun Click(intent: Intent, context: Context){
            parentLayout.setOnClickListener {
                context.startActivity(intent)
            }
        }

    }
}