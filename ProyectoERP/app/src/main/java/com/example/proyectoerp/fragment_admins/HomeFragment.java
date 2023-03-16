package com.example.proyectoerp.fragment_admins;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.proyectoerp.R;


public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        //Web view para entrar al correo
        WebView web = v.findViewById(R.id.web);
        web.loadUrl("https://accounts.google.com/ServiceLogin/signinchooser?service=mail&passive=1209600&osid=1&continue=https%3A%2F%2Fmail.google.com%2Fmail%2Fu%2F0%2F&followup=https%3A%2F%2Fmail.google.com%2Fmail%2Fu%2F0%2F&emr=1&ifkv=AWnogHfDB_r6YNOgK_3yTFS2ocvM5KUEhTN7thQgTGr9ojAD4vKADYrGNTdijv7lMZnNAFStNLss&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
        web.setWebViewClient(new WebViewClient());

        // Inflate the layout for this fragment
        return v;
    }
}