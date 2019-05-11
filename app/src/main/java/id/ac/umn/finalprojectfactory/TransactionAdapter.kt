package id.ac.umn.finalprojectfactory

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import data.Transaction

class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {
    var transactionList: ArrayList<Transaction>;
    var context: Context;

    constructor(tempList: ArrayList<Transaction>, context: Context){
        this.transactionList = tempList;
        this.context = context;
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TransactionViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val layout: LayoutInflater = LayoutInflater.from(p0.context)
        val view: View = layout.inflate(R.layout.product_transaction, p0, false)
        return TransactionViewHolder(view)
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return transactionList.size
    }

    override fun onBindViewHolder(p0: TransactionViewHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        p0.txt
    }


    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val idItem: TextView = itemView.txtview_produkid as TextView
//        val warnaItem: TextView = itemView.txtview_Warna as TextView
//        val ukuranItem: TextView = itemView.txtview_Ukuran as TextView
//        val jumlahItem: TextView = itemView.txtview_Jumlah as TextView
//        val keteranganItem: TextView = itemView.txtview_Keterangan as TextView
//        val parentLayout: LinearLayout = itemView.parent_layout as LinearLayout
//        fun Click(intent: Intent, context: Context){
//            parentLayout.setOnClickListener {
//                context.startActivity(intent)
//            }
//        }
    }
}