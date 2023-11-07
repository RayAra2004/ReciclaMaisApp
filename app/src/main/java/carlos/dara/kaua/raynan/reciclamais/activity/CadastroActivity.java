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

        EditText etNome = findViewById(R.id.editText_nome_cadastro);
        Spinner spDiaNascimento = findViewById(R.id.sp_dia_data_de_nascimento_cadastro);
        Spinner spMesNascimento = findViewById(R.id.sp_mes_data_de_nascimento_cadastro);
        Spinner spAnoNascimento = findViewById(R.id.sp_ano_data_de_nascimento_cadastro);
        EditText etTelefone = findViewById(R.id.editText_telefone_cadastro);
        EditText etEmail = findViewById(R.id.editText_email_cadastro);
        EditText etSenha = findViewById(R.id.editText_senha_cadastro);
        EditText etConfirmarSenha = findViewById(R.id.editText_confirme_sua_senha_cadastro);
        Button botaoFinalizar = findViewById(R.id.btn_cadastrar_cadastro);


    }
}