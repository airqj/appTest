package a0x00f.com.apptest

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.view.View.OnClickListener
import android.view.View
import android.widget.ImageButton
import a0x00f.com.apptest.CustomView
import android.os.Handler

class MainActivity : AppCompatActivity(),OnClickListener {

    private var btn:ImageButton? = null
    private var customView:CustomView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        setContentView(R.layout.activity_main)

        btn = findViewById(R.id.imgBtn)
        customView = findViewById(R.id.myView)
        btn?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.imgBtn) {
            System.out.println("Click")
            customView?.hadle?.postDelayed(customView,1000)
        }
    }
}
