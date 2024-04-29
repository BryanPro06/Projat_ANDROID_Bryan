package fr.bd.naturecollectionbryan2.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.bd.naturecollectionbryan2.AssuranceModel
import fr.bd.naturecollectionbryan2.AssuranceRepository
import fr.bd.naturecollectionbryan2.MainActivity
import fr.bd.naturecollectionbryan2.R

class AssuranceAdapter(
    private val context: MainActivity,
    private val AssuranceList: List<AssuranceModel>,
    private val layoutId: Int
    ) : RecyclerView.Adapter<AssuranceAdapter.ViewHolder>(){

    // boite pour ranger tout les composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val assuranceImage = view.findViewById<ImageView>(R.id.image_item)
        val AssuranceName:TextView? = view.findViewById(R.id.name_item)
        val AssuranceDescription:TextView? = view.findViewById(R.id.description_item)
        val starIcon = view.findViewById<ImageView>(R.id.star_icon)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // recuperer les informations de l'assurance
        val currentAssurance = AssuranceList[position]

        //recuperer le repository
        val repo = AssuranceRepository()

        //utiliser glide pour récuperer l'image à partir de son lien -> composant
        Glide.with(context).load(Uri.parse(currentAssurance.imageUrl)).into(holder.assuranceImage)

        //mettre a jour le nom de l'assurance
        holder.AssuranceName?.text  = currentAssurance.name

        //mettre a jour la description de l'assurance
        holder.AssuranceDescription?.text  = currentAssurance.description

        //verifier si l'assurance a été liké
        if(currentAssurance.liked) {
            holder.starIcon.setImageResource(R.drawable.ic_like)
        }
        else {
            holder.starIcon.setImageResource(R.drawable.ic_unlike)
        }

        //rajouter une interaction sur cette etoile
        holder.starIcon.setOnClickListener {
            // inverse si le bouton est like ou pas
            currentAssurance.liked = !currentAssurance.liked
            //mettre à jour l'ojet assurance
            repo.updateAssurance(currentAssurance)
        }
    }

    override fun getItemCount(): Int = AssuranceList.size

}