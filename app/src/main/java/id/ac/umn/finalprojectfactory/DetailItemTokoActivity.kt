package id.ac.umn.finalprojectfactory

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail_item_toko.*

class DetailItemTokoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_item_toko)

        txtview_KodeSendal.text = "Product ID : " + intent.getBundleExtra("produkid").toString()
        txtview_Ukuran.text = intent.getBundleExtra("ukuran").toString()
        txtview_Warna.text = intent.getBundleExtra("warna").toString()
        txtview_CurrentStockToko.text = intent.getBundleExtra("jumlah").toString()
    }
}
