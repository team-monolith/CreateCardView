package com.monolith.createcardview

import android.graphics.*
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import java.io.File
import java.io.FileOutputStream

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    val GLOBAL=MyApp.getInstance()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_first,container,false)
        val layout=view.findViewById<ConstraintLayout>(R.id.constlayout)

        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bitmap_card:Bitmap=CreateCard()


        view.findViewById<ImageView>(R.id.iv_card).setImageBitmap(bitmap_card)
    }

    fun CreateCard():Bitmap{

        //フレームは50pxずつのズレ,サイズを12.54で割るとちょうどフレームサイズの模様

        val img_frame=BitmapFactory.decodeResource(resources,R.drawable.frame1)
        val img_icon=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,R.drawable.icon),(img_frame.height/3),(img_frame.height/3),true)
        val img_badge_back=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,R.drawable.badge_background_4),(img_frame.height/6),(img_frame.height/6),true)
        val img_badge_icon=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,R.drawable.badge_icon_7),(img_frame.height/6),(img_frame.height/6),true)
        val str_id:String=12345.toString()
        val str_name:String="やまだたろう"
        val str_distance:String="1,234,250m"

        val width=img_frame.width
        val height=img_frame.height

        val frameWidth:Float=img_frame.width/12.54f

        val paint= Paint()
        val output= Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888)
        val canvas= Canvas(output)

        paint.isAntiAlias=true

        canvas.drawBitmap(img_frame,0f,0f,paint)

        canvas.drawBitmap(img_icon,((width-frameWidth)/8f)-(img_icon.width/2f)+frameWidth/2,((height-frameWidth)/4f)-(img_icon.height/2f)+frameWidth/2,paint)

        canvas.drawBitmap(img_badge_back,2650f,550f,paint)

        canvas.drawBitmap(img_badge_icon,2650f,550f,paint)

        //※ビューは一度作ったものをリサイズして利用するので、位置は無理やりハードコートしています
        paint.textSize=150f

        /*
        canvas.drawText("ID：$str_id",(width/2)-paint.measureText("ID：$str_id")/2,(height-frameWidth)/4+frameWidth-250,paint)
        canvas.drawText(str_name,(width/2)-paint.measureText(str_name)/2,((height-frameWidth)/4f)+(paint.fontMetrics.top/-2) +frameWidth/2,paint)
        canvas.drawText(str_distance,(width/2)-paint.measureText(str_distance)/2,((height-frameWidth)/4f)+(paint.fontMetrics.top/-2) +frameWidth/2+200,paint)
        */

        canvas.drawText("ID：$str_id",(width-frameWidth)/4+frameWidth/2+125,(height-frameWidth)/4+frameWidth-250,paint)
        canvas.drawText(str_name,(width-frameWidth)/4+frameWidth/2+125,((height-frameWidth)/4f)+(paint.fontMetrics.top/-2) +frameWidth/2,paint)
        canvas.drawText(str_distance,(width-frameWidth)/4*3+frameWidth/2-paint.measureText(str_distance),((height-frameWidth)/4f)+(paint.fontMetrics.top/-2) +frameWidth/2+200,paint)

        paint.color= Color.parseColor("#808080")
        paint.strokeWidth=5f
        //canvas.drawLine(1000f,280f,2400f,280f,paint)
        canvas.drawLine(1000f,480f,2450f,480f,paint)
        canvas.drawLine(1000f,680f,2450f,680f,paint)
        canvas.drawLine(1000f,880f,2450f,880f,paint)

        return output

    }
}