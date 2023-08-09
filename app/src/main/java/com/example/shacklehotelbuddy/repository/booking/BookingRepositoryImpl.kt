package com.example.shacklehotelbuddy.repository.booking

import android.content.Context
import android.content.SharedPreferences
import com.example.shacklehotelbuddy.ui.model.BookingItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import javax.inject.Inject

class BookingRepositoryImpl @Inject constructor(
    val context: Context
) : BookingRepository {

    companion object {
        const val HISTORY_FILE = "HISTORY_FILE"
        const val HISTORY_KEY = "HISTORY_KEY"
        const val MAX_HISTORY_SIZE = 10
    }

    override fun getSearchHistory(): List<BookingItem> {
        return getBookingList()
    }

    override fun saveSearchHistory(bookingItem: BookingItem) {
        val currentList = getBookingList().toMutableList()
        if (currentList.size == MAX_HISTORY_SIZE) {
            currentList.removeAt(currentList.lastIndex)

        }
        currentList.add(0, bookingItem)
        saveBookingList(currentList)
    }

    private fun getSharedPreference(): SharedPreferences? {
        return context.getSharedPreferences(
            HISTORY_FILE,
            Context.MODE_PRIVATE
        )
    }

    private fun saveBookingList(list: List<BookingItem>) {
        val prefs: SharedPreferences = getSharedPreference() ?: return
        val editor: SharedPreferences.Editor = prefs.edit()
        val json: String = Gson().toJson(list)
        editor.putString(HISTORY_KEY, json)
        editor.apply()
    }

    private fun getBookingList(): List<BookingItem> {
        val prefs: SharedPreferences = getSharedPreference() ?: return emptyList()
        val json: String = prefs.getString(HISTORY_KEY, null) ?: return emptyList()
        val type: Type = object : TypeToken<List<BookingItem>>() {}.type
        return Gson().fromJson(json, type)
    }
}
