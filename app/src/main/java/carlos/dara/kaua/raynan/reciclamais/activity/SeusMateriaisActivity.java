package carlos.dara.kaua.raynan.reciclamais.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import carlos.dara.kaua.raynan.reciclamais.R;

public class SeusMateriaisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seus_materiais);

        RecyclerView rvMateriaisPostados = findViewById(R.id.rv_materiais_postados_seus_materiais);
    }
}