package id.ac.umn.finalprojectfactory

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import data.Laporan
import kotlinx.android.synthetic.main.activity_list_laporan.*

class ListLaporanActivity : AppCompatActivity() {

    var laporanList: ArrayList<Laporan> = ArrayList()
    lateinit var lAdapter: LaporanAdapter
    lateinit var tipe: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_laporan)

        tipe = intent.getStringExtra("tipe")
        val layoutManager = LinearLayoutManager(this)

        recyclerview_laporan.layoutManager = layoutManager
        lAdapter = LaporanAdapter(laporanList, this)
        recyclerview_laporan.adapter = lAdapter
    }
}
