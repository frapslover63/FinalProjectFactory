package id.ac.umn.finalprojectfactory

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_transaksi_pabrik.*

class TransaksiPabrikActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaksi_pabrik)

        fabNext.setOnClickListener{
            val intent = Intent(this@TransaksiPabrikActivity, TransaksiPabrikConfirmActivity::class.java)
            startActivity(intent)
        }
    }
}
