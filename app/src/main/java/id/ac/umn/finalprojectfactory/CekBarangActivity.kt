package id.ac.umn.finalprojectfactory

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cek_barang.*

class CekBarangActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cek_barang)
        cardviewBarangToko.setOnClickListener {
            val intent = Intent(this@CekBarangActivity, ListBarangActivity::class.java)
            startActivity(intent)
        }
        cardviewBarangPabrik.setOnClickListener{
            val intent = Intent(this@CekBarangActivity, ListBarangActivity::class.java)
            startActivity(intent)
        }
    }
}
