package eguino.iribe.mydigimind.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import eguino.iribe.mydigimind.Carrito
import eguino.iribe.mydigimind.R
import eguino.iribe.mydigimind.Recordatorio
import eguino.iribe.mydigimind.ui.Task
import eguino.iribe.mydigimind.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
            val btnSetTime: Button = root.findViewById(R.id.setTime) as Button

            btnSetTime.setOnClickListener(){
                val cal = Calendar.getInstance()
                val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                    cal.set(Calendar.HOUR_OF_DAY, hour)
                    cal.set(Calendar.MINUTE, minute)

                    btnSetTime.text= SimpleDateFormat("HH:mm").format(cal.time)
                }
                TimePickerDialog(root.context,timeSetListener,cal.get(Calendar.HOUR_OF_DAY),
                    cal.get(Calendar.MINUTE),true).show()
            }

        val btn_save = root.findViewById(R.id.btnDone) as Button
        val et_titulo = root.findViewById(R.id.edtText) as EditText
        val checkMonday = root.findViewById(R.id.monday) as CheckBox
        val checkTuesday = root.findViewById(R.id.tuesday) as CheckBox
        val checkWednesday = root.findViewById(R.id.wednesday) as CheckBox
        val checkThursday = root.findViewById(R.id.thursday) as CheckBox
        val checkFriday = root.findViewById(R.id.friday) as CheckBox
        val checkSaturday = root.findViewById(R.id.saturday) as CheckBox
        val checkSunday = root.findViewById(R.id.sunday) as CheckBox

        btn_save.setOnClickListener{
            var title=et_titulo.text.toString()
            var time=btnSetTime.text.toString()
            var days=ArrayList<String>()

            if(checkMonday.isChecked){
                days.add("Monday")
            }
            if(checkTuesday.isChecked){
                days.add("Tuesday")
            }
            if(checkWednesday.isChecked){
                days.add("Wednesday")
            }
            if(checkThursday.isChecked){
                days.add("Thursday")
            }
            if(checkFriday.isChecked){
                days.add("Friday")
            }
            if(checkSaturday.isChecked){
                days.add("Saturday")
            }
            if(checkSunday.isChecked){
                days.add("Sunday")
            }

            var task = Task(title,days,time)

            HomeFragment.tasks.add(task)

            Toast.makeText(root.context,"New task added",Toast.LENGTH_SHORT).show()
        }

        return root
    }




}