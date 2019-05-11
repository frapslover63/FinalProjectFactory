package id.ac.umn.finalprojectfactory

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import data.Laporan
import kotlinx.android.synthetic.main.laporan_pabrik.view.*
import kotlinx.android.synthetic.main.product_pabrik.view.*

class LaporanAdapter : RecyclerView.Adapter<LaporanAdapter.LaporanViewHolder> {

    private var laporanList: ArrayList<Laporan> = ArrayList()
    private var context: Context
    private var tipeDetail: String = ""

    constructor(dataList: ArrayList<Laporan>, context: Context){
        this.laporanList = dataList
        this.context = context
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): LaporanViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(p0.context)
        val view: View = layoutInflater.inflate(R.layout.product_pabrik, p0, false)
        return LaporanViewHolder(view)
    }

    override fun getItemCount(): Int {
        return laporanList.size
    }

    fun FilterList(filteredList: ArrayList<Laporan>){
        updateList(filteredList)
    }

    override fun onBindViewHolder(p0: LaporanViewHolder, p1: Int) {
        p0.transactionId.text = "Product ID : " + laporanList.get(p1).transactionId
        p0.tanggal.text = "Warna : " + laporanList.get(p1).tanggal
        p0.namaCompany.text = "Ukuran : " + laporanList.get(p1).namaCompany
        p0.totalHarga.text = "Jumlah : " + laporanList.get(p1).totalHarga
        var intent: Intent = Intent()

        if(tipeDetail.equals("toko")){
            intent = Intent(context, DetailItemTokoActivity::class.java)
        }
        else if(tipeDetail.equals("pabrik")){
            intent = Intent(context, DetailItemPabrikActivity::class.java)
        }
        intent.putExtra("produkid", laporanList.get(p1).transactionId)
        intent.putExtra("warna", laporanList.get(p1).tanggal)
        intent.putExtra("ukuran", laporanList.get(p1).namaCompany)
        intent.putExtra("jumlah", laporanList.get(p1).totalHarga)
        //intent.putExtras(pass)
        p0.Click(intent, context)
    }

    fun updateList(newList: ArrayList<Laporan>, tipe: String){
        if(newList.size > 0){
            tipeDetail = tipe
            laporanList = ArrayList()
            laporanList.addAll(newList)
            notifyDataSetChanged()
        }
    }

    fun updateList(newList: ArrayList<Laporan>){
        if(newList.size > 0){
            laporanList = ArrayList()
            laporanList.addAll(newList)
            notifyDataSetChanged()
        }
    }

    class LaporanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val transactionId: TextView = itemView.txtview_detailtranspabrikid
        val tanggal: TextView = itemView.txtview_Tanggal
        val namaCompany: TextView = itemView.txtview_CompanyName
        val totalHarga: TextView = itemView.txtview_Totalharga
        val parentLayout: LinearLayout = itemView.parent_layout
        fun Click(intent: Intent, context: Context){
            parentLayout.setOnClickListener {
                context.startActivity(intent)
            }
        }

    }
}