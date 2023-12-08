package carlos.dara.kaua.raynan.reciclamais.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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

        botaoReciclarVidro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirInformacoesMaterialActivity("BannerVidro");
            }
        });

        botaoReciclarPlastico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirInformacoesMaterialActivity("BannerPlastico");
            }
        });

        botaoReciclarMadeira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirInformacoesMaterialActivity("BannerMadeira");
            }
        });

        botaoReciclarEletronico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirInformacoesMaterialActivity("BannerEletronico");
            }
        });

        botaoReciclarMetal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirInformacoesMaterialActivity("BannerMetal");
            }
        });

        botaoReciclarPapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirInformacoesMaterialActivity("BannerPapel");
            }
        });

        botaoReciclarOrganico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirInformacoesMaterialActivity("BannerOrganico");
            }
        });

        botaoReciclarHospitalar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirInformacoesMaterialActivity("BannerHospitalar");
            }
        });
    }

    private void abrirInformacoesMaterialActivity(String banner) {
        Intent intent = new Intent(AprendaReciclarActivity.this, InformacoesMaterialActivity.class);
        intent.putExtra("banner", banner);
        startActivity(intent);
    }
}
