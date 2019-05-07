package id.ac.umn.finalprojectfactory

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail_item_toko.*

class DetailItemTokoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_item_toko)

        txtview_KodeSendal.text = intent.getStringExtra("produkid");
        txtview_Ukuran.text = intent.getStringExtra("ukuran");
        txtview_Warna.text = intent.getStringExtra("warna");
        txtview_CurrentStockToko.text = intent.getStringExtra("jumlah");
    }
}
