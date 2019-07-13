package com.example.astimen.myapplication.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.astimen.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link frg_search.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link frg_search#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frg_search extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    //--deklarasi data JSON



    /*
    id_rec: "20170105135026564",
    user_id: "asti",
    user_name: "astimen",
    department: "Finance",
    user_sex: "Male",
    role_admin: "true",
    role_user: "false"
    success: 1
    */

    private static String id_rec="";
    private static String user_id="";
    private static String user_name="";
    private static String department="";
    private static String user_sex="";
    private static String role_admin="";
    private static String role_user="";
    private static String img_user="";
    private static Integer istatus;

    private int nom=0;
    private static ArrayList<SearchResults> results = new ArrayList<SearchResults>();
    private  SearchResults sr1 = new SearchResults();

   //----end deklarasi






    public frg_search() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frg_search.
     */
    // TODO: Rename and change types and number of parameters
    public static frg_search newInstance(String param1, String param2) {
        frg_search fragment = new frg_search();
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

        return inflater.inflate(R.layout.fragment_frg_search, container, false);
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

    //--myikc--

    class myikc extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }


        protected String doInBackground(String... args) {


            return null;
        }

        protected void onPostExecute(String file_url) {





            Button btn_search = (Button) getView().findViewById(R.id.btnSearch);
            btn_search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do something

                    //Toast.makeText(getActivity(), "Button Save Clicked", Toast.LENGTH_LONG).show();

                    EditText etKriteria = (EditText) getView().findViewById(R.id.txtKriteria);
                    final String setKriteria=etKriteria.getText().toString();


                    if(setKriteria.trim().length()==0)
                    {
                        pesandialog("Entrian Kriteria tidak boleh kosong ...!");
                        etKriteria.requestFocus();
                        return;

                    }


                    //------volley

                    final String REGISTER_URL = "http://192.168.43.4/aflowz/web_cari.php?kriteria="+setKriteria;


                    JsonObjectRequest jsonRequest = new JsonObjectRequest
                            (Request.Method.GET, REGISTER_URL, null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // the response is already constructed as a JSONObject!

                                    //Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG).show();

                                    //---proses data response

                                    try {

                                        //istatus=response.getInt("success");
                                        JSONArray ja = response.getJSONArray("datauser");

                                        results.clear();
                                        nom=0;


                                        for (int i = 0; i < ja.length(); i++) {

                                            JSONObject c = ja.getJSONObject(i);



                                            // Storing each json item in variable
                                            nom=nom+1;

                                            id_rec = c.getString("id_rec");
                                            user_id = c.getString("user_id");
                                            user_name = c.getString("user_name");
                                            department = c.getString("department");
                                            user_sex = c.getString("user_sex");
                                            role_admin = c.getString("role_admin");
                                            role_user = c.getString("role_user");
                                            img_user = c.getString("img_user");



                                            sr1 = new SearchResults();
                                            sr1.set_id_rec(id_rec);
                                            sr1.set_user_id(user_id);
                                            sr1.set_user_name(user_name);
                                            sr1.set_department(department);
                                            sr1.set_user_sex(user_sex);
                                            sr1.set_role_admin(role_admin);
                                            sr1.set_role_user(role_user);
                                            sr1.set_img_user(img_user);



                                            results.add(sr1);


                                        }



                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    TampilListUser();
                                    //------------------------
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getActivity(),"Server :" + error.toString(), Toast.LENGTH_LONG).show();
                                }
                            }){


                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                    requestQueue.add(jsonRequest);

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



    //--endMyIkc--

    public class SearchResults {
        private String id_rec = "";
        private String user_id = "";
        private String user_name = "";
        private String department = "";
        private String user_sex = "";
        private String role_admin = "";
        private String role_user = "";
        private String img_user = "";



        //------id_rec
        public void set_id_rec(String id_rec) {
            this.id_rec = id_rec;
        }
        public String get_id_rec() {
            return id_rec;
        }

        //------user_id
        public void set_user_id(String user_id) {
            this.user_id = user_id;
        }
        public String get_user_id() {
            return user_id;
        }

        //------user_name
        public void set_user_name(String user_name) {
            this.user_name = user_name;
        }
        public String get_user_name() {
            return user_name;
        }
        //------department
        public void set_department(String department) {
            this.department = department;
        }
        public String get_department() {
            return department;
        }

        //------user_sex
        public void set_user_sex(String user_sex) {
            this.user_sex = user_sex;
        }
        public String get_user_sex() {
            return user_sex;
        }

        //------role_admin
        public void set_role_admin(String role_admin) {
            this.role_admin = role_admin;
        }
        public String get_role_admin() {
            return role_admin;
        }


        //------role_user
        public void set_role_user(String role_user) {
            this.role_user = role_user;
        }
        public String get_role_user() {
            return role_user;
        }

        //------img_user
        public void set_img_user(String img_user) {
            this.img_user = img_user;
        }
        public String get_img_user() {
            return img_user;
        }



    }

    public class MyCustomBaseAdapter extends BaseAdapter {
        private ArrayList<SearchResults> searchArrayList;

        private LayoutInflater mInflater;

        public MyCustomBaseAdapter(Context context, ArrayList<SearchResults> results) {
            searchArrayList = results;
            mInflater = LayoutInflater.from(context);
        }

        public int getCount() {
            return searchArrayList.size();
        }

        public Object getItem(int position) {
            return searchArrayList.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.datauser_list_view, null);
                holder = new ViewHolder();
                holder.txt_id_rec = (TextView) convertView.findViewById(R.id.txtIdRec);
                holder.txt_user_id = (TextView) convertView.findViewById(R.id.txtUserId);
                holder.txt_user_name = (TextView) convertView.findViewById(R.id.txtUserName);
                holder.txt_department = (TextView) convertView.findViewById(R.id.txtDepartment);
                holder.txt_user_admin = (TextView) convertView.findViewById(R.id.txtRoleAdmin);
                holder.txt_user_role = (TextView) convertView.findViewById(R.id.txtRoleUser);

                holder.imgPhoto = (ImageView) convertView.findViewById(R.id.imgUser);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.txt_id_rec.setText(searchArrayList.get(position).get_id_rec());
            holder.txt_user_id.setText(searchArrayList.get(position).get_user_id());
            holder.txt_user_name.setText(searchArrayList.get(position).get_user_name());
            holder.txt_department.setText(searchArrayList.get(position).get_department());
            holder.txt_user_admin.setText(searchArrayList.get(position).get_role_admin());
            holder.txt_user_role.setText(searchArrayList.get(position).get_role_user());


            if(searchArrayList.get(position).get_role_admin().equals("true"))
            {
                holder.txt_user_admin.setText("Admin");
            }
            else
            {
                holder.txt_user_admin.setText("Not Admin");
            }

            if(searchArrayList.get(position).get_role_user().equals("true"))
            {
                holder.txt_user_role.setText("User");
            }
            else
            {
                holder.txt_user_role.setText("Not User");
            }


            if(searchArrayList.get(position).get_img_user().equals("tidak ada"))
            {
                if(searchArrayList.get(position).get_user_sex().equals("Male"))
                {
                    holder.imgPhoto.setImageResource(R.drawable.male);
                    holder.imgPhoto.setTag("Male");
                }
                else
                {
                    holder.imgPhoto.setImageResource(R.drawable.female);
                    holder.imgPhoto.setTag("Female");
                }

            }
            else
            {
                String url_photo_oke="http://192.168.1.7/aflowz"+ searchArrayList.get(position).get_img_user();
                Glide.with(getActivity())
                        .load(url_photo_oke)
                        .into(holder.imgPhoto);
            }








            return convertView;
        }

        class ViewHolder {
            ImageView imgPhoto;
            TextView txt_id_rec;
            TextView txt_user_id;
            TextView txt_user_name;
            TextView txt_department;
            TextView txt_user_admin;
            TextView txt_user_role;


        }


    }

    private void TampilListUser()
    {

        ArrayList<SearchResults> searchResults = results;


        final ListView lv1 = (ListView) getView().findViewById(R.id.lvDataUser);
        lv1.setAdapter(new MyCustomBaseAdapter(getActivity(), searchResults));


        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {


                //Toast.makeText(getActivity(), "User Name : " + user_name +" clicking ...", Toast.LENGTH_LONG).show();
                //--kirim data ke frg_edit




                String bid_rec = ((TextView) view.findViewById(R.id.txtIdRec)).getText().toString();
                String buser_id = ((TextView) view.findViewById(R.id.txtUserId)).getText().toString();
                String buser_name = ((TextView) view.findViewById(R.id.txtUserName)).getText().toString();
                String bdepartment = ((TextView) view.findViewById(R.id.txtDepartment)).getText().toString();

                ImageView imgUser = ((ImageView) view.findViewById(R.id.imgUser));

                String buser_sex = imgUser.getTag().toString();




                String brole_admin = ((TextView) view.findViewById(R.id.txtRoleAdmin)).getText().toString();
                String brole_user = ((TextView) view.findViewById(R.id.txtRoleUser)).getText().toString();


                Bundle bundle_search = new Bundle();
                bundle_search.putString("id_rec", bid_rec);
                bundle_search.putString("user_id", buser_id);
                bundle_search.putString("user_name", buser_name);
                bundle_search.putString("department", bdepartment);
                bundle_search.putString("user_sex", buser_sex);
                bundle_search.putString("role_admin", brole_admin);
                bundle_search.putString("role_user", brole_user);


                //set Fragmentclass Arguments


                android.support.v4.app.Fragment mFragment = null;
                mFragment = frg_edit.newInstance(bundle_search);
                mFragment.setArguments(bundle_search);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frm_utama, mFragment)
                        .addToBackStack(null)
                        .commit();


            }
        });



    }


}
