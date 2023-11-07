package carlos.dara.kaua.raynan.reciclamais.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    }
}