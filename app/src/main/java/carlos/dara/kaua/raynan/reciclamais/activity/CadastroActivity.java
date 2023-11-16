package carlos.dara.kaua.raynan.reciclamais.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

        // TESTANDO VALIDAÇÃO SIMPLES
        botaoFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = etNome.getText().toString();
                String telefone = etTelefone.getText().toString();
                String email = etEmail.getText().toString();
                String senha = etSenha.getText().toString();
                String confimarSenha = etConfirmarSenha.getText().toString();
                String diaSelecionado = spDiaNascimento.getSelectedItem().toString();
                String mesSelecionado = spMesNascimento.getSelectedItem().toString();
                String anoSelecionado = spAnoNascimento.getSelectedItem().toString();

                if (nome.isEmpty()){
                    etNome.setError("Campo nome é obrigatório");
                    return;
                }
                if (TextUtils.isEmpty(diaSelecionado) || TextUtils.isEmpty(mesSelecionado) || TextUtils.isEmpty(anoSelecionado)){
                    Toast.makeText(getApplicationContext(), "Por favor, selecione a data de nascimento completa", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (telefone.isEmpty() || telefone.length() < 10){
                    etTelefone.setError("Telefone inválido");
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    etEmail.setError("Email inválido");
                    return;
                }
                if (senha.isEmpty() || senha.length() < 8){
                    etSenha.setError("A senha deve conter pelo menos 8 caracteres");
                    return;
                }
                if (!senha.equals(confimarSenha)){
                    etConfirmarSenha.setError("As senhas não coincidem");
                    return;
                }
            }
        });
        // FIM DO TESTE DA VALIDAÇÃO SIMPLES


    }
}