package id.ac.umn.finalprojectfactory

import android.app.DatePickerDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_laporan_date_picker.*
import java.util.*

class LaporanDatePickerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laporan_date_picker)

        val cFrom = Calendar.getInstance()
        val yearFrom = cFrom.get(Calendar.YEAR)
        val monthFrom = cFrom.get(Calendar.MONTH)
        val dayFrom = cFrom.get(Calendar.DAY_OF_MONTH)

        val cTill = Calendar.getInstance()
        val yearTill = cTill.get(Calendar.YEAR)
        val monthTill = cTill.get(Calendar.MONTH)
        val dayTill = cTill.get(Calendar.DAY_OF_MONTH)


        btnSearch.setOnClickListener(){
            val intent = Intent(this@LaporanDatePickerActivity, ListLaporanActivity::class.java)
            startActivity(intent)
        }
        txt_DariTanggal.setOnClickListener(){
            val datePickerDari = DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, InputYearFrom, InputMonthFrom, InputDayOfMonthFrom ->
                txt_DariTanggal.setText(""+InputYearFrom+"/"+InputMonthFrom+"/"+InputDayOfMonthFrom)
            },yearFrom,monthFrom,dayFrom)
            datePickerDari.show()
        }
        txt_SampaiTanggal.setOnClickListener(){
            val datePickerSampai = DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, InputYearTill, InputMonthTill, InputDayOfMonthTill ->
                txt_SampaiTanggal.setText(""+InputYearTill+"/"+InputMonthTill+"/"+InputDayOfMonthTill)
            },yearTill,monthTill,dayTill)
            datePickerSampai.show()
        }
    }
}
