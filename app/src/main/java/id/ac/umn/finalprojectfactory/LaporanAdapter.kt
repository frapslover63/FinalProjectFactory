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
import data.LaporanToko
import kotlinx.android.synthetic.main.laporan_pabrik.view.*
import kotlinx.android.synthetic.main.product_pabrik.view.*

class LaporanAdapter : RecyclerView.Adapter<LaporanAdapter.LaporanViewHolder> {

    private var laporanList: ArrayList<Laporan> = ArrayList()
    private var context: Context
    private var tipeDetail: String = ""
    var intent: Intent = Intent()

    private var data: Int = 1 ;

    constructor(dataList: ArrayList<Laporan>, context: Context, type: String){
        this.laporanList = dataList
        this.context = context
        this.tipeDetail = type
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): LaporanViewHolder{
        val layoutInflater: LayoutInflater = LayoutInflater.from(p0.context)
        val view: View = layoutInflater.inflate(R.layout.laporan_pabrik, p0, false)
        return LaporanViewHolder(view)
    }

    override fun getItemCount(): Int {
        return laporanList.size
    }

    fun FilterList(filteredList: ArrayList<Laporan>){
        updateList(filteredList)
    }

    override fun onBindViewHolder(p0: LaporanViewHolder, p1: Int) {
        p0.transactionId.text = "Transaction ID" + laporanList.get(p1).transactionId
        p0.tanggal.text = laporanList.get(p1).tanggal
        p0.namaCompany.text = laporanList.get(p1).namaCompany
        p0.totalHarga.text = laporanList.get(p1).totalHarga.toString()

        intent = Intent(context, DetailTransaksiPabrikActivity::class.java)
        intent. putExtra("ID", laporanList.get(p1).transactionId.toString())

        intent.putExtra("produkid", laporanList.get(p1).transactionId)
        intent.putExtra("warna", laporanList.get(p1).tanggal)
        intent.putExtra("ukuran", laporanList.get(p1).namaCompany)
        intent.putExtra("jumlah", laporanList.get(p1).totalHarga)
        //intent.putExtras(pass)
        p0.Click(intent, context)
    }

//    fun updateList(newList: ArrayList<Laporan>, tipe: String){
//        if(newList.size > 0){
//            tipeDetail = tipe
//            laporanList = ArrayList()
//            laporanList.addAll(newList)
//            notifyDataSetChanged()
//        }
//    }

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
        val parentLayout: LinearLayout = itemView.layout_papa
        fun Click(intent: Intent, context: Context){
            parentLayout.setOnClickListener {
                context.startActivity(intent)
            }
        }

    }
}