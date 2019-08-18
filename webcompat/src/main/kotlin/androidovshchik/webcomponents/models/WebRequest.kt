/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package androidovshchik.webcomponents.models

import android.os.Parcel
import android.os.Parcelable

open class WebRequest(val data: CharSequence?, baseUrl: CharSequence? = null) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        TODO("baseUrl")) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(data?.toString())
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WebRequest> {
        override fun createFromParcel(parcel: Parcel): WebRequest {
            return WebRequest(parcel)
        }

        override fun newArray(size: Int): Array<WebRequest?> {
            return arrayOfNulls(size)
        }
    }

}