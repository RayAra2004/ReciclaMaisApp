package carlos.dara.kaua.raynan.reciclamais.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagingData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import carlos.dara.kaua.raynan.reciclamais.R;
import carlos.dara.kaua.raynan.reciclamais.adapter.MaterialComparator;
import carlos.dara.kaua.raynan.reciclamais.adapter.MyAdapterMaterial;
import carlos.dara.kaua.raynan.reciclamais.adapter.MyAdapterPontoColeta;
import carlos.dara.kaua.raynan.reciclamais.adapter.PontoColetaComparator;
import carlos.dara.kaua.raynan.reciclamais.entities.Material;
import carlos.dara.kaua.raynan.reciclamais.entities.PontoColeta;
import carlos.dara.kaua.raynan.reciclamais.viewModel.MainViewModel;
import carlos.dara.kaua.raynan.reciclamais.viewModel.SeusMateriaisViewModel;

public class SeusMateriaisActivity extends AppCompatActivity {

    MyAdapterMaterial myAdapterMaterial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seus_materiais);

        RecyclerView rvMateriaisPostados = findViewById(R.id.rv_materiais_postados_seus_materiais);

        rvMateriaisPostados.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvMateriaisPostados.setLayoutManager(layoutManager);

        myAdapterMaterial = new MyAdapterMaterial(this, new MaterialComparator());
        rvMateriaisPostados.setAdapter(myAdapterMaterial);

        SeusMateriaisViewModel seusMateriaisViewModel = new SeusMateriaisViewModel(getApplication());

        LiveData<PagingData<Material>> materialLD = seusMateriaisViewModel.getMateriaisLD();

        materialLD.observe(this, new Observer<PagingData<Material>>() {
            @Override
            public void onChanged(PagingData<Material> materialPagingData) {
                myAdapterMaterial.submitData(getLifecycle(), materialPagingData);
            }
        });
    }
}