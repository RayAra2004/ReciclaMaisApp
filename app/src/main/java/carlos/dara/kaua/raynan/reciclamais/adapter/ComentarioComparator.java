package carlos.dara.kaua.raynan.reciclamais.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import carlos.dara.kaua.raynan.reciclamais.entities.Comentario;



public class ComentarioComparator extends DiffUtil.ItemCallback<Comentario>{
    @Override
    public boolean areItemsTheSame(@NonNull Comentario oldItem, @NonNull Comentario newItem) {
        return oldItem.id == newItem.id;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Comentario oldItem, @NonNull Comentario newItem) {
        return oldItem.id == newItem.id &&
                oldItem.nome.equals(newItem.nome) &&
                oldItem.descricao.equals(newItem.descricao);
    }
}
