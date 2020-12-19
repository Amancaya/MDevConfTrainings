package negron.kaya.training6.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Speaker(
    val id: String = "",
    val name: String,
    val talk: String
): Parcelable