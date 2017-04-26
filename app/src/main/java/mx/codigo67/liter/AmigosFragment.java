package mx.codigo67.liter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class AmigosFragment extends Fragment {

    RecyclerView mRecyclerView;
    List<AmigosViewModel> amigos;
    AmigosAdapter amigosAdapter;

    public static AmigosFragment newInstance() {
        AmigosFragment fragment = new AmigosFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_amigos, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_amigos);
        mRecyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 1);
        mRecyclerView.setLayoutManager(layoutManager);

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

            retrieveFriends();

        }

        return view;
    }

    public void retrieveFriends() {

        amigos = new ArrayList<>();

        amigos.add(new AmigosViewModel(
                "1.",
                BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.foto1),
                "Jacqueline Lexj", "47"));

        amigos.add(new AmigosViewModel(
                "2.",
                BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.foto2),
                "Eduardo Leal", "42"));

        amigos.add(new AmigosViewModel(
                "3.",
                BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.foto3),
                "Mariana Cota", "34"));

        amigos.add(new AmigosViewModel(
                "4.",
                BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.foto4),
                "Elisa Lopez", "21"));

        amigos.add(new AmigosViewModel(
                "5.",
                BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.foto5),
                "Kevin Dominguez", "12"));

        if (AccessToken.getCurrentAccessToken() != null) {
            getFbFriends();
        } else {
            amigosAdapter = new AmigosAdapter(amigos, R.layout.item_amigo, getActivity());
            mRecyclerView.setAdapter(amigosAdapter);
        }

    }

    public void getFbFriends() {

        GraphRequest request = GraphRequest.newMyFriendsRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONArrayCallback() {
                    @Override
                    public void onCompleted(JSONArray array, GraphResponse response) {

                        for (int i = 0; i < array.length(); i++) {
                            try {
                                Bitmap picture = getBitmapFromURL(array.getJSONObject(i).getString("id"));
                                String name = array.getJSONObject(i).getString("name");

                                int pos = i + 6;
                                String position = String.valueOf(pos) + ".";

                                amigos.add(new AmigosViewModel(position, picture, name, "7"));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        amigosAdapter = new AmigosAdapter(amigos, R.layout.item_amigo, getActivity());
                        mRecyclerView.setAdapter(amigosAdapter);

                    }
                });

        request.executeAsync();


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.amigos_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_salir) {
            //Do whatever you want to do
            ParseUser.logOutInBackground();
            startActivity(new Intent(getActivity(), mx.codigo67.liter.StartActivity.class));
            getActivity().finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static Bitmap getBitmapFromURL(String userID) {

        try {
            URL imageURL = new URL("https://graph.facebook.com/" + userID + "/picture?type=large");
            Log.d("*3*", imageURL.toString());
            HttpURLConnection connection = (HttpURLConnection) imageURL.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }

}