package mx.codigo67.liter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PremiosAdapter extends RecyclerView.Adapter<PremiosAdapter.PromoViewHolder> {

    private List<PremiosViewModel> items;
    private int itemLayout;
    private static Context context;

    public PremiosAdapter(List<PremiosViewModel> promosList, int itemLayout, Context ctx) {
        this.items = promosList;
        this.itemLayout = itemLayout;
        this.context = ctx;
    }

    @Override
    public PromoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayout, viewGroup, false);
        return new PromoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PromoViewHolder PromoViewHolder, int position) {
        PremiosViewModel item = items.get(position);
        PromoViewHolder.currentItem = items.get(position);

        PromoViewHolder.titulo.setText(item.getTitulo());

        Picasso.with(context)
                .load(item.getImagen())
                .into(PromoViewHolder.imagen);

        PromoViewHolder.puntos.setText(item.getPuntos() + " monedas");

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class PromoViewHolder extends RecyclerView.ViewHolder {

        protected TextView titulo;
        protected ImageView imagen;
        protected TextView puntos;

        public PremiosViewModel currentItem;

        public PromoViewHolder(View v) {
            super(v);

            titulo = (TextView) v.findViewById(R.id.titulo);
            imagen = (ImageView) v.findViewById(R.id.imagen);
            puntos = (TextView) v.findViewById(R.id.puntos);

        }
    }

}
