package com.techworx.blaze.ui.country.models

import android.os.Parcel
import android.os.Parcelable

class Country() : Parcelable {
    var countryName: String = ""
    var countryISO: String = ""
    var countryCode: String = ""
    var flagDrawable: Int = -1

    constructor(parcel: Parcel) : this() {
        countryName = parcel.readString()!!
        countryISO = parcel.readString()!!
        countryCode = parcel.readString()!!
        flagDrawable = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(countryName)
        parcel.writeString(countryISO)
        parcel.writeString(countryCode)
        parcel.writeInt(flagDrawable)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Country> {
        override fun createFromParcel(parcel: Parcel): Country {
            return Country(parcel)
        }

        override fun newArray(size: Int): Array<Country?> {
            return arrayOfNulls(size)
        }
    }
}