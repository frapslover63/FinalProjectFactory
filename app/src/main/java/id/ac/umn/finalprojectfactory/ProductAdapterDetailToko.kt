package id.ac.umn.finalprojectfactory

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import data.Product
import kotlinx.android.synthetic.main.product_pabrik_laporan.view.*
import kotlinx.android.synthetic.main.product_toko_laporan.view.*

class ProductAdapterDetailToko : RecyclerView.Adapter<ProductAdapterDetailToko.ProductDetailTokoViewHolder> {
    private var dataList: ArrayList<Product>
    private var context: Context
    private var tipeDetail: String = ""

    constructor(dataList: ArrayList<Product>, context: Context){
        this.dataList = dataList
        this.context = context
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ProductDetailTokoViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(p0.context)
        val view: View = layoutInflater.inflate(R.layout.product_toko_laporan, p0, false)
        return ProductDetailTokoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun FilterList(filteredList: ArrayList<Product>){
        updateList(filteredList)
    }

    override fun onBindViewHolder(p0: ProductDetailTokoViewHolder, p1: Int) {
        p0.idItem.text = dataList.get(p1).produkId
        p0.warna.text = dataList.get(p1).warna
        p0.ukuran.text = dataList.get(p1).ukuran.toString()
        p0.jumlah.text = dataList.get(p1).jumlah.toString()
        p0.harga.text = dataList.get(p1).hargaLaporan.toString()
    }

    fun updateList(newList: ArrayList<Product>, tipe: String){
        if(newList.size > 0){
            tipeDetail = tipe
            dataList = ArrayList()
            dataList.addAll(newList)
            notifyDataSetChanged()
        }
    }

    fun updateList(newList: ArrayList<Product>){
        if(newList.size > 0){
            dataList = ArrayList()
            dataList.addAll(newList)
            notifyDataSetChanged()
        }
    }

    class ProductDetailTokoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idItem: TextView = itemView.txtview_produkid_laporan2
        val ukuran: TextView = itemView.txtview_Ukuran_laporan2
        val warna: TextView = itemView.txtview_Warna_laporan2
        val jumlah: TextView = itemView.txtview_Jumlah_laporan2
        val harga: TextView = itemView.txtview_HargaJual_laporan2
        val parentLayout: LinearLayout = itemView.parent_layout2
//        fun Click(intent: Intent, context: Context){
//            parentLayout.setOnClickListener {
//                context.startActivity(intent)
//            }
//        }

    }
}