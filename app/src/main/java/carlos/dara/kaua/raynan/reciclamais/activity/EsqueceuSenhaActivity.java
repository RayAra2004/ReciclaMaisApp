package carlos.dara.kaua.raynan.reciclamais.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import carlos.dara.kaua.raynan.reciclamais.R;

public class EsqueceuSenhaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueceu_senha);

        EditText etDigiteCodigoConfirmacao = findViewById(R.id.editText_digite_o_codigo_esqueceu_senha);
        EditText etDigiteNovaSenha = findViewById(R.id.editText_senha_nova_esqueceu_senha);
        EditText etConfirmeSenhaNova = findViewById(R.id.editText_confirme_senha_esqueceu_senha);
        Button botaoFinalizar = findViewById(R.id.btn_finalizar_esqueceu_senha);

        botaoFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Codigoconfirmacao = etDigiteCodigoConfirmacao.getText().toString();
                String NovaSenha = etDigiteNovaSenha.getText().toString();
                String ConfirmeSenhaNova = etConfirmeSenhaNova.getText().toString();

                if (Codigoconfirmacao.isEmpty()){
                    etDigiteCodigoConfirmacao.setError("Campo obrigatório");
                    return;
                }
                if (NovaSenha.isEmpty() || NovaSenha.length() < 8){
                    etDigiteNovaSenha.setError("senha deve conter no mínimo 8 caracteres");
                    return;
                }
                if (!NovaSenha.equals(ConfirmeSenhaNova)){
                    etConfirmeSenhaNova.setError("As senhas não coincidem");
                    return;
                }
            }
        });
    }
}