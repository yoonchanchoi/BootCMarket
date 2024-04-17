package com.example.bootcmarket

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat


private const val NOTIFICATION_ID = 1

fun Context.notification() {
    val intent = Intent(this, MainActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
    // 알림의 기본 정보
    val notification = createNotification(
        channelId = "market_Id",
        title = "알림 제목",
        text = "알림 내용",
        bigContentText= "설정한 키워드에 대한 알림이 도착했습니다.!",
        intent = pendingIntent
    )
    notify(notificationId = NOTIFICATION_ID, notification = notification)
}




private fun Context.createNotification(
    channelId: String,
    title: String,
    text: String,
    bigContentText: String,
    intent: PendingIntent
) = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
    NotificationCompat.Builder(this, channelId)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setWhen(System.currentTimeMillis())
        .setContentTitle(title)
        .setContentText(text)
        .setStyle(NotificationCompat.BigTextStyle().bigText(bigContentText))
        .setAutoCancel(true)
        .setContentIntent(intent)
        .build()
}else{
    NotificationCompat.Builder(this)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setWhen(System.currentTimeMillis())
        .setContentTitle(title)
        .setContentText(text)
        .setStyle(NotificationCompat.BigTextStyle().bigText(bigContentText))
        .setAutoCancel(true)
        .setContentIntent(intent)
        .build()
}

private fun Context.notify(notificationId: Int, notification: Notification) {
    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.notify(notificationId, notification)
}



fun Context.notificationChannelCreate(){
    val manager = getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager

    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        // 26 버전 이상
        val channelId="market_Id"
        val channelName="market_name"
        val channel = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            // 채널에 다양한 정보 설정
            description = "My Channel One Description"
            setShowBadge(true)
            val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_ALARM)
                .build()
            setSound(uri, audioAttributes)
            enableVibration(true)
        }
        manager.createNotificationChannel(channel)
    }
}
