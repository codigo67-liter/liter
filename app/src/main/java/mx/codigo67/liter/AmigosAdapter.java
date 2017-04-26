package mx.codigo67.liter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AmigosAdapter extends RecyclerView.Adapter<AmigosAdapter.PromoViewHolder> {

    private List<AmigosViewModel> items;
    private int itemLayout;
    private static Context context;

    public AmigosAdapter(List<AmigosViewModel> promosList, int itemLayout, Context ctx) {
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
        AmigosViewModel item = items.get(position);
        PromoViewHolder.currentItem = items.get(position);

        PromoViewHolder.posicion.setText(item.getTitulo());
        PromoViewHolder.nombre.setText(item.getNombre());
        PromoViewHolder.puntos.setText(item.getPuntos());

        PromoViewHolder.imagen.setImageBitmap(item.getImagen());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class PromoViewHolder extends RecyclerView.ViewHolder {

        protected TextView posicion;
        protected ImageView imagen;
        protected TextView nombre;
        protected TextView puntos;

        public AmigosViewModel currentItem;

        public PromoViewHolder(View v) {
            super(v);

            posicion = (TextView) v.findViewById(R.id.tv_posicion);
            imagen = (ImageView) v.findViewById(R.id.iv_foto);
            nombre = (TextView) v.findViewById(R.id.tv_nombre);
            puntos = (TextView) v.findViewById(R.id.tv_bottles);

        }
    }

}
