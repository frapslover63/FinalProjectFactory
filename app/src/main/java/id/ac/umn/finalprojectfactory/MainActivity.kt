package id.ac.umn.finalprojectfactory

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cardviewStockBaru.setOnClickListener {
            val intent = Intent(this@MainActivity, NewStockActivity::class.java)
            startActivity(intent)
        }

        cardviewStock.setOnClickListener {
            val intent = Intent(this@MainActivity, UpdateStockActivity::class.java)
            startActivity(intent)
        }

        cardviewLaporan.setOnClickListener {
            val intent = Intent(this@MainActivity, LaporanActivity::class.java)
            startActivity(intent)
        }

        cardviewListBarang.setOnClickListener {
            val intent = Intent(this@MainActivity, ListBarangActivity::class.java)
            startActivity(intent)
        }
    }
}
