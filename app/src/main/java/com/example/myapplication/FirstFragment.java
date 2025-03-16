package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class FirstFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);
        Button button1 = view.findViewById(R.id.button1);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        EditText editText = view.findViewById(R.id.edit1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String input = editText.getText().toString();
                MyThread thread  = new MyThread(Integer.parseInt(input));
                thread.start();

               //  Bundle bundle = new Bundle();
                // bundle.putInt("num",52);
                // secondFragment.setArguments(bundle);
                // Код с верху работает и переносит в фрагмент 2 число 52


                SecondFragment secondFragment = new SecondFragment();
                FragmentTransaction transaction = FirstFragment.this.requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, secondFragment);
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });

        return view;

    }
    public static class MyThread extends Thread{
        int count;
        SecondFragment secondFragment = new SecondFragment();
        public MyThread(int count){
            this.count=count;
        }
        @Override
        public void run() {


            Bundle bundle = new Bundle();
            bundle.putInt("num",count);
            secondFragment.setArguments(bundle);
            // в бандл успешно помещается число count

        }


    }

}