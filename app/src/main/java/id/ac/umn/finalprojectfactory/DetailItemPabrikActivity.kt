package id.ac.umn.finalprojectfactory

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detail_item_pabrik.*
import kotlinx.android.synthetic.main.activity_detail_item_pabrik.txtview_KodeSendal
import kotlinx.android.synthetic.main.activity_detail_item_pabrik.txtview_Ukuran
import kotlinx.android.synthetic.main.activity_detail_item_pabrik.txtview_Warna

class DetailItemPabrikActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_item_pabrik)

        val id : String = intent.getStringExtra("produkid")
        val size : String = intent.getStringExtra("ukuran")
        val color: String = intent.getStringExtra("warna")
        val count: String = intent.getStringExtra("jumlah")

        txtview_KodeSendal.text = id
        txtview_Ukuran.text = size
        txtview_Warna.text = color
        txtview_CurrentStockPabrik.text = count

        btn_SetStock.setOnClickListener{
            val asString: EditText = edt_Jumlahpabrik
            if(asString.text.trim().length<=0){
                Toast.makeText(this, "Jumlah Field tidak boleh kosong", Toast.LENGTH_LONG).show()
            }
            else{
                val jumlah: Int = asString.text.toString().toInt()
                sendData(id, size, color, jumlah)
            }

        }
    }

    fun sendData(id: String, size: String, color: String, jumlah: Int){


    }
}
