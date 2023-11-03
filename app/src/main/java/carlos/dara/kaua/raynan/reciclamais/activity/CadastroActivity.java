package carlos.dara.kaua.raynan.reciclamais.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import carlos.dara.kaua.raynan.reciclamais.R;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        EditText editTextNome = findViewById(R.id.editText_nome_cadastro);
        Spinner spinnerDiaNascimento = findViewById(R.id.sp_dia_data_de_nascimento_cadastro);
        Spinner spinnerMesNascimento = findViewById(R.id.sp_mes_data_de_nascimento_cadastro);
        Spinner spinnerAnoNascimento = findViewById(R.id.sp_ano_data_de_nascimento_cadastro);
        EditText editTextTelefone = findViewById(R.id.editText_telefone_cadastro);
        EditText editTextEmail = findViewById(R.id.editText_email_cadastro);
        EditText editTextSenha = findViewById(R.id.editText_senha_cadastro);
        EditText editTextConfirmarSenha = findViewById(R.id.editText_confirme_sua_senha_cadastro);
        Button botaoFinalizar = findViewById(R.id.btn_cadastrar_cadastro);


    }
}