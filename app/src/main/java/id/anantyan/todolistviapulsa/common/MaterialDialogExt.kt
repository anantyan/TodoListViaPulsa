package id.anantyan.todolistviapulsa.common

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * Created by Arya Rezza Anantya on 09/06/24.
 */
fun Context.messageListDialog(
    title: String,
    items: List<String>,
    onItemClick: (String, Int) -> Unit
) {
    val itemArray = items.toTypedArray()
    MaterialAlertDialogBuilder(this)
        .setTitle(title)
        .setItems(itemArray) { dialog, which ->
            onItemClick(items[which], which)
        }
        .setCancelable(true)
        .show()
}