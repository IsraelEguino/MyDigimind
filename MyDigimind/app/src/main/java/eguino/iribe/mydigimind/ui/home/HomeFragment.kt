package eguino.iribe.mydigimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import eguino.iribe.mydigimind.Carrito
import eguino.iribe.mydigimind.R
import eguino.iribe.mydigimind.Recordatorio
import eguino.iribe.mydigimind.ui.Task
import kotlinx.android.synthetic.main.recordatorio.view.*

class HomeFragment : Fragment() {
    private var adaptador: AdaptadorTareas?=null
    private lateinit var homeViewModel: HomeViewModel

    companion object{
        var tasks = ArrayList<Task>()
        var first= true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        if(first) {
            fillTask()
            first=false
        }

        adaptador = AdaptadorTareas(root.context,tasks)
        val gridView: GridView = root.findViewById(R.id.gridview)
        gridView.adapter=adaptador


        return root
    }

    fun fillTask(){
        tasks.add(Task("Practice 1", arrayListOf("Monday","Sunday"),"07:30"))
        tasks.add(Task("Practice 2", arrayListOf("Saturday","Thursday"),"17:30"))
        tasks.add(Task("Practice 3", arrayListOf("Wednesday","Tuesday"),"20:20"))
        tasks.add(Task("Practice 4", arrayListOf("Friday"),"11:11"))
        tasks.add(Task("Practice 5", arrayListOf("Monday"),"03:01"))
        tasks.add(Task("Practice 6", arrayListOf("Sunday"),"00:00"))
        tasks.add(Task("Practice 7", arrayListOf("Wednesday"),"023:59"))

    }

    private class AdaptadorTareas: BaseAdapter{
        var tasks=ArrayList<Task>()
        var contexto: Context? = null

        constructor(context: Context,tasks: ArrayList<Task>){
            this.contexto=context
            this.tasks=tasks
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var task = tasks[position]
            var inflator = LayoutInflater.from(contexto)
            var vista = inflator.inflate(R.layout.task_view,null)

            var tv_title: TextView= vista.findViewById(R.id.tv_title)
            var tv_time: TextView= vista.findViewById(R.id.tv_time)
            var tv_days: TextView= vista.findViewById(R.id.tv_days)

            tv_title.setText(task.title)
            tv_time.setText(task.time)
            tv_days.setText(task.days.toString())

            return vista
        }

        override fun getItem(position: Int): Any {
            return tasks[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return tasks.size
        }
    }
}