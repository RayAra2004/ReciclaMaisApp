package carlos.dara.kaua.raynan.reciclamais.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import carlos.dara.kaua.raynan.reciclamais.R;

public class MudarDadosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mudar_dados);

        EditText editTextNome = findViewById(R.id.editText_nome_mudar_dados);
        Spinner spinnerDiaNascimento = findViewById(R.id.sp_dia_data_de_nascimento_mudar_dados);
        Spinner spinnerMesNascimento = findViewById(R.id.sp_mes_data_de_nascimento_mudar_dados);
        Spinner spinnerAnoNascimento = findViewById(R.id.sp_ano_data_de_nascimento_mudar_dados);
        EditText editTextTelefone = findViewById(R.id.editText_telefone_mudar_dados);
        EditText editTextSenha = findViewById(R.id.editText_senha_mudar_dados);
        EditText editTextConfirmeSenha = findViewById(R.id.editText_confirme_sua_senha_mudar_dados);
        Button botaoAtualizarDados = findViewById(R.id.btn_atualizar_mudar_dados);

        botaoAtualizarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = editTextNome.getText().toString();
                String telefone = editTextTelefone.getText().toString();
                String senha = editTextSenha.getText().toString();
                String confimarSenha = editTextConfirmeSenha.getText().toString();

                if (nome.isEmpty()){
                    editTextNome.setError("Campo nome é obrigatório");
                    return;
                }
                if (telefone.isEmpty() || telefone.length() < 10){
                    editTextTelefone.setError("Telefone inválido");
                    return;
                }
                if (senha.isEmpty() || senha.length() < 8){
                    editTextSenha.setError("A senha deve conter pelo menos 8 caracteres");
                    return;
                }
                if (!senha.equals(confimarSenha)){
                    editTextConfirmeSenha.setError("As senhas não coincidem");
                    return;
                }

            }
        });

    }
}