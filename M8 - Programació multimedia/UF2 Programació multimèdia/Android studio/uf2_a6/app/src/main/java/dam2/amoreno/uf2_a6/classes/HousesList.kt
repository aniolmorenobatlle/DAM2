package dam2.amoreno.uf2_a6.classes

import java.text.DecimalFormat

data class HousesList(val address: String, val price: Float, val imageResourceId: Int, val lat: Float, val long: Float) {
    val formattedPrice: String
        get() {
            val formatter = DecimalFormat("#,###") // Que sigui 100.000
            return "${formatter.format(price)} €"    // Afegir €
        }
}
