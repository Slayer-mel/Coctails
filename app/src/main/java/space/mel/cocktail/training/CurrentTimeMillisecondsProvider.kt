package space.mel.cocktail.training

class CurrentTimeMillisecondsProvider {
    fun timeMilliseconds(plusMillis  : Int): Long{
        return System.currentTimeMillis() + plusMillis
    }
}