/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package androidovshchik.webcomponents.extensions

import android.os.Build

fun isIceCreamSandwich() = Build.VERSION.SDK_INT == Build.VERSION_CODES.ICE_CREAM_SANDWICH

fun isIceCreamSandwichMR1() = Build.VERSION.SDK_INT == Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1

fun isJellyBean() = Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN

fun isJellyBeanMR1() = Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR1

fun isJellyBeanMR2() = Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR2

fun isKitkat() = Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT

fun isKitkatWatch() = Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT_WATCH

fun isLollipop() = Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP

fun isLollipopMR1() = Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP_MR1

fun isMarshmallow() = Build.VERSION.SDK_INT == Build.VERSION_CODES.M

fun isNougat() = Build.VERSION.SDK_INT == Build.VERSION_CODES.N

fun isNougatMR1() = Build.VERSION.SDK_INT == Build.VERSION_CODES.N_MR1

fun isOreo() = Build.VERSION.SDK_INT == Build.VERSION_CODES.O

fun isOreoMR1() = Build.VERSION.SDK_INT == Build.VERSION_CODES.O_MR1

fun isPie() = Build.VERSION.SDK_INT == Build.VERSION_CODES.P

fun isIceCreamSandwichPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH

fun isIceCreamSandwichMR1Plus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1

fun isJellyBeanPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN

fun isJellyBeanMR1Plus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1

fun isJellyBeanMR2Plus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2

fun isKitkatPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT

fun isKitkatWatchPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH

fun isLollipopPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

fun isLollipopMR1Plus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1

fun isMarshmallowPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

fun isNougatPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

fun isNougatMR1Plus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1

fun isOreoPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

fun isOreoMR1Plus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1

fun isPiePlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P