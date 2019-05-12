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
import kotlinx.android.synthetic.main.activity_detail_transaksi_toko.view.*
import kotlinx.android.synthetic.main.laporan_pabrik.view.*
import kotlinx.android.synthetic.main.laporan_pabrik.view.txtview_Tanggal
import kotlinx.android.synthetic.main.laporan_pabrik.view.txtview_Totalharga
import kotlinx.android.synthetic.main.laporan_toko.view.*

class LaporanAdapterToko : RecyclerView.Adapter<LaporanAdapterToko.LaporanTokoViewHolder>{

    private var laporanListToko: ArrayList<LaporanToko> = ArrayList()
    private var context: Context
    private var intent: Intent = Intent()

    constructor(laporanListToko: ArrayList<LaporanToko>, context: Context) {
        this.laporanListToko = laporanListToko
        this.context = context
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): LaporanTokoViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(p0.context)
        val view: View = layoutInflater.inflate(R.layout.laporan_toko, p0, false)
        return LaporanTokoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return laporanListToko.size
    }

    override fun onBindViewHolder(p0: LaporanTokoViewHolder, p1: Int) {
        p0.transactionId.text = "Transaction ID" + laporanListToko.get(p1).transactionId
        p0.tanggal.text = laporanListToko.get(p1).tanggal
        p0.status.text = laporanListToko.get(p1).status.toString()
        p0.totalHarga.text = laporanListToko.get(p1).totalHarga.toString()

        intent = Intent(context, DetailTransaksiTokoActivity::class.java)
        intent. putExtra("ID", laporanListToko.get(p1).transactionId.toString())

        intent.putExtra("produkid", laporanListToko.get(p1).transactionId)
        intent.putExtra("warna", laporanListToko.get(p1).tanggal)
        intent.putExtra("ukuran", laporanListToko.get(p1).status)
        intent.putExtra("jumlah", laporanListToko.get(p1).totalHarga)
        //intent.putExtras(pass)
        p0.Click(intent, context)
    }

    fun updateList(newList: ArrayList<LaporanToko>){
        if(newList.size > 0){
            laporanListToko = ArrayList()
            laporanListToko.addAll(newList)
            notifyDataSetChanged()
        }
    }


    class LaporanTokoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val transactionId: TextView = itemView.txtview_transaksitokoid
        val tanggal: TextView = itemView.txtview_Tanggal
        val status: TextView = itemView.txtview_Status
        val totalHarga: TextView = itemView.txtview_Totalharga
        val parentLayout: LinearLayout = itemView.layout_mama
        fun Click(intent: Intent, context: Context){
            parentLayout.setOnClickListener {
                context.startActivity(intent)
            }
        }
    }


}