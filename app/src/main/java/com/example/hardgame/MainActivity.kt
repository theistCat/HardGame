package com.example.hardgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.hardgame.databinding.ActivityMainBinding
import java.lang.IndexOutOfBoundsException
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listOfLines = parserData()
        var ss = 0

        val array = arrayOf(
            "Прогуливаясь по лесу в столь замечательную солнечную погоду, вы замечаете какое-то движение в кустах. Ваши действия?",
            "1 Кину камень в кусты и буду на стороже",
            "2 Пойду и гляну что там",
            "3 Убегу прочь пока на меня не напали"
        )

        var text = array[0]
        var btn1 = array[1]
        var btn2 = array[2]
        var btn3 = array[3]

        fun inLogic1(temper: String) {

            val array1 = arrayListOf<String>()
            for (i in listOfLines) {
                if (i.startsWith(temper, 0, true)) {
                    Log.i("myTest", i)
                    array1.add(i)
                }
            }

            val array2 = arrayListOf(array1[0], array1[1], array1[2], array1[3])

            text = array2[0]
            btn1 = array2[1]
            btn2 = array2[2]
            btn3 = array2[3]

            binding.tv.text = text.filter { it != '/' && !it.isDigit() }
            binding.ch1.text = btn1.filter { it != '/' && !it.isDigit() }
            binding.ch2.text = btn2.filter { it != '/' && !it.isDigit() }
            binding.ch3.text = btn3.filter { it != '/' && !it.isDigit() }
            Log.i("myTest", array1.size.toString())
        }

        fun inLogic2(temper: String) {
            val array1 = arrayListOf<String>()
            try {

                for (i in listOfLines) {
                    if (i.startsWith(temper, 0, true)) {
                        Log.i("myTest", i)
                        array1.add(i)
                    }
                }

                val array2 = arrayListOf(array1[1], array1[2], array1[3], array1[4])
                text = array2[0]
                btn1 = array2[1]
                btn2 = array2[2]
                btn3 = array2[3]

                binding.tv.text = text.filter { it != '/' && !it.isDigit() }
                binding.ch1.text = btn1.filter { it != '/' && !it.isDigit() }
                binding.ch2.text = btn2.filter { it != '/' && !it.isDigit() }
                binding.ch3.text = btn3.filter { it != '/' && !it.isDigit() }
                Log.e("myTest", array1.size.toString())

            } catch (e: IndexOutOfBoundsException) {
                binding.tv.text = "на этом пока все))) ждите продолжения..."
                binding.ch1.visibility = View.INVISIBLE
                binding.ch2.visibility = View.INVISIBLE
                binding.ch3.text = "выход"
                binding.ch3.setOnClickListener {
                    finish()
                    exitProcess(0)
                }
            }
        }

        binding.tv.text = text
        binding.ch1.text = btn1.filter { it != '/' && !it.isDigit() }
        binding.ch2.text = btn2.filter { it != '/' && !it.isDigit() }
        binding.ch3.text = btn3.filter { it != '/' && !it.isDigit() }

        binding.ch1.setOnClickListener()
        {
            val temper =
                btn1.filter { !it.isLetter() && !it.isWhitespace() && it != '?' && it != ',' && it != '-' }
            Log.d("myTest", temper)
            ss++
            if (ss == 1) inLogic1(temper) else inLogic2(temper)
        }

        binding.ch2.setOnClickListener()
        {
            val temper =
                btn2.filter { !it.isLetter() && !it.isWhitespace() && it != '?' && it != ',' && it != '-' }
            Log.d("myTest", temper)
            ss++
            if (ss == 1) inLogic1(temper) else inLogic2(temper)
        }
        binding.ch3.setOnClickListener()
        {
            val temper =
                btn3.filter { !it.isLetter() && !it.isWhitespace() && it != '?' && it != ',' && it != '-' }
            Log.d("myTest", temper)
            ss++
            if (ss == 1) inLogic1(temper) else inLogic2(temper)
        }
    }

    private fun parserData(): List<String> =
        requireNotNull(this).application.applicationContext.assets.open("game.txt")
            .bufferedReader().use { it.readText() }.split("\n")
}