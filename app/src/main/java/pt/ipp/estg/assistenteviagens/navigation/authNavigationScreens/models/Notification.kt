package pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.models

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import pt.ipp.estg.assistenteviagens.R

fun Notification(mContext: Context) {
    val notificationManager =
        mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            "channel_id",
            "channel_name",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)
    }
    val notification = NotificationCompat.Builder(mContext, "channel_id")
        .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
        .setContentTitle("WhereYouWantToGo?")
        .setContentText("Registado com Sucesso!")
        .setAutoCancel(true)
        .build()

    notificationManager.notify(1, notification)
}