package space.mel.cocktail.training

import android.text.format.DateFormat

class MainProvider {
    val currentTimeMillisecondsProvider: CurrentTimeMillisecondsProvider = CurrentTimeMillisecondsProvider()
    val timeFormatProvider:TimeFormatProvider = TimeFormatProvider()

    fun getTimeString(plusMillisecond : Int) : String {
        return DateFormat.format(timeFormatProvider.timeFormat(),
            currentTimeMillisecondsProvider.timeMilliseconds(plusMillisecond)).toString()
    }
}


//"HH:dd"
//System.currentTimeMillis()