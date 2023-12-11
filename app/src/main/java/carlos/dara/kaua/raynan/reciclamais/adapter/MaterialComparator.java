package carlos.dara.kaua.raynan.reciclamais.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import carlos.dara.kaua.raynan.reciclamais.entities.Material;

public class MaterialComparator extends DiffUtil.ItemCallback<Material>{
    public boolean areItemsTheSame(@NonNull Material oldItem, @NonNull Material newItem) {
        return oldItem.id == newItem.id;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Material oldItem, @NonNull Material newItem) {
        return oldItem.id.equals(newItem.id) &&
                oldItem.imageURL.equals(newItem.imageURL);
    }
}
