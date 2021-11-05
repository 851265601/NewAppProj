package com.example.newapp.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Looper;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {
    public void ShowToast(String str) {

        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();

    }

    public void saveStringToSp(String Name, String token) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sp_ttit", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Name, token);
        editor.commit();
    }

    public String getStringFromSp(String Name) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sp_ttit", MODE_PRIVATE);
        return sharedPreferences.getString(Name, "");
    }

    public void showToastSync(String str) {
        Looper.prepare();
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
        Looper.loop();
    }

    public void showToast(String str) {
        //Looper.prepare();
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
        // Looper.loop();
    }

    public void Navigate(Class cls) {

        Intent intent = new Intent(getActivity(), cls);
        startActivity(intent);

    }
}
