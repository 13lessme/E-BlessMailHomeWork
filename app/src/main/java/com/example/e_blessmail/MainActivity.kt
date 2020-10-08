package com.example.e_blessmail

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
private var uri:Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSend.setOnClickListener { sendMail() }

        getphoto.setOnClickListener {
            val intent1 = Intent(Intent.ACTION_PICK)
            intent1.type = "image/*"
            startActivityForResult(intent1, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == 1){
        uri = data?.data
            KatySuper.setImageURI(uri)
        }
    }
    private fun sendMail(){

        val recipientList: String = edtTo.text.toString()
        val recipients = recipientList.split(",").toTypedArray()

        val subject: String = edtSubject.text.toString()
        val message: String = edtMessage.text.toString()

        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("image/*")
        intent.putExtra(Intent.EXTRA_EMAIL, recipients)
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, message)
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        intent.type = "image/png"

        startActivity(Intent.createChooser(intent, "Выберите Email клиент: "))
    }
}
