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
import carlos.dara.kaua.raynan.reciclamais.activity.MainActivity;
import carlos.dara.kaua.raynan.reciclamais.entities.PontoColeta;
import carlos.dara.kaua.raynan.reciclamais.util.ImageCache;

public class MyAdapter extends PagingDataAdapter<PontoColeta, MyViewHolder> {
    MainActivity mainActivity;
    public MyAdapter(MainActivity mainActivity, @NonNull DiffUtil.ItemCallback<PontoColeta> diffCallback) {
        super(diffCallback);
        this.mainActivity = mainActivity;
    }

    /**
     * Cria os elementos de UI referente a um item da lista
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.component_ponto_coleta, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    /**
     * Preenche um item da lista
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PontoColeta pontoColeta = this.getItem(position);

        // preenche o campo de nome
        TextView tvNameList = holder.itemView.findViewById(R.id.tv_nome_ponto_coleta_componente);
        tvNameList.setText(pontoColeta.nome);

        // preenche o campo de preço
        TextView tvPiceList = holder.itemView.findViewById(R.id.tv_materiais_reciclados_componente);
        tvPiceList.setText(pontoColeta.materiaisRecicladosBD);

        // preenche o campo de foto
        int w = (int) mainActivity.getResources().getDimension(R.dimen.thumb_width);
        int h = (int) mainActivity.getResources().getDimension(R.dimen.thumb_height);
        ImageView imvProductThumb = holder.itemView.findViewById(R.id.imv_foto_ponto_de_coleta_ponto_coleta);
        // somente agora o a imagem é obtida do servidor. Caso a imagem já esteja salva no cache da app,
        // não baixamos ela de novo
        ImageCache.loadImageUrlToImageView(mainActivity, pontoColeta.URLimagem, imvProductThumb, w, h);

        // ao clicar em um item da lista, navegamos para a tela que mostra os detalhes do produto
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.startViewPontoColetaAcitivity(String.valueOf(pontoColeta.id));
            }
        });
    }
}