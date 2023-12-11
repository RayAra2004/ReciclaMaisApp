package carlos.dara.kaua.raynan.reciclamais.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;

import carlos.dara.kaua.raynan.reciclamais.R;
import carlos.dara.kaua.raynan.reciclamais.activity.SeusMateriaisActivity;
import carlos.dara.kaua.raynan.reciclamais.entities.Comentario;
import carlos.dara.kaua.raynan.reciclamais.entities.Material;
import carlos.dara.kaua.raynan.reciclamais.util.ImageCache;

public class MyAdapterMaterial extends PagingDataAdapter<Material, MyViewHolder> {
    SeusMateriaisActivity seusMateriaisActivity;

    public MyAdapterMaterial(SeusMateriaisActivity seusMateriaisActivity, @NonNull DiffUtil.ItemCallback<Material> diffCallback){
        super(diffCallback);
        this.seusMateriaisActivity = seusMateriaisActivity;
    }

    /**
     * Cria os elementos de UI referente a um item da lista
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.component_material, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    /**
     * Preenche um item da lista
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Material material = this.getItem(position);

        // preenche o campo de nome
        TextView tvEstadoList = holder.itemView.findViewById(R.id.tv_estado_componete_material);
        tvEstadoList.setText(material.estado);

        // preenche o campo de foto
        int w = (int) seusMateriaisActivity.getResources().getDimension(R.dimen.thumb_width);
        int h = (int) seusMateriaisActivity.getResources().getDimension(R.dimen.thumb_height);
        ImageView imvMaterialThumb = holder.itemView.findViewById(R.id.imv_foto_material_material);
        // somente agora o a imagem é obtida do servidor. Caso a imagem já esteja salva no cache da app,
        // não baixamos ela de novo
        ImageCache.loadImageUrlToImageView(seusMateriaisActivity, material.imageURL, imvMaterialThumb, w, h);


    }
}
