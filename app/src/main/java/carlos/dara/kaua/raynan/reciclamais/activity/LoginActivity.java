package carlos.dara.kaua.raynan.reciclamais.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import carlos.dara.kaua.raynan.reciclamais.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText editTextEmail = findViewById(R.id.editText_digite_seu_email_login);
        EditText editTextSenha = findViewById(R.id.editText_digite_sua_senha_login);
        TextView textViewEsqueceuSenha = findViewById(R.id.tv_esqueceu_senha_login);
        Button botaoLogin = findViewById(R.id.btn_login_login);
        Button botaoCadastrar = findViewById(R.id.btn_cadastrar_login);

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString();
                String senha = editTextSenha.getText().toString();

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    editTextEmail.setError("Email inválido");
                    return;
                }
                if (senha.isEmpty() || senha.length() < 8){
                    editTextSenha.setError("Senha deve conter no mínimo 8 caracteres");
                }
            }
        });


    }
}