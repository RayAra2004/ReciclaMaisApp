package carlos.dara.kaua.raynan.reciclamais.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import carlos.dara.kaua.raynan.reciclamais.entities.PontoColeta;

public class PontoColetaComparator extends DiffUtil.ItemCallback<PontoColeta>{
    @Override
    public boolean areItemsTheSame(@NonNull PontoColeta oldItem, @NonNull PontoColeta newItem) {
        return oldItem.id == newItem.id;
    }

    @Override
    public boolean areContentsTheSame(@NonNull PontoColeta oldItem, @NonNull PontoColeta newItem) {
        return oldItem.id == newItem.id &&
                oldItem.nome.equals(newItem.nome) &&
                oldItem.URLimagem.equals(newItem.URLimagem);
    }
}
