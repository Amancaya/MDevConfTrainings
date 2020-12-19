package negron.kaya.training6

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import negron.kaya.training6.model.Speaker
import timber.log.Timber

private const val COLLECTION_SPEAKERS = "speakers"

object FirebaseHelper {

    private val db = FirebaseFirestore
                                        .getInstance().collection(COLLECTION_SPEAKERS)

    fun getSpeakers(speakerListener: (List<Speaker>) -> Unit) {
        db.addSnapshotListener { value, error ->
            if (error != null) {
                Timber.tag("FirebaseHelper").e(error.message.toString())
            }
            value?.documents?.map { document ->
                Speaker(
                    id = document.id,
                    name = document.getString("name") ?: "",
                    talk = document.getString("talk") ?: ""
                )
            }?.let { speakers -> speakerListener(speakers) }
        }
    }

    fun addSpeaker(speaker: Speaker) {
        db.add(speaker).addOnSuccessListener {
            Timber.tag("FirebaseHelper").i("Agregado correctamente")
        }.addOnFailureListener { exception ->
            Timber.tag("FirebaseHelper").e(exception)
        }
    }

    fun deleteSpeaker(speaker: Speaker) {
        db.document(speaker.id).delete().addOnSuccessListener {
            Timber.tag("FirebaseHelper").i("Elimindo correctamente")
        }.addOnFailureListener { exception ->
            Timber.tag("FirebaseHelper").e(exception)
        }
    }

    fun updateSpeaker(speaker: Speaker) {
        db.document(speaker.id).set(speaker).addOnSuccessListener {
            Timber.tag("FirebaseHelper").i("Actualizado correctamente")
        }.addOnFailureListener { exception ->
            Timber.tag("FirebaseHelper").e(exception)
        }
    }
}