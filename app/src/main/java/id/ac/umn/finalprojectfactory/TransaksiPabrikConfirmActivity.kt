package id.ac.umn.finalprojectfactory

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import kotlinx.android.synthetic.main.activity_transaksi_pabrik.*
import kotlinx.android.synthetic.main.activity_transaksi_pabrik_conf.*

class TransaksiPabrikConfirmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaksi_pabrik_conf)

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
