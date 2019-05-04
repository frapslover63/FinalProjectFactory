package id.ac.umn.finalprojectfactory

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_laporan.*
import kotlinx.android.synthetic.main.activity_main.*

class LaporanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laporan)

        cardviewLaporanToko.setOnClickListener {
            val intent = Intent(this@LaporanActivity, ListLaporanActivity::class.java)
            startActivity(intent)
        }
        cardviewLaporanPabrik.setOnClickListener{
            val intent = Intent(this@LaporanActivity, ListLaporanActivity::class.java)
            startActivity(intent)
        }
    }
}
