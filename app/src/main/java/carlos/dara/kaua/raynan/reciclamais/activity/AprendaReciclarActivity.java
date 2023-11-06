package carlos.dara.kaua.raynan.reciclamais.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import carlos.dara.kaua.raynan.reciclamais.R;

public class AprendaReciclarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprenda_reciclar);

        Button botaoReciclarVidro = findViewById(R.id.btn_vidro_aprenda_reciclar);
        Button botaoReciclarPlastico = findViewById(R.id.btn_plastico_aprenda_reciclar);
        Button botaoReciclarMadeira = findViewById(R.id.btn_madeira_aprenda_reciclar);
        Button botaoReciclarEletronico = findViewById(R.id.btn_lixo_eletronico_aprenda_reciclar);
        Button botaoReciclarMetal = findViewById(R.id.btn_metal_aprenda_reciclar);
        Button botaoReciclarPapel = findViewById(R.id.btn_papel_aprenda_reciclar);
        Button botaoReciclarOrganico = findViewById(R.id.btn_lixo_organico_aprenda_reciclar);
        Button botaoReciclarHospitalar = findViewById(R.id.btn_lixo_hospitalar_aprenda_reciclar);


    }
}