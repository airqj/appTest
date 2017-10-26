package a0x00f.com.apptest

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.WindowManager
import java.io.InputStream
import java.net.URL
import java.util.logging.Handler

/**
 * Created by qinjianbo on 17-10-26.
 */

class CustomView(context: Context) : View(context),Runnable {

    var paint: Paint = Paint()
    var vy: Int = 0
    private val imgURL = "http://ww1.sinaimg.cn/mw690/005Fj2RDgw1f9mvl4pivvj30c82ougw3.jpg"
    var hadle = android.os.Handler()
    /*
    init {
        hadle.postDelayed(this,100)
    }
    */
    constructor(context: Context, attributeSet: AttributeSet):this(context) {}

    var wm: WindowManager = getContext().getSystemService(
            Context.WINDOW_SERVICE) as WindowManager

    var width0 = wm.defaultDisplay.width
    var height0 = wm.defaultDisplay.height
    val inputIs: InputStream = URL(imgURL).openStream()
    val bmp = BitmapFactory.decodeStream(inputIs)

    fun resizeBitmap(bitmap: Bitmap?, w: Int, h: Int, y:Int): Bitmap? {
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

    //在这里我们将测试canvas提供的绘制图形方法
    override fun onDraw(canvas: Canvas) {

        val rectF: RectF = RectF(0F,0F,canvas?.width.toFloat(),canvas?.height.toFloat())
        vy++
        val bitmap = resizeBitmap(bmp,width0,height0,vy)
        canvas.drawBitmap(bitmap,null,rectF,null)
    }

    override fun run() {
        this.invalidate()
        hadle.postDelayed({hadle.postDelayed(this,1000)},1000)
    }
}