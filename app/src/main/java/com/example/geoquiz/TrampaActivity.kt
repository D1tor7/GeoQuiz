package com.example.geoquiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.geoquiz.databinding.ActivityTrampaBinding



class TrampaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTrampaBinding
    var rptaCorrecta=false

    companion object{
        val EXTRA_RPTA_MOSTRADA="com.example.geoquiz_rpta_mostrada"
        val EXTRA_RPTA_CORRECTA="com.example,mainactivity"
        fun nuevoIntent(packageContext:Context,rptaCorrecta:Boolean):Intent{
            val intento=Intent(packageContext,TrampaActivity::class.java)
            intento.putExtra(EXTRA_RPTA_CORRECTA,rptaCorrecta)
            return  intento
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityTrampaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rptaCorrecta=getIntent().getBooleanExtra(EXTRA_RPTA_CORRECTA,false)

        binding.botonMostrar.setOnClickListener{
            binding.tvRespuesta.text=if (rptaCorrecta){
                getString(R.string.afirmacion_correcta)}else{
                getString(R.string.afirmacion_incorrecta)
            }
            setResultPreguntaMostrada(true)
        }
    }

    private fun setResultPreguntaMostrada(fueMostrada:Boolean){
        val data=Intent()
        data.putExtra(EXTRA_RPTA_MOSTRADA,fueMostrada)
        setResult(RESULT_OK,data)
    }







}