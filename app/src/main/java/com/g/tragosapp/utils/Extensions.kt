package com.g.tragosapp.utils

import android.content.Context
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText

fun Context.shortToast(message: String) = makeText(this, message, LENGTH_SHORT).show()