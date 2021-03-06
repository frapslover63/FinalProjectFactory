package id.ac.umn.finalprojectfactory

import android.app.DatePickerDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_laporan_date_picker.*
import java.util.*

class LaporanDatePickerActivity : AppCompatActivity() {

    lateinit var tipe: String
    var bool: Boolean = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laporan_date_picker)

        tipe = intent.getStringExtra("tipe")

        val cFrom = Calendar.getInstance()
        val yearFrom = cFrom.get(Calendar.YEAR)
        val monthFrom = cFrom.get(Calendar.MONTH)
        val dayFrom = cFrom.get(Calendar.DAY_OF_MONTH)

        val cTill = Calendar.getInstance()
        val yearTill = cTill.get(Calendar.YEAR)
        val monthTill = cTill.get(Calendar.MONTH)
        val dayTill = cTill.get(Calendar.DAY_OF_MONTH)

        var tanggal1: Boolean = false
        var tanggal2: Boolean = false

        var tahunDari: Int = 0
        var bulanDari: Int = 0
        var hariDari: Int = 0

        var tahunSampai: Int = 0
        var bulanSampai: Int = 0
        var hariSampai: Int = 0

        txt_DariTanggal.setOnClickListener(){
            val datePickerDari = DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, InputYearFrom, InputMonthFrom, InputDayOfMonthFrom ->
                txt_DariTanggal.setText(""+InputYearFrom+"/"+(InputMonthFrom+1)+"/"+InputDayOfMonthFrom)
                tahunDari = InputYearFrom
                bulanDari = InputMonthFrom+1
                hariDari = InputDayOfMonthFrom
                tanggal1 = true
            },yearFrom,monthFrom,dayFrom)
            datePickerDari.show()
        }

        txt_SampaiTanggal.setOnClickListener(){
            val datePickerSampai = DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, InputYearTill, InputMonthTill, InputDayOfMonthTill ->
                txt_SampaiTanggal.setText(""+InputYearTill+"/"+(InputMonthTill+1)+"/"+InputDayOfMonthTill)
                tahunSampai = InputYearTill
                bulanSampai = InputMonthTill+1
                hariSampai = InputDayOfMonthTill
                tanggal2 = true
            },yearTill,monthTill,dayTill)
            datePickerSampai.show()
        }

        btnSearch.setOnClickListener(){
            if(tahunSampai>=tahunDari){
                if(tahunSampai==tahunDari){
                    if(bulanSampai>=bulanDari){
                        if(bulanSampai==bulanDari){
                            bool=true
                            if(hariSampai>=hariDari){
                                bool = true;
                            }
                            else{
                                bool = false;
                            }
                        }
                    }
                }
                else{
                    bool = true;
                }
            }

            if(tanggal1 && tanggal2&& bool){
                val intent = Intent(this@LaporanDatePickerActivity, ListLaporanActivity::class.java)
                intent.putExtra("tahunDari", tahunDari.toString())
                intent.putExtra("bulanDari", bulanDari.toString())
                intent.putExtra("hariDari", hariDari.toString())
                intent.putExtra("tahunSampai", tahunSampai.toString())
                intent.putExtra("bulanSampai", bulanSampai.toString())
                intent.putExtra("hariSampai", hariSampai.toString())
                intent.putExtra("tipe", tipe)
                startActivity(intent)
            }

            if(!tanggal1 || !tanggal2){
                Snackbar.make(it, "Tanggal masih ada yang kosong", Snackbar.LENGTH_SHORT).show()
            }
            if(!bool){
                Snackbar.make(it, "Tanggal dari tidak boleh melebihi tanggal sampai", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}
