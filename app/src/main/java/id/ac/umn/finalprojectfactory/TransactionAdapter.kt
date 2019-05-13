package id.ac.umn.finalprojectfactory

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import data.Transaction
import kotlinx.android.synthetic.main.product_transaction.view.*

class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {
    var transactionList: ArrayList<Transaction>;
    var context: Context;

    constructor(tempList: ArrayList<Transaction>, context: Context){
        this.transactionList = tempList;
        this.context = context;
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TransactionViewHolder {
        val layout: LayoutInflater = LayoutInflater.from(p0.context)
        val view: View = layout.inflate(R.layout.product_transaction, p0, false)
        return TransactionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

    fun updateList(newList: ArrayList<Transaction>){
        if(newList.size > 0){
            transactionList = ArrayList()
            transactionList.addAll(newList)
            notifyDataSetChanged()
        }
        else{
            transactionList = ArrayList()
            notifyDataSetChanged()
        }
    }

    override fun onBindViewHolder(p0: TransactionViewHolder, p1: Int) {
        p0.idItem.text = transactionList[p1].produkId
        p0.warnaItem.text = transactionList[p1].warna
        p0.jumlahItem.text = transactionList[p1].jumlah.toString()
        p0.ukuranItem.text = transactionList[p1].ukuran.toString()
        p0.hargaItem.text = transactionList[p1].harga.toString()
    }


    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idItem: TextView = itemView.txtview_produkid
        val warnaItem: TextView = itemView.txtview_Warna
        val jumlahItem: TextView = itemView.txtview_Jumlah
        val ukuranItem: TextView = itemView.txtview_Ukuran
        val hargaItem: TextView = itemView.txtview_HargaTransaction
        val parentLayout: LinearLayout = itemView.parent_layout
        fun Click(intent: Intent, context: Context){
            parentLayout.setOnClickListener {
                context.startActivity(intent)
            }
        }
    }
}