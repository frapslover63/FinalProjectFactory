package id.ac.umn.finalprojectfactory

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.widget.Toast
import data.Transaction
import kotlinx.android.synthetic.main.activity_transaksi_pabrik.*

class TransaksiPabrikActivity : AppCompatActivity() {

    var transactionList: ArrayList<Transaction> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaksi_pabrik)

        fabAdd.setOnClickListener {
            var empty:Boolean = false
            var warning:String = ""
            if(edt_produkid.text.isNullOrEmpty() || edt_Ukuran.text.isNullOrEmpty() || edt_Warna.text.isNullOrEmpty() || edt_Jumlah.text.isNullOrEmpty() || edt_HargaBeli.text.isNullOrEmpty()){
                empty = true
                if(edt_produkid.text.isEmpty()){
                    warning += "Kode Sendal, "
                }
                if(edt_Ukuran.text.isEmpty()){
                    warning += "Ukuran, "
                }
                if(edt_Warna.text.isEmpty()){
                    warning += "Warna, "
                }
                if(edt_Jumlah.text.isEmpty()){
                    warning += "Jumlah, "
                }
                if(edt_HargaBeli.text.isEmpty()){
                    warning += "Harga, "
                }
                warning += "masih kosong!"
                Snackbar.make(it, warning, Snackbar.LENGTH_SHORT).show()
            }

            if(!empty){
                val produkid:String = edt_produkid.text.toString()
                val ukuran:Int = edt_Ukuran.text.toString().toInt()
                val warna:String = edt_Ukuran.text.toString()
                val jumlah:Int = edt_Jumlah.text.toString().toInt()
                val harga:Int = edt_HargaBeli.text.toString().toInt()

                val transaction: Transaction = Transaction(1, jumlah, warna, ukuran, produkid, harga)
                transactionList.add(transaction)
            }
        }

        fabNext.setOnClickListener{
            val intent = Intent(this@TransaksiPabrikActivity, TransaksiPabrikConfirmActivity::class.java)
            startActivity(intent)
        }
    }
}
