package a0x00f.com.apptest

import android.app.Activity
import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.view.View
import java.io.InputStream
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val imgURL = "http://ww1.sinaimg.cn/mw690/005Fj2RDgw1f9mvl4pivvj30c82ougw3.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(CustomView1(this))

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
    }

    /**
     * 使用内部类 自定义一个简单的View
     * @author Administrator
     */
    internal inner class CustomView1(context: Context) : View(context) {

        var paint: Paint

        init {
            paint = Paint() //设置一个笔刷大小是3的黄色的画笔
            paint.color = Color.YELLOW
            paint.strokeJoin = Paint.Join.ROUND
            paint.strokeCap = Paint.Cap.ROUND
            paint.strokeWidth = 3f
        }

        //在这里我们将测试canvas提供的绘制图形方法
        override fun onDraw(canvas: Canvas) {
            val inputIs: InputStream = URL(imgURL).openStream()

            val tmpOptions = BitmapFactory.Options()
            tmpOptions.inJustDecodeBounds = true
            val bmp = BitmapFactory.decodeStream(inputIs)
            val width = tmpOptions.outWidth
            val height = tmpOptions.outHeight

            val rectF = RectF(0F,0F,canvas?.width.toFloat(),canvas?.height.toFloat())
            val bitmap = Bitmap.createBitmap(bmp,0,0,bmp.width,canvas.height)
            canvas.drawBitmap(bitmap,null,rectF,null)
        }

    }

}
