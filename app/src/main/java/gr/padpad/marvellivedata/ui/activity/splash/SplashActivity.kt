package gr.padpad.marvellivedata.ui.activity.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import gr.padpad.marvellivedata.R
import gr.padpad.marvellivedata.ui.activity.dashboard.DashboardActivity


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initLayout()
    }

    private fun initLayout() {
        Handler().postDelayed({
            startActivity(Intent(this, DashboardActivity::class.java))
        }, 2000)
    }


}
