package com.monolith.createcardview

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
        var test=53
        test+=5
        CreateCard()
    }

    fun CreateCard(){

        //フレームは50pxずつのズレ,サイズを12.54で割るとちょうどフレームサイズの模様

        val img_frame=BitmapFactory.decodeResource(resources,R.drawable.frame1)
        val img_icon=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,R.drawable.icon),(img_frame.height/3),(img_frame.height/3),true)
        val str_id:String=12345.toString()
        val str_name:String="あいうえおかきくけこ"

        val width=img_frame.width
        val height=img_frame.height

        val frameWidth:Float=img_frame.width/12.54f

        val paint= Paint()
        val output= Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888)
        val canvas= Canvas(output)

        paint.isAntiAlias=true

        canvas.drawBitmap(img_frame,0f,0f,paint)

        canvas.drawBitmap(img_icon,((width-frameWidth)/8f)-(img_icon.width/2f)+frameWidth/2,((height-frameWidth)/4f)-(img_icon.height/2f)+frameWidth/2,paint)

        //※ビューを作るだけで、一度作ったものをリサイズして利用するので、位置は無理やりハードコートしています
        paint.textSize=120f
        canvas.drawText("   I D   ：$str_id",(width-frameWidth)/4f+frameWidth/2f+20,(height-frameWidth)/4+frameWidth-250,paint)
        canvas.drawText("NAME：$str_name",(width-frameWidth)/4f+frameWidth/2f,(height-frameWidth)/4+frameWidth+50,paint)


        //ここにブレークポイントを置き、outputの中身をデバッグで見ること。
        //動作させても落ちます（処理未記述のため）
        outputCard(output)

    }

    fun outputCard(output:Bitmap){
        //画像を250x250にリサイズし出力
        val file = File(GLOBAL.DIRECTORY, "icon.png")
        FileOutputStream(file).use { fileOutputStream ->
            output.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream)
            fileOutputStream.flush()
            fileOutputStream.flush()
        }
    }
}