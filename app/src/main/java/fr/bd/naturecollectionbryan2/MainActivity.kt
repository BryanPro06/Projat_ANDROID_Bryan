package fr.bd.naturecollectionbryan2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.bd.naturecollectionbryan2.fragments.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // charger notre repository
        val repo = AssuranceRepository()

        //mettre à jour la liste des assurances
        repo.updateData{
            // injecter le fragment dans notre boite (fragment_container)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, HomeFragment(this))
            transaction.addToBackStack(null)
            transaction.commit()

        }

    }
}