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
import kotlinx.android.synthetic.main.recordatorio.view.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    var adapter: CarritoAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val gridview = root.findViewById(R.id.gridview) as GridView

        var carrito: Carrito = Carrito()
        carrito.agregar(Recordatorio("Mon Sat", "08:12", "Levantarse"))
        carrito.agregar(Recordatorio("Fri Sun", "10:15", "Salir a correr"))


        var adapter = CarritoAdapter(this.context,carrito)
        gridview.adapter=adapter

        homeViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }

    class CarritoAdapter : BaseAdapter {
        var context: Context? = null
        var carrito: Carrito = Carrito()

        constructor(context: Context?, carrito: Carrito) {
            this.context = context
            this.carrito = carrito
        }

        override fun getCount(): Int {
            return carrito.recordatorios.size
        }

        override fun getItem(position: Int): Any {
            return carrito.recordatorios[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var recordatorio = carrito.recordatorios[position]
            var inflator =
                context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista = inflator.inflate(R.layout.recordatorio, null)

            vista.txtDiasRecordatorio.setText(recordatorio.dias)
            vista.txtNombreRecordatorio.setText(recordatorio.nombre)
            vista.txtTiempoRecordatorio.setText(recordatorio.tiempo)

            return vista
        }
    }
}