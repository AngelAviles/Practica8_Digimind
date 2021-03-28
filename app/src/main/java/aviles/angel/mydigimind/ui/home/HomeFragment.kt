package aviles.angel.mydigimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import aviles.angel.mydigimind.R
import aviles.angel.mydigimind.Recordatorio

class HomeFragment : Fragment() {
    companion object {
        var tasks = ArrayList<Recordatorio>()
        var first = true
    }

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        if (first) {
            fillTasks()
            first = false
        }

        var gridview: GridView = root.findViewById<GridView>(R.id.gridView)

        var adaptador: RecordatorioAdapter = RecordatorioAdapter(container!!.context, tasks)
        gridview.adapter = adaptador

        return root
    }

    fun fillTasks() {
        tasks.add(Recordatorio(arrayListOf("Tuesday"), "17:30", "Practice 1"))
        tasks.add(Recordatorio(arrayListOf("Monday", ", Sunday"), "17:00", "Practice 2"))
        tasks.add(Recordatorio(arrayListOf("Wednesday"), "14:00", "Practice 3"))
        tasks.add(Recordatorio(arrayListOf("Saturday"), "11:00", "Practice 4"))
        tasks.add(Recordatorio(arrayListOf("Friday"), "13:00", "Practice 5"))
        tasks.add(Recordatorio(arrayListOf("Thursday"), "10:40", "Practice 6"))
        tasks.add(Recordatorio(arrayListOf("Monday"), "12:00", "Practice 7"))
    }
}



class RecordatorioAdapter: BaseAdapter {
    var tasks = ArrayList<Recordatorio>()
    var context: Context? = null

    constructor(context: Context, tasks: ArrayList<Recordatorio>) {
        this.context = context
        this.tasks = tasks
    }

    override fun getCount(): Int {
        return tasks.size
    }

    override fun getItem(position: Int): Any {
        return tasks[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var recordatorio = tasks[position]
        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var vista = inflator.inflate(R.layout.recordatorio, null)

        val txtNombreRecordatorio =  vista.findViewById<TextView>(R.id.txtNombreRecordatorio)
        val txtDiasRecordatorio =  vista.findViewById<TextView>(R.id.txtDiasRecordatorio)
        val txtTiempoRecordatorio =  vista.findViewById<TextView>(R.id.txtTiempoRecordatorio)

        var dias = ""
        for (dia in recordatorio.dias) {
            dias += dia
        }

        txtNombreRecordatorio.setText(recordatorio.nombre)
        txtDiasRecordatorio.setText(dias)
        txtTiempoRecordatorio.setText(recordatorio.tiempo)

        return vista
    }

}