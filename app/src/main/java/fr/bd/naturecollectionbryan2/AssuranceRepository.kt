package fr.bd.naturecollectionbryan2

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fr.bd.naturecollectionbryan2.AssuranceRepository.Singleton.AssuranceList
import fr.bd.naturecollectionbryan2.AssuranceRepository.Singleton.databaseRef
import javax.security.auth.callback.Callback

class AssuranceRepository {

    object Singleton {
        //se connecter à la référence "assurances"
        val databaseRef =
            FirebaseDatabase.getInstance().getReference("assurances")

        //créer une liste qui va contenir nos assurances
        val AssuranceList = arrayListOf<AssuranceModel>()

    }

    fun updateData(callback: () -> Unit) {
        // absorber les données depuis la dataBaseRef -> liste d'assurances
        databaseRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // retirer les anciennes
                AssuranceList.clear()
                //recolter la liste
                for (ds in snapshot.children) {
                    // construire un objet assurance
                    val assurance = ds.getValue(AssuranceModel::class.java)

                    //vérifier que l'assurance n'est pas null
                    if(assurance != null) {
                        // ajouter l'assurance à notre liste
                        AssuranceList.add(assurance)
                    }
                } // actionner le callback
                    callback()
            }

            override fun onCancelled(error: DatabaseError) {}


        })
    }
    //mettre a jour un objet assurance en bdd
    fun updateAssurance(assurance: AssuranceModel) = databaseRef.child(assurance.id).setValue(assurance)

}