package carlos.dara.kaua.raynan.reciclamais.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagingData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import carlos.dara.kaua.raynan.reciclamais.R;
import carlos.dara.kaua.raynan.reciclamais.adapter.ComentarioComparator;
import carlos.dara.kaua.raynan.reciclamais.adapter.MyAdapterComentario;
import carlos.dara.kaua.raynan.reciclamais.entities.Comentario;
import carlos.dara.kaua.raynan.reciclamais.entities.PontoColeta;
import carlos.dara.kaua.raynan.reciclamais.util.ImageCache;
import carlos.dara.kaua.raynan.reciclamais.viewModel.DescricaoPontoViewModel;

public class DescricaoPontoActivity extends AppCompatActivity {

    private MyAdapterComentario myAdapterComentario;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_ponto);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        System.out.println(id);
        DescricaoPontoViewModel descricaoPontoViewModel = new ViewModelProvider(this).get(DescricaoPontoViewModel.class);
        LiveData<PontoColeta> pontoColeta = descricaoPontoViewModel.getPontoColetaDetailsLD(id);
        pontoColeta.observe(this, new Observer<PontoColeta>() {
            @Override
            public void onChanged(PontoColeta pontoColeta) {
                if (pontoColeta != null) {
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

                    pontoColeta.materiasReciclados.forEach(tipoMaterial -> {
                        switch (tipoMaterial.nome) {
                            case "Plástico":
                                findViewById(R.id.tv_plastico_descricao_ponto).setVisibility(View.VISIBLE);
                                break;
                            case "Vidro":
                                findViewById(R.id.tv_vidro_descricao_ponto).setVisibility(View.VISIBLE);
                                break;
                            case "Metal":
                                findViewById(R.id.tv_metal_descricao_ponto).setVisibility(View.VISIBLE);
                                break;
                            case "Eletrônicos":
                                findViewById(R.id.tv_eletronico_descricao_ponto).setVisibility(View.VISIBLE);
                                break;
                            case "Madeira":
                                findViewById(R.id.tv_madeira_descricao_ponto).setVisibility(View.VISIBLE);
                                break;
                            case "Orgânico":
                                findViewById(R.id.tv_organico_descricao_ponto).setVisibility(View.VISIBLE);
                                break;
                            case "Hospitalar":
                                findViewById(R.id.tv_hospitalar_descricao_ponto).setVisibility(View.VISIBLE);
                                break;
                            case "Papel":
                                findViewById(R.id.tv_papel_descricao_ponto).setVisibility(View.VISIBLE);
                                break;
                        }
                    });

                    RecyclerView rvComentariosPontoSelecionado = findViewById(R.id.rv_comentarios_descricao_ponto);
                    rvComentariosPontoSelecionado.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
                    rvComentariosPontoSelecionado.setLayoutManager(layoutManager);

                    myAdapterComentario = new MyAdapterComentario(DescricaoPontoActivity.this, new ComentarioComparator());
                    rvComentariosPontoSelecionado.setAdapter(myAdapterComentario);

                    DescricaoPontoViewModel descricaoPontoViewModel1 = new DescricaoPontoViewModel(getApplication(), Integer.parseInt(id));
                    LiveData<PagingData<Comentario>> comentarioLD = descricaoPontoViewModel1.getComentariosLD();
                    comentarioLD.observe(DescricaoPontoActivity.this, new Observer<PagingData<Comentario>>() {
                        @Override
                        public void onChanged(PagingData<Comentario> comentarioPagingData) {
                            myAdapterComentario.submitData(getLifecycle(), comentarioPagingData);
                        }
                    });
                } else {
                    Toast.makeText(DescricaoPontoActivity.this, "Não foi possível obter os detalhes do ponto", Toast.LENGTH_LONG).show();
                }
            }
        });

        RatingBar rbAvaliacaoPontoSelecionado = findViewById(R.id.rb_avaliacao_em_estrela_descricao_ponto);
        EditText etAdicionarComentarioPontoSelecionado = findViewById(R.id.editText_adicionar_cometario_descricao_ponto);

        Button botaoComentar = findViewById(R.id.btn_comentar_descricao_ponto);
        botaoComentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setEnabled(false);

                int nota = (int) rbAvaliacaoPontoSelecionado.getRating();
                String comentario = etAdicionarComentarioPontoSelecionado.getText().toString();

                if (nota > 0 && !comentario.isEmpty()) {
                    LiveData<Boolean> resultLD = descricaoPontoViewModel.postComentario(comentario, nota, id);

                    resultLD.observe(DescricaoPontoActivity.this, new Observer<Boolean>() {
                        @Override
                        public void onChanged(Boolean aBoolean) {
                            if (aBoolean) {
                                view.setEnabled(true);
                                etAdicionarComentarioPontoSelecionado.setText("");
                                rbAvaliacaoPontoSelecionado.setRating(0);
                                Toast.makeText(DescricaoPontoActivity.this, "Novo comentário registrado com sucesso", Toast.LENGTH_LONG).show();
                            } else {
                                view.setEnabled(true);
                                Toast.makeText(DescricaoPontoActivity.this, "Erro ao registrar novo comentário", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } else {
                    if (nota == 0 && comentario.isEmpty()) {
                        Toast.makeText(DescricaoPontoActivity.this, "Dê uma nota e escreva um comentário antes de enviar.", Toast.LENGTH_LONG).show();
                    } else if (nota == 0) {
                        Toast.makeText(DescricaoPontoActivity.this, "É necessário avaliar o ponto!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(DescricaoPontoActivity.this, "É necessário escrever um comentário!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        Button botaoAbrirTrajetoriaPontoSelecionado = findViewById(R.id.btn_abrir_trajetoria_descricao_ponto);
        TextView tvEnderecoPontoSelecionado = findViewById(R.id.tv_endereco_ponto_selecionado_descricao_ponto);

        botaoAbrirTrajetoriaPontoSelecionado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtenha o endereço do TextView
                String endereco = tvEnderecoPontoSelecionado.getText().toString();

                // Crie a URI para abrir o Google Maps com a rota
                String uri = "http://maps.google.com/maps?daddr=" + endereco;

                // Crie um Intent para abrir o Google Maps
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, android.net.Uri.parse(uri));
                mapIntent.setPackage("com.google.android.apps.maps");

                // Verifique se há aplicativos que podem responder a este intent
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                } else {
                    // Se não houver aplicativos que possam responder a este intent, exiba uma mensagem
                    Toast.makeText(DescricaoPontoActivity.this, "Não foi possível abrir o Google Maps.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
