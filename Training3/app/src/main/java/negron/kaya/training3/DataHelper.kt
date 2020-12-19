package negron.kaya.training3

import negron.kaya.training3.model.Dog

object DataHelper {

    fun getDog() = Dog(
        name = "Winnie",
        age = "1 año",
        color = "Beige",
        weight = "5 Kilos",
        photo = "https://i.ibb.co/19k9Wcv/Captura-de-Pantalla-2020-12-15-a-la-s-01-43-32.png",
        address = "Sucre, Bolivia"
    )
}