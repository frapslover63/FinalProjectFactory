package id.ac.umn.finalprojectfactory

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_laporan.*

class LaporanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laporan)

        cardviewLaporanToko.setOnClickListener {
            val intent = Intent(this@LaporanActivity, LaporanDatePickerActivity::class.java)
            intent.putExtra("tipe", "toko")
            startActivity(intent)
        }
        cardviewLaporanPabrik.setOnClickListener{
            val intent = Intent(this@LaporanActivity, LaporanDatePickerActivity::class.java)
            intent.putExtra("tipe", "pabrik")
            startActivity(intent)
        }
    }
}
