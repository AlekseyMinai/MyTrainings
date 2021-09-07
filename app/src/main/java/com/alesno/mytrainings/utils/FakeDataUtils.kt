package com.alesno.mytrainings.utils

import android.os.Build
import androidx.annotation.RequiresApi


object FakeDataUtils {

    @RequiresApi(Build.VERSION_CODES.O)
    fun <T : Any> createListInstance(clazz: Class<T>, count: Int): List<T> {
        val list = mutableListOf<T>()
        repeat(count) {
            createInstance(clazz)?.let { list.add(it) }
        }
        return list
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun <T : Any> createInstance(clazz: Class<T>): T? {
        val primaryConstructor = clazz.constructors.firstOrNull()
        val parameters = primaryConstructor?.parameters?.map {
            when (it.type) {
                String::class.java -> "asdf"
                Int::class.java -> 5
                Char::class.java -> 'a'
                Byte::class.java -> 1
                Long::class.java -> 50L
                Float::class.java -> 5f
                Double::class.java -> 5.0
                Boolean::class.java -> true
                else -> createInstance(it.type)
            }
        }
        return parameters?.let { primaryConstructor.newInstance(*it.toTypedArray()) as T }
    }

}