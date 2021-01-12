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
        val paint= Paint()
        val output= Bitmap.createBitmap(1254,758,Bitmap.Config.ARGB_8888)
        val canvas= Canvas(output)

        paint.isAntiAlias=true

        canvas.drawBitmap(BitmapFactory.decodeResource(resources,R.drawable.frame1),0f,0f,paint)

        canvas.drawBitmap(BitmapFactory.decodeResource(resources,R.drawable.icon),1f,1f,paint)

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