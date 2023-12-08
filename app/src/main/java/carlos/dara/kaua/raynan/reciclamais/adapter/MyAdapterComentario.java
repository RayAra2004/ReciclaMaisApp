package carlos.dara.kaua.raynan.reciclamais.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;

import carlos.dara.kaua.raynan.reciclamais.R;
import carlos.dara.kaua.raynan.reciclamais.activity.DescricaoPontoActivity;
import carlos.dara.kaua.raynan.reciclamais.activity.MainActivity;
import carlos.dara.kaua.raynan.reciclamais.entities.Comentario;
import carlos.dara.kaua.raynan.reciclamais.entities.PontoColeta;


public class MyAdapterComentario extends PagingDataAdapter<Comentario, MyViewHolder> {
    DescricaoPontoActivity descricaoPontoActivity;
    public MyAdapterComentario(DescricaoPontoActivity mainActivity, @NonNull DiffUtil.ItemCallback<Comentario> diffCallback) {
        super(diffCallback);
        this.descricaoPontoActivity = mainActivity;
    }

    /**
     * Cria os elementos de UI referente a um item da lista
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.component_comentario, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    /**
     * Preenche um item da lista
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Comentario comentario = this.getItem(position);

        // preenche o campo de nome
        TextView tvNameList = holder.itemView.findViewById(R.id.tv_nome_pessoa_comentario);
        tvNameList.setText(comentario.nome);

        TextView tvDescricaoList = holder.itemView.findViewById(R.id.tv_comentario_comentario);
        tvDescricaoList.setText(comentario.descricao);

        ImageView imageView = holder.itemView.findViewById(R.id.imv_nota_comentario);

        switch (comentario.nota){
            case 1:
                imageView.setImageResource(R.drawable.estrela_um);
                break;
            case 2:
                imageView.setImageResource(R.drawable.estrela_dois);
                break;
            case 3:
                imageView.setImageResource(R.drawable.estrela_tres);
                break;
            case 4:
                imageView.setImageResource(R.drawable.estrela_quatro);
                break;
            case 5:
                imageView.setImageResource(R.drawable.estrela_cinco);
                break;
        }


    }
}
