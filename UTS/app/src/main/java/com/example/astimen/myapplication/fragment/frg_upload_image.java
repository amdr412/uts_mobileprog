package com.example.astimen.myapplication.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.astimen.myapplication.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import static android.R.attr.bitmap;
import static com.example.astimen.myapplication.R.id.imageView;
import static com.example.astimen.myapplication.R.id.imgGambarTerpilih;
import static com.example.astimen.myapplication.R.id.txtNamaGambar;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link frg_upload_image.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link frg_upload_image#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frg_upload_image extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    private ImageView imageView;

    private EditText editTextName;

    private Bitmap bitmap;

    private int PICK_IMAGE_REQUEST = 1;


    private String id_rec="";




    private String server_path = "";
    private String UPLOAD_URL="";





    public frg_upload_image() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frg_upload_image.
     */
    // TODO: Rename and change types and number of parameters
    public static frg_upload_image newInstance(String param1, String param2) {
        frg_upload_image fragment = new frg_upload_image();
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
        return inflater.inflate(R.layout.fragment_frg_upload_image, container, false);
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

        protected void onPostExecute(String file_url)
        {

            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Upload Photo");

            TextView txtUser = (TextView) getView().findViewById(R.id.txtIdRecUser);
            txtUser.setText(mParam1 + " ("+mParam2+")");
            txtUser.setGravity(Gravity.CENTER);

            editTextName = (EditText)  getView().findViewById(R.id.txtNamaGambar);
            editTextName.setText("img_"+mParam2);

            id_rec=mParam1;


            imageView  = (ImageView)  getView().findViewById(imgGambarTerpilih);

            Button btnLoad = (Button) getView().findViewById(R.id.btnPilihGambar);
            btnLoad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do something

                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

                }
            });

            Button btnUpLoad = (Button) getView().findViewById(R.id.btnUploadGambar);
            btnUpLoad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do something

                    uploadImage();
                }
            });





        }



    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);

                //Setting the Bitmap to ImageView
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage(){


        // cek bitmap size

        int ukuran=bitmap.getByteCount();
        ukuran=ukuran/(1024*16);

        if(ukuran>600)
        {
            Toast.makeText(getActivity(),"Ukuran File Gambar terlalu besar, gunakan ScreenShots Gadget untuk memperkecil ....", Toast.LENGTH_LONG).show();
            return;
        }


        //showToast(String.valueOf(bitmap.getByteCount()));


        //Showing the progress dialog


        String UPLOAD_URL ="http://192.168.1.7/aflowz/app_upload/upload.php";



        final ProgressDialog loading = ProgressDialog.show(getActivity(),"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(getActivity(), s , Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(getActivity(),"File telah diuplaod ...", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                //Converting Bitmap to String
                String image_file = getStringImage(bitmap);

                //Getting Image Name
                String nama_file = editTextName.getText().toString().trim();

                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put("d_image_file", image_file);
                params.put("d_nama_file", nama_file);
                params.put("d_id_rec_master", id_rec);
                params.put("d_nama_proses", "photo");

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    public String getStringImage(Bitmap bmp){

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 70, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

}
