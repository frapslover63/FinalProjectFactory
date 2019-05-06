package id.ac.umn.finalprojectfactory

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_new_stock_option.*
import android.content.Intent;

class NewStockOption : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_stock_option);

        barangBaru.setOnClickListener{
            val intent =  Intent(this@NewStockOption, NewStockActivity::class.java);
            startActivity(intent);
        }

        tambahModel.setOnClickListener{
            val intent =  Intent(this@NewStockOption, NewModelActivity::class.java);
            startActivity(intent);
        }

    }
}
