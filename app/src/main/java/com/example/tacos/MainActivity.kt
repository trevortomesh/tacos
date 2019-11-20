package com.example.tacos

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    companion object{val REQUEST_IMAGE_CAPTURE = 1 }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val filename = "tacoInstructions.txt"
        cameraButton.setOnClickListener {

            dispatchTakePictureIntent()


        }
        add.setOnClickListener {
            var fileContents = "\n" + editText.text.toString()

            openFileOutput(filename, Context.MODE_APPEND).use{
                it.write(fileContents.toByteArray())
            }




            editText.text = null
            val menu = openFileInput(filename).reader()
            giveMeTaco.text = menu.readText()

        }



    }

    private fun dispatchTakePictureIntent(){
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also{
            takePictureIntent ->
                takePictureIntent.resolveActivity(packageManager)?.also{
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if( requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK){
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
        }
    }


    }


