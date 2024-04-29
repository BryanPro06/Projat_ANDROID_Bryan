package fr.bd.naturecollectionbryan2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.bd.naturecollectionbryan2.AssuranceModel
import fr.bd.naturecollectionbryan2.AssuranceRepository.Singleton.AssuranceList
import fr.bd.naturecollectionbryan2.MainActivity
import fr.bd.naturecollectionbryan2.R
import fr.bd.naturecollectionbryan2.adapter.AssuranceAdapter
import fr.bd.naturecollectionbryan2.adapter.AssuranceItemDecoration

class HomeFragment(
    private val context: MainActivity
) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_home, container, false)

        //recuperer le recyclerview
        val horizontalRecyclerView = view?.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView?.adapter =AssuranceAdapter(context, AssuranceList , R.layout.item_horizontal_assurance)

        //recuperer le second recyclerview
        val verticalRecyclerView = view?.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView?.adapter =AssuranceAdapter(context, AssuranceList, R.layout.item_vertical_asssurance)
        verticalRecyclerView?.addItemDecoration(AssuranceItemDecoration())

        return view
    }
}