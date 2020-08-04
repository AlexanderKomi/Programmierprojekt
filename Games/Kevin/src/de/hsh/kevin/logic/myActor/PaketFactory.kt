package de.hsh.kevin.logic.myActor

import de.hsh.kevin.logic.Config

/**
 * Erstellt Paktete
 * @author Kevin
 */
object PaketFactory {
    private val badPaketImages: List<String> = listOf(
        Config.resLocation + "ordner_red/ordner1.png",
        Config.resLocation + "ordner_red/ordner2.png",
        Config.resLocation + "ordner_red/ordner3.png",
        Config.resLocation + "ordner_red/ordner4.png")

    private val goodPaketImages: List<String> = listOf(
        Config.resLocation + "ordner_black/ordner1.png",
        Config.resLocation + "ordner_black/ordner2.png",
        Config.resLocation + "ordner_black/ordner3.png",
        Config.resLocation + "ordner_black/ordner4.png")

    /**
     * Erstellt ein BadPaket an Positon (x,y)
     * @param x - Wert der Positon des Pakets
     * @param y - Wert der Positon des Pakets
     * @return Paket
     */
    fun getBadPaket(x: Double = 0.0, y: Double = 0.0): Paket {
        return Paket(badPaketImages, x, y, enmPaketTyp.bad)
    }

    /**
     * Erstellt ein GoodPaket an Positon (x,y)
     * @param x - Wert der Positon des Pakets
     * @param y - Wert der Positon des Pakets
     * @return Paket
     */
    fun getGoodPaket(x: Double = 0.0, y: Double = 0.0): Paket {
        return Paket(goodPaketImages, x, y, enmPaketTyp.good)
    }

}
