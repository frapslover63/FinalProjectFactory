package id.ac.umn.finalprojectfactory

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail_item_pabrik.*
import kotlinx.android.synthetic.main.activity_detail_item_pabrik.txtview_KodeSendal
import kotlinx.android.synthetic.main.activity_detail_item_pabrik.txtview_Ukuran
import kotlinx.android.synthetic.main.activity_detail_item_pabrik.txtview_Warna
import kotlinx.android.synthetic.main.activity_detail_item_toko.*

class DetailItemPabrikActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_item_pabrik)

        txtview_KodeSendal.text = intent.getStringExtra("produkid");
        txtview_Ukuran.text = intent.getStringExtra("ukuran");
        txtview_Warna.text = intent.getStringExtra("warna");
        txtview_CurrentStockPabrik.text = intent.getStringExtra("jumlah");
    }
}
