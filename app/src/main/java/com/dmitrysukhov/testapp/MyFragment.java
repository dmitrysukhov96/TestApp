package com.dmitrysukhov.testapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyFragment extends Fragment {

    private final int id;
    private NotificationManager notificationManager;

    public MyFragment(int id) {
        this.id = id;
    }

    public int getMyId() {
        return id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fragment, container, false);
        TextView textView = view.findViewById(R.id.textview_fragment_position);
        textView.setText(String.valueOf(id));
        FragmentCallback fragmentCallback = (FragmentCallback) getContext();
        view.findViewById(R.id.fab_fragment_add).setOnClickListener(view1 -> {
            if (fragmentCallback != null) {
                fragmentCallback.createNewFragment();
            }
        });
        view.findViewById(R.id.fab_fragment_remove).setOnClickListener(v -> {
            if (ViewPagerAdapter.countOfFragments > 0) {
                if (fragmentCallback != null) {
                    fragmentCallback.deleteFragment(id);
                    notificationManager.cancel(id);
                }
            }
        });
        view.findViewById(R.id.button_fragment_create_notification).setOnClickListener(view12 -> {
            Intent resultIntent = new Intent(getContext(), MainActivity.class);
            resultIntent.putExtra(MainActivity.FRAGMENT_TAG, id);
            PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            Notification notification;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(String.valueOf(id), String.valueOf(id), NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.createNotificationChannel(channel);
                notification = new Notification.Builder(getContext(), String.valueOf(id))
                        .setContentTitle(getString(R.string.notification_title)).setContentText(getString(R.string.notification_content)+" "+id)
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.ic_message).setContentIntent(pendingIntent).build();
                notificationManager.notify(id, notification);
            } //открывается не тот фрагмент который соответствует но тот чей ноутификейшн был создан последним. я не понял чего.
        });   //что-то с интентом или с тем что это инстанс одного и того же фрагмента. 
        if (ViewPagerAdapter.countOfFragments <= 1) {
            view.findViewById(R.id.fab_fragment_remove).setVisibility(View.GONE);
        }
        return view;
    }
}