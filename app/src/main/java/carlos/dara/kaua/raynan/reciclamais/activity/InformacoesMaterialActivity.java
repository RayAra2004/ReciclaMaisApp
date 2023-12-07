package carlos.dara.kaua.raynan.reciclamais.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import carlos.dara.kaua.raynan.reciclamais.R;

public class InformacoesMaterialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes_material);

        ImageView imvBannerMaterial = findViewById(R.id.imv_banner_materiais);

        // Obtém o banner da Intent
        String banner = getIntent().getStringExtra("banner");

        // Define a imagem com base no banner
        if (banner != null) {
            switch (banner) {
                case "BannerVidro":
                    imvBannerMaterial.setImageResource(R.drawable.banner_vidro);
                    break;
                case "BannerPlastico":
                    imvBannerMaterial.setImageResource(R.drawable.banner_plastico);
                    break;
                case "BannerMadeira":
                    imvBannerMaterial.setImageResource(R.drawable.banner_madeira);
                    break;
                case "BannerEletronico":
                    imvBannerMaterial.setImageResource(R.drawable.banner_eletronicos);
                    break;
                case "BannerMetal":
                    imvBannerMaterial.setImageResource(R.drawable.banner_metal);
                    break;
                case "BannerPapel":
                    imvBannerMaterial.setImageResource(R.drawable.banner_papel);
                    break;
                case "BannerOrganico":
                    imvBannerMaterial.setImageResource(R.drawable.banner_lixoorganico);
                    break;
                case "BannerHospitalar":
                    imvBannerMaterial.setImageResource(R.drawable.banner_lixohospitalar);
                    break;
                default:
                    // Lógica para tratar banners desconhecidos
                    break;
            }
        }
    }
}
