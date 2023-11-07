package carlos.dara.kaua.raynan.reciclamais.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import carlos.dara.kaua.raynan.reciclamais.R;

public class DescricaoPontoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_ponto);

        TextView tvNomePontoSelecionado = findViewById(R.id.tv_nome_ponto_selecionado_descricao_ponto);
        TextView tvEnderecoPontoSelecionado = findViewById(R.id.tv_endereco_ponto_selecionado_descricao_ponto);
        TextView tvTelefonePontoSelecionado = findViewById(R.id.tv_telefone_ponto_selecionado_descricao_ponto);
        RatingBar rbAvaliacaoPontoSelecionado = findViewById(R.id.rb_avaliacao_em_estrela_descricao_ponto);
        EditText etAdicionarComentarioPontoSelecionado = findViewById(R.id.editText_adicionar_cometario_descricao_ponto);
        RecyclerView rvComentariosPontoSelecionado = findViewById(R.id.rv_comentarios_descricao_ponto);
        Button botaoAbrirTrajetoriaPontoSelecionado = findViewById(R.id.btn_abrir_trajetoria_descricao_ponto);

    }
} //todo adicionar botão de report