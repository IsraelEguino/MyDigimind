package eguino.iribe.mydigimind.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import eguino.iribe.mydigimind.Carrito
import eguino.iribe.mydigimind.R
import eguino.iribe.mydigimind.Recordatorio

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    val carrito: Carrito= Carrito()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            val boton = root.findViewById(R.id.boton) as Button

            val monday = root.findViewById(R.id.monday) as CheckBox
            val tuesday = root.findViewById(R.id.tuesday) as CheckBox
            val wednesday = root.findViewById(R.id.wednesday) as CheckBox
            val thursday = root.findViewById(R.id.thursday) as CheckBox
            val friday = root.findViewById(R.id.friday) as CheckBox
            val saturday = root.findViewById(R.id.saturday) as CheckBox
            val sunday = root.findViewById(R.id.sunday) as CheckBox

            val edtText = root.findViewById(R.id.edtText) as EditText
            val edtTime = root.findViewById(R.id.edtTime) as EditText



            boton.setOnClickListener(){
                var dias: String=""
                if (monday.isChecked){
                    dias="Mon "
                }
                if (tuesday.isChecked){
                    dias=dias+"Tue "
                }
                if (wednesday.isChecked){
                    dias=dias+"Wed "
                }
                if (thursday.isChecked){
                    dias=dias+"Thu "
                }
                if (friday.isChecked){
                    dias=dias+"Fri "
                }
                if (saturday.isChecked){
                    dias=dias+"Sat "
                }
                if (sunday.isChecked){
                    dias=dias+"Sun "
                }
                val re: Recordatorio= Recordatorio(dias,edtTime.toString(),edtText.toString())
                carrito.agregar(re)
                limpiar(monday,tuesday,wednesday,thursday,friday,saturday,sunday,edtText,edtTime)
            }
        })
        return root
    }

    fun limpiar(monday: CheckBox,tuesday: CheckBox,wednesday: CheckBox,thursday: CheckBox, friday: CheckBox,
                saturday: CheckBox,sunday: CheckBox,text: EditText,time: EditText){
        monday.setChecked(false)
        tuesday.setChecked(false)
        wednesday.setChecked(false)
        thursday.setChecked(false)
        friday.setChecked(false)
        saturday.setChecked(false)
        sunday.setChecked(false)

        text.setText("")
        time.setText("")
    }
}