package com.example.astimen.myapplication.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.astimen.myapplication.R;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link frg_edit.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link frg_edit#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frg_edit extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    private OnFragmentInteractionListener mListener;

    public frg_edit() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment frg_entry.
     */
    // TODO: Rename and change types and number of parameters
    public static frg_edit newInstance(Bundle bundle) {
        frg_edit fragment = new frg_edit();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    //----code action menu

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_edit, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_new:
            {
                ((AppCompatActivity)
                        getActivity()).getSupportActionBar().setTitle("Entry New Data");

                android.support.v4.app.Fragment mFragment = null;
                mFragment = frg_entry.newInstance("0","0");

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frm_utama, mFragment)
                        .addToBackStack(null)
                        .commit();
            }
            break;
            case R.id.action_search:
            {
                ((AppCompatActivity)
                        getActivity()).getSupportActionBar().setTitle("Search Data");

                android.support.v4.app.Fragment mFragment = null;
                mFragment = frg_search.newInstance("0","0");

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frm_utama, mFragment)
                        .addToBackStack(null)
                        .commit();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        new myikc().execute();



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frg_edit, container, false);




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

            //---mengambil data dari bundle_search

            Bundle bundle_edit = getArguments();

            final String db_id_rec = bundle_edit.getString("id_rec");
            final String db_user_id = bundle_edit.getString("user_id");
            String db_user_name = bundle_edit.getString("user_name");
            String db_department = bundle_edit.getString("department");
            String db_user_sex = bundle_edit.getString("user_sex");
            String db_role_admin = bundle_edit.getString("role_admin");
            String db_role_user = bundle_edit.getString("role_user");


            //---set data dari bundle ke object layout

            EditText etIdRec = (EditText) getView().findViewById(R.id.txtIdRec);
            etIdRec.setText(db_id_rec);

            //---set EditText langsung

            ((EditText) getView().findViewById(R.id.txtUserId)).setText(db_user_id);
            ((EditText) getView().findViewById(R.id.txtUserName)).setText(db_user_name);

            //---set item spinner dengan String

            Spinner spDepartment = (Spinner) getView().findViewById(R.id.spnDepartment);
            spDepartment.setSelection(((ArrayAdapter<String>)spDepartment.getAdapter()).getPosition(db_department));

            //---set radion button grup  yang diset grup nya salah
            RadioGroup rbgSex = (RadioGroup) getView().findViewById(R.id.rgSex);
            RadioButton rbMale = (RadioButton) getView().findViewById(R.id.rbMale);
            RadioButton rbFemale = (RadioButton) getView().findViewById(R.id.rbFemale);

            if (db_user_sex.equals("Male"))
            {
                rbgSex.check(rbMale.getId());
            }
            else
            {
                rbgSex.check(rbFemale.getId());
            }

            //--set checkbox


            CheckBox cbRoleAdmin = (CheckBox) getView().findViewById(R.id.cbxAdmin);
            CheckBox cbRoleUser = (CheckBox) getView().findViewById(R.id.cbxUser);

            if(db_role_admin.equals("Admin"))
            {
                cbRoleAdmin.setChecked(true);
            }
            else
            {
                cbRoleAdmin.setChecked(false);
            }



            if(db_role_user.equals("User"))
            {
                cbRoleUser.setChecked(true);
            }
            else
            {
                cbRoleUser.setChecked(false);
            }


            //--ganti title bar

            ((AppCompatActivity)
                    getActivity()).getSupportActionBar().setTitle("Edit Data");


            //---jika button Upload cilck

            Button btn_upload = (Button) getView().findViewById(R.id.btnUpload);
            btn_upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    android.support.v4.app.Fragment mFragment = null;
                    mFragment = frg_upload_image.newInstance(db_id_rec,db_user_id);

                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.frm_utama, mFragment)
                            .addToBackStack(null)
                            .commit();



                }
            });

            //---jika button save click
            Button btn_save = (Button) getView().findViewById(R.id.btnSave);
            btn_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do something

                    Toast.makeText(getActivity(), "Button Save Clicked", Toast.LENGTH_LONG).show();

                    EditText etIdRec = (EditText) getView().findViewById(R.id.txtIdRec);
                    final String dtxtIdrec=etIdRec.getText().toString();

                    EditText etUserId = (EditText) getView().findViewById(R.id.txtUserId);
                    final String dtxtUserId=etUserId.getText().toString();

                    final String dtxtUserName = ((EditText) getView().findViewById(R.id.txtUserName)).getText().toString();
                    final String dtxtPassword = ((EditText) getView().findViewById(R.id.txtPassword)).getText().toString();
                    final String dtxtPassword2 = ((EditText) getView().findViewById(R.id.txtPassword2)).getText().toString();
                    final String dspnDepartment = ((Spinner) getView().findViewById(R.id.spnDepartment)).getSelectedItem().toString();

                    final int drbSex = ((RadioGroup) getView().findViewById(R.id.rgSex)).getCheckedRadioButtonId();
                    final RadioButton rbSex = ((RadioButton) getView().findViewById(drbSex));
                    final String sdrbSex = rbSex.getText().toString();


                    final Boolean bolRoleAdmin=((CheckBox) getView().findViewById(R.id.cbxAdmin)).isChecked();
                    final Boolean bolRoleUser=((CheckBox) getView().findViewById(R.id.cbxUser)).isChecked();

                    final String sbolRoleAdmin = bolRoleAdmin.toString();
                    final String sbolRoleUser = bolRoleUser.toString();




                    if(dtxtIdrec.trim().length()==0)
                    {
                        pesandialog("Entrian Id Record tidak boleh kosong ...!");
                        etIdRec.requestFocus();

                    }
                    else if(dtxtUserId.trim().length()==0)
                    {
                        pesandialog("Entrian User Id tidak boleh kosong ...!");
                        etUserId.requestFocus();

                    }

                    else if(dtxtUserName.trim().length()==0)
                    {
                        pesandialog("Entrian User Name tidak boleh kosong ...!");
                        ((EditText) getView().findViewById(R.id.txtUserName)).requestFocus();

                    }

                    else if(dtxtPassword.trim().length()==0)
                    {
                        pesandialog("Entrian Password tidak boleh kosong ...!");
                        ((EditText) getView().findViewById(R.id.txtPassword)).requestFocus();

                    }
                    else if(dtxtPassword2.trim().length()==0)
                    {
                        pesandialog("Entrian Password kedua tidak boleh kosong ...!");
                        ((EditText) getView().findViewById(R.id.txtPassword2)).requestFocus();

                    }

                    //pengecekan password satu dan dua

                    if(!dtxtPassword.equals(dtxtPassword2))
                    {
                        pesandialog("Password dan Pasword2 tidak sama ...!");
                        ((EditText) getView().findViewById(R.id.txtPassword2)).requestFocus();
                    }

                   //------volley

                    final String REGISTER_URL = "http://192.168.1.7/aflowz/volley_update.php";

                    final String KEY_ID_REC = "id_rec";
                    final String KEY_USER_ID = "user_id";
                    final String KEY_USER_NAME = "user_name";
                    final String KEY_PASSWORD = "password";
                    final String KEY_DEPARTMENT = "department";
                    final String KEY_USER_SEX = "user_sex";
                    final String KEY_ROLE_ADMIN = "role_admin";
                    final String KEY_ROLE_USER = "role_user";


                    StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(getActivity(),"Server : " + response, Toast.LENGTH_LONG).show();
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getActivity(),"Server :" + error.toString(), Toast.LENGTH_LONG).show();
                                }
                            }){
                        @Override
                        protected Map<String,String> getParams(){
                            Map<String,String> params = new HashMap<String, String>();
                            params.put(KEY_ID_REC,dtxtIdrec);
                            params.put(KEY_USER_ID,dtxtUserId);
                            params.put(KEY_USER_NAME, dtxtUserName);
                            params.put(KEY_PASSWORD, dtxtPassword);
                            params.put(KEY_DEPARTMENT, dspnDepartment);
                            params.put(KEY_USER_SEX, sdrbSex);
                            params.put(KEY_ROLE_ADMIN, sbolRoleAdmin);
                            params.put(KEY_ROLE_USER, sbolRoleUser);

                            return params;
                        }

                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                    requestQueue.add(stringRequest);

                   //-------------

                }
            });



        }
    }

    void pesandialog(String pesan){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        Toast.makeText(getActivity(), "Isi kembali entrian yang kosong ...", Toast.LENGTH_LONG).show();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;

                }
            }

        };



        AlertDialog.Builder builder = new AlertDialog.Builder(getView().getContext());
        builder
                .setTitle("AflowZ")
                .setMessage(pesan)
                .setCancelable(false)
                .setPositiveButton("OK",dialogClickListener)
                .show();

    }



}
