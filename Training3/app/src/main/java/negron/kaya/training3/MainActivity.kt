package negron.kaya.training3

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import negron.kaya.training3.model.Dog

class MainActivity : AppCompatActivity() {

    private val infoWeight:InfoLayout by lazy { findViewById<InfoLayout>(R.id.info_weight) }
    private val photoImageView: ImageView by lazy { findViewById<ImageView>(R.id.img_photo) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView(DataHelper.getDog())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_profile, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when(item.itemId) {
            R.id.item_edit -> {
                Toast.makeText(this, "Click en edit", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun setupView(dog: Dog) {
        findViewById<TextView>(R.id.text_name).text = dog.name
        findViewById<TextView>(R.id.text_address).text = dog.address
        findViewById<InfoLayout>(R.id.info_color).setCharacteristic(dog.color)
        findViewById<InfoLayout>(R.id.info_age).apply {
            contentDescription = "23 anios"
            setCharacteristic(dog.age)
        }
        infoWeight.setCharacteristic(dog.weight)

        setupPhoto(dog.photo)
    }

    private fun setupPhoto(url: String) {
        Picasso.get().load(url).placeholder(R.drawable.ic_pet_default).into(photoImageView)
    }

    fun follow(view: View) {
        val button = view as Button
        button.text = getString(R.string.btn_following)


        AlertDialog.Builder(this)
            .setMessage("Un mensaje en mi dialogo")
            .setCancelable(false)
            .setPositiveButton("Si", DialogInterface.OnClickListener { _, _ ->
                Snackbar.make(view, "Snackbar si", Snackbar.LENGTH_LONG).show()
            })
            .setNegativeButton("No", DialogInterface.OnClickListener { _, _ ->
                Snackbar.make(view, "Snackbar no", Snackbar.LENGTH_LONG).show()
            }).create().show()
    }
}