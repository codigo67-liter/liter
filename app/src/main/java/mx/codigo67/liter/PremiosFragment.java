package mx.codigo67.liter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class PremiosFragment extends Fragment {

    RecyclerView mRecyclerView;
    List<PremiosViewModel> promos;
    PremiosAdapter promosAdapter;

    ProgressBar indicador;

    public static PremiosFragment newInstance() {
        PremiosFragment fragment = new PremiosFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_premios, container, false);

        indicador = (ProgressBar) view.findViewById(R.id.pb_indicador);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_promos);
        mRecyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 1);
        mRecyclerView.setLayoutManager(layoutManager);

        retrievePromos();

        return view;
    }

    public void retrievePromos() {

        promos = new ArrayList<>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Premio");
        query.orderByAscending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> premios, ParseException e) {
                if (e == null) {

                    if (premios.size() != 0) {

                        for (int i = 0; i < premios.size(); i++) {
                            promos.add(new PremiosViewModel(premios.get(i).getString("premioTitulo"), premios.get(i).getParseFile("imagen").getUrl(), premios.get(i).getString("premioMonedas")));
                        }

                        promosAdapter = new PremiosAdapter(promos, R.layout.item_promo, getActivity());
                        mRecyclerView.setAdapter(promosAdapter);

                        indicador.setVisibility(View.GONE);
                        mRecyclerView.setVisibility(View.VISIBLE);

                    } else {
                        //TODO - Enviar una alerta de que No hay Promos
                    }
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

    }
}