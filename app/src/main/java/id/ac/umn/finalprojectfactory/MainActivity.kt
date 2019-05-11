package id.ac.umn.finalprojectfactory

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cardviewBarangBaru.setOnClickListener {
            val intent = Intent(this@MainActivity, NewStockOption::class.java)
            startActivity(intent)
        }
        cardviewCekBarang.setOnClickListener {
            val intent = Intent(this@MainActivity, CekBarangActivity::class.java)
            startActivity(intent)
        }
        cardviewLaporan.setOnClickListener {
            val intent = Intent(this@MainActivity, LaporanActivity::class.java)
            startActivity(intent)
        }
        cardviewTransaksi.setOnClickListener {
            val intent = Intent(this@MainActivity, TransaksiPabrikActivity::class.java)
            startActivity(intent)
        }
    }
}
