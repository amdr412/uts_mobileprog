package com.example.astimen.myapplication.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.example.astimen.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link frg_welcome.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link frg_welcome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frg_welcome extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public frg_welcome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frg_welcome.
     */
    // TODO: Rename and change types and number of parameters
    public static frg_welcome newInstance(String param1, String param2) {
        frg_welcome fragment = new frg_welcome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        new myikc().execute();
        return inflater.inflate(R.layout.fragment_frg_welcome, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    class myikc extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }


        protected String doInBackground(String... args) {


            return null;
        }

        protected void onPostExecute(String file_url) {



            String urlPictureMain, urlCoverMain, urlPictureSecond;


            Button btnHome=(Button) getView().findViewById(R.id.btnRefresh);
            btnHome.setText("Selamat Datang di Home");

            btnHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do something

                    Toast.makeText(getActivity(), "Loading Home", Toast.LENGTH_LONG).show();
                    new myikc().execute();

                }
            });


            WebView wv =(WebView) getView().findViewById(R.id.wvAflowz);
            WebSettings webSettings = wv.getSettings();
            webSettings.setJavaScriptEnabled(true);
            //tambahan script untuk invoke html javascript



            String url="http://aflowz.com";
            //String url="http://10.0.2.2/aflowz/trigerandro.html";

            wv.loadUrl(url);
            wv.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });

            wv.addJavascriptInterface(new WebAppInterface(getActivity()), "MyAndroid");

            wv.setWebViewClient(new WebViewClient() {
                public void onReceivedError(WebView view, int errorCode, String description, String
                        failingUrl) {
                    Toast.makeText(getActivity(), "Mohon periksa koneksi data internet anda ...", Toast.LENGTH_SHORT).show();
                    WebView wv =(WebView) getView().findViewById(R.id.wvAflowz);

                    wv.loadDataWithBaseURL("file:///android_asset/", "<img src=\"file:///android_res/drawable/internetdisc.png\"/>", "text/html", "utf-8", null);
                }
            });



        }
    }

    public class WebAppInterface {
        Context mContext;
        /** Instantiate the interface and set the context */
        WebAppInterface(Context c) {
            mContext = c;
        }
        /** Show a toast from the web page */
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }
        @JavascriptInterface
        public void PesanToast(String nama,String pesan) {
            showToast("Pesan dari "+nama+":"+pesan);
            ((AppCompatActivity)
                    getActivity()).getSupportActionBar().setTitle("Button2");
        }
        @JavascriptInterface
        public void showDialog(String nama,String pesan) {
            DialogInterface.OnClickListener dialogClickListener = new
                    DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE:
                                {
                                }
                                break;
                                case DialogInterface.BUTTON_NEGATIVE:
                                {
                                }
                                break;
                            }
                        }
                    };
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(pesan)
                    .setCancelable(false)
                    .setTitle("Pesan Dari:"+nama)
                    .setPositiveButton("Ya", dialogClickListener)
                    .show();
            ((AppCompatActivity)
                    getActivity()).getSupportActionBar().setTitle("Button1");
        }
    }
}

