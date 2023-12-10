package carlos.dara.kaua.raynan.reciclamais.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import carlos.dara.kaua.raynan.reciclamais.R;
import carlos.dara.kaua.raynan.reciclamais.util.Config;
import carlos.dara.kaua.raynan.reciclamais.viewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent i = getIntent();
        String fragmentRetorno = i.getStringExtra("fragment");

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        EditText editTextEmail = findViewById(R.id.editText_digite_seu_email_login);
        EditText editTextSenha = findViewById(R.id.editText_digite_sua_senha_login);
        TextView textViewEsqueceuSenha = findViewById(R.id.tv_esqueceu_senha_login);
        Button botaoLogin = findViewById(R.id.btn_login_login);
        Button botaoCadastrar = findViewById(R.id.btn_cadastrar_login);

        // Adiciona um ouvinte de clique ao textViewEsqueceuSenha
        textViewEsqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Abre a EsqueceuSenhaActivity quando o textView for clicado
                Intent intent = new Intent(LoginActivity.this, EsqueceuSenhaActivity.class);
                startActivity(intent);
            }
        });

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = editTextEmail.getText().toString();
                final String senha = editTextSenha.getText().toString();

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editTextEmail.setError("Email inválido");
                    return;
                }
                if (senha.isEmpty() || senha.length() < 8) {
                    editTextSenha.setError("Senha deve conter no mínimo 8 caracteres");
                }
                view.setEnabled(false);

                LiveData<Boolean> resultLD = loginViewModel.login(email, senha);

                resultLD.observe(LoginActivity.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if (aBoolean) {
                            Config.setLogin(LoginActivity.this, email);
                            Config.setPassword(LoginActivity.this, senha);
                            view.setEnabled(true);
                            Toast.makeText(LoginActivity.this, "Login realizado com sucesso", Toast.LENGTH_LONG).show();

                            finish();

                        } else {
                            view.setEnabled(true);
                            Toast.makeText(LoginActivity.this, "Não foi possível realizar o login da aplicação", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(i);
            }
        });
    }
}
