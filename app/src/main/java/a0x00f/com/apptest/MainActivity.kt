package a0x00f.com.apptest

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.view.View
import java.io.InputStream
import java.net.URL
import android.view.WindowManager
import android.graphics.Bitmap
import android.os.Handler


class MainActivity : AppCompatActivity() {

    private val imgURL = "http://ww1.sinaimg.cn/mw690/005Fj2RDgw1f9mvl4pivvj30c82ougw3.jpg"
    private var y:Int = 0
    private var hadle = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        setContentView(CustomView1(this))
        //setContentView(R.layout.activity_main)
    }

    fun resizeBitmap(bitmap: Bitmap?, w: Int, h: Int): Bitmap? {
        if (bitmap != null) {
            val width = bitmap.width
            val height = bitmap.height
            val scaleWight = w.toFloat() / width
            val scaleHeight = h.toFloat() / height
            val matrix = Matrix()
            matrix.postScale(scaleWight, scaleHeight)
            return Bitmap.createBitmap(bitmap, 0, y, width, 400, matrix, true)

        } else {
            return null
        }
    }

    /**
     * 使用内部类 自定义一个简单的View
     * @author Administrator
     */
    internal inner class CustomView1(context: Context) : View(context),Runnable {

        var paint: Paint

        init {
            paint = Paint() //设置一个笔刷大小是3的黄色的画笔
            paint.color = Color.YELLOW
            paint.strokeJoin = Paint.Join.ROUND
            paint.strokeCap = Paint.Cap.ROUND
            paint.strokeWidth = 3f
            this.handler.postDelayed(this,500)
        }

        var wm: WindowManager = getContext().getSystemService(
                Context.WINDOW_SERVICE) as WindowManager

        var width0 = wm.defaultDisplay.width
        var height0 = wm.defaultDisplay.height
        val inputIs: InputStream = URL(imgURL).openStream()
        val bmp = BitmapFactory.decodeStream(inputIs)


        //在这里我们将测试canvas提供的绘制图形方法
        override fun onDraw(canvas: Canvas) {

            val rectF: RectF = RectF(0F,0F,canvas?.width.toFloat(),canvas?.height.toFloat())
            val bitmap = resizeBitmap(bmp,width0,height0)
            canvas.drawBitmap(bitmap,null,rectF,null)
        }

        override fun run() {
            y.plus(1)
            // 重新绘制View
            this.invalidate();
            // 重新设置定时器，在60秒后调用run方法
            this.handler.postDelayed(this, 1000)
        }
    }
}
