package carlos.dara.kaua.raynan.reciclamais.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Comment;

import carlos.dara.kaua.raynan.reciclamais.R;
import carlos.dara.kaua.raynan.reciclamais.entities.PontoColeta;
import carlos.dara.kaua.raynan.reciclamais.util.ImageCache;
import carlos.dara.kaua.raynan.reciclamais.viewModel.DescricaoPontoViewModel;

public class DescricaoPontoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_ponto);

        Intent i = getIntent();
        String id = i.getStringExtra("id");

        DescricaoPontoViewModel descricaoPontoViewModel = new ViewModelProvider(this).get(DescricaoPontoViewModel.class);
        LiveData<PontoColeta> pontoColeta = descricaoPontoViewModel.getPontoColetaDetailsLD(id);
        pontoColeta.observe(this, new Observer<PontoColeta>() {
            @Override
            public void onChanged(PontoColeta pontoColeta) {
                if(pontoColeta != null){
                    ImageView imvProductPhoto = findViewById(R.id.imv_ponto_selecionado_descricao_ponto);
                    int imgHeight = (int) DescricaoPontoActivity.this.getResources().getDimension(R.dimen.img_height);
                    ImageCache.loadImageUrlToImageView(DescricaoPontoActivity.this, pontoColeta.URLimagem, imvProductPhoto, -1, imgHeight);

                    TextView tvNomePontoSelecionado = findViewById(R.id.tv_nome_ponto_selecionado_descricao_ponto);
                    tvNomePontoSelecionado.setText(pontoColeta.nome);

                    TextView tvEnderecoPontoSelecionado = findViewById(R.id.tv_endereco_ponto_selecionado_descricao_ponto);
                    String endereco = Character.toUpperCase(pontoColeta.endereco.getTp_logradouro().charAt(0)) +
                            pontoColeta.endereco.getTp_logradouro().substring(1)
                            + " " + pontoColeta.endereco.getLogradouro()
                            + ", " + pontoColeta.endereco.getNumero() + " - " + pontoColeta.endereco.getBairro() +
                            ", " + pontoColeta.endereco.getCidade() + " - " + pontoColeta.endereco.getCep();
                    tvEnderecoPontoSelecionado.setText(endereco);

                    TextView tvTelefonePontoSelecionado = findViewById(R.id.tv_telefone_ponto_selecionado_descricao_ponto);
                    tvTelefonePontoSelecionado.setText(pontoColeta.telefone.toString());

                    RecyclerView rvComentariosPontoSelecionado = findViewById(R.id.rv_comentarios_descricao_ponto);
                }else {
                    Toast.makeText(DescricaoPontoActivity.this, "Não foi possível obter os detalhes do produto", Toast.LENGTH_LONG).show();
                }
            }
        });




        RatingBar rbAvaliacaoPontoSelecionado = findViewById(R.id.rb_avaliacao_em_estrela_descricao_ponto);
        EditText etAdicionarComentarioPontoSelecionado = findViewById(R.id.editText_adicionar_cometario_descricao_ponto);

        Button botaoAbrirTrajetoriaPontoSelecionado = findViewById(R.id.btn_abrir_trajetoria_descricao_ponto);

    }
} //todo adicionar botão de report