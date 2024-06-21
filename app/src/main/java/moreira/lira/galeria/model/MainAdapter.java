package moreira.lira.galeria.model;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import moreira.lira.galeria.MainActivity;
import moreira.lira.galeria.R;
import moreira.lira.galeria.Util;

public class MainAdapter extends RecyclerView.Adapter {
    MainActivity mainActivity;
    List<String> photos;

    public MainAdapter(MainActivity mainActivity, List<String> photos) {
        this.mainActivity = mainActivity;
        this.photos = photos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //obtendo o inlador de layout
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        //criando os elementos de interface necesários
        View v = inflater.inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ImageView imPhoto = holder.itemView.findViewById(R.id.imItem);
        //valores que vão ser recebidos nas dimensões
        int w = (int)
                mainActivity.getResources().getDimension(R.dimen.itemWidth);
        int h = (int)
                mainActivity.getResources().getDimension(R.dimen.itemHeight);
        // carrega a imagem em um Bitmap
        Bitmap bitmap = Util.getBitmap(photos.get(position), w, h);
        imPhoto.setImageBitmap(bitmap);

        //é definido oq acontece após o click do botão
        imPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.starPhotoActivity(photos.get(position));
            }

        });
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }
}