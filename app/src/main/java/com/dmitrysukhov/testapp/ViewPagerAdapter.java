package com.dmitrysukhov.testapp;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.adapter.FragmentViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private final ArrayList<MyFragment> arrayList = new ArrayList<>();
    public static int countOfFragments = 0;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public void addFragment(MyFragment fragment) {
        arrayList.add(fragment);
        countOfFragments = arrayList.size();
        notifyDataSetChanged();
    }

    public void removeFragment(int id) {
        MyFragment fragment = null;
        for (MyFragment item : arrayList) {
            if (item.getMyId() == id) fragment = item;
        }
        arrayList.remove(fragment);
        countOfFragments = arrayList.size();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return arrayList.get(position).getMyId();
    }

    @Override
    public void onBindViewHolder(@NonNull FragmentViewHolder holder, int position, @NonNull List<Object> payloads) {
        holder.setIsRecyclable(false);
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public boolean containsItem(long itemId) {
        return super.containsItem(itemId);
    }
}
