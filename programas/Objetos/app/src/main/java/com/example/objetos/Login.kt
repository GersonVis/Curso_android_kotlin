package com.example.objetos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class Login : AppCompatActivity() {
    val padre=this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var chEmpresas= findViewById<ChipGroup>(R.id.chEmpresas)
        //recorremos los hijos del childgroup
        for(i in 0..chEmpresas.childCount-1){
            //hacer un cast para acceder a las propiedas de un chip
            var chip:Chip = chEmpresas.getChildAt(i) as Chip
            // añadir evento al icono de cierre
            chip.setOnCloseIconClickListener{
                //removemos del group la vista que tocamos
                chEmpresas.removeView(it)
            }
            chip.setOnClickListener{
                //recast para acceder a los atributos de un chip
                var transicion=it as Chip
                Toast.makeText(this, "${transicion.text}", Toast.LENGTH_SHORT).show()
            }
        }
        //crear un nuevo chip
        var chipNuevo = Chip(this).run {
            text ="otro"
            //agregar icono
            chipIcon = ContextCompat.getDrawable(padre, R.drawable.ic_baseline_email_24)
            //hacer visible icono de cierre
            isCloseIconVisible =true
            //hacer clickeable el elmento
            isClickable =true
            //agregamos el evento anterior
            setOnCloseIconClickListener{
                chEmpresas.removeView(it as View)
            }
            this
        }
        chEmpresas.addView(chipNuevo as View)

        // intaciamos vista con codigo
        var rgConfirmacion= findViewById<RadioGroup>(R.id.rgConfirmacion)
        var rbLlamada= findViewById<RadioButton>(R.id.rbLlamada)

        //seleccionamos el radio button con codigo
        rgConfirmacion.check(rbLlamada.id)

        //referencia a checbox
        var chMsg: CheckBox = findViewById<CheckBox>(R.id.cboxTelefono)

        //marcar seleccionado
        chMsg.isChecked =true

        chMsg.isEnabled =false

        var tgOpcion: ToggleButton = findViewById<ToggleButton>(R.id.tgOpcion)

        //evento de seleccion
        tgOpcion.setOnCheckedChangeListener{ vista, checked->
            if(checked){
                Toast.makeText(this, "ha sido seleccionado", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun onCheckBoxListener(view: View){
        if(view is CheckBox){
            var checked = view.isChecked
            if(checked){
                when(view.id){
                    R.id.cboxMsg->Toast.makeText(this, "Has seleccionado mensaje", Toast.LENGTH_SHORT).show()
                    R.id.cboxTelefono->Toast.makeText(this, "has selccionado llamada", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
    fun onClickRadioButtonLlamada(view: View){
       if(view is RadioButton){
            var checked =view.isChecked
            when(view.id){
                R.id.rbLlamada->{
                    if(checked){
                        Toast.makeText(this, "Registra los numeros que te iremos dando", Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.rbDosPasos->{
                    if(checked){
                        Toast.makeText(this, "Necesitaras registrar el telefono", Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.rbSms->{
                    if(checked){
                        Toast.makeText(this, "Registra el codigo que te hemos enviado por msg", Toast.LENGTH_SHORT).show()
                    }
                }
                else->{
                    println("ninguno")
                }
            }
       }
    }
}