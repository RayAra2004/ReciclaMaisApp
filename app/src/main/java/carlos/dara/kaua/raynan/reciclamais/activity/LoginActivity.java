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
import carlos.dara.kaua.raynan.reciclamais.fragments.AdicionarPontoFragment;
import carlos.dara.kaua.raynan.reciclamais.util.Config;
import carlos.dara.kaua.raynan.reciclamais.viewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent i = getIntent();
        String fragmentRetorno = i.getStringExtra("fragment");

        // A função que entra em contato com o servidor web está definida dentro da ViewModel
        // referente a essa Activity
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        EditText editTextEmail = findViewById(R.id.editText_digite_seu_email_login);
        EditText editTextSenha = findViewById(R.id.editText_digite_sua_senha_login);
        TextView textViewEsqueceuSenha = findViewById(R.id.tv_esqueceu_senha_login);
        Button botaoLogin = findViewById(R.id.btn_login_login);
        Button botaoCadastrar = findViewById(R.id.btn_cadastrar_login);

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = editTextEmail.getText().toString();
                final String senha = editTextSenha.getText().toString();

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    editTextEmail.setError("Email inválido");
                    return;
                }
                if (senha.isEmpty() || senha.length() < 8){
                    editTextSenha.setError("Senha deve conter no mínimo 8 caracteres");
                }

                // O ViewModel possui o método login, que envia as informações para o servidor web.
                // O servidor web recebe as infos e verifica se estão corretas. Se sim, siginifca
                // que o login foi realizado com sucesso e a app recebe o valor true. Se as infos
                // estão incorretas, o servidor retorna o valor false.
                //
                // O método de login retorna um LiveData, que na prática é um container que avisa
                // quando o resultado do servidor chegou.
                LiveData<Boolean> resultLD = loginViewModel.login(email, senha);

                // Aqui nós observamos o LiveData. Quando o servidor responder, o resultado indicando
                // se o login deu certo ou não será guardado dentro do LiveData. Neste momento o
                // LiveData avisa que o resultado chegou chamando o método onChanged abaixo.
                resultLD.observe(LoginActivity.this, new Observer<Boolean>() {

                    // Ao ser chamado, o método onChanged informa também qual foi o resultado
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        // aBoolean contém o resultado do login. Se aBoolean for true, significa
                        // que as infos de login e senha enviadas ao servidor estão certas. Neste
                        // caso, guardamos as infos de login e senha dentro da app através da classe
                        // Config. Essas infos de login e senha precisam ser guardadas dentro da app
                        // para que possam ser usadas quando a app pedir dados ao servidor web que só
                        // podem ser obtidos se o usuário enviar o login e senha.
                        if(aBoolean) {

                            // guarda os dados de login e senha dentro da app
                            Config.setLogin(LoginActivity.this, email);
                            Config.setPassword(LoginActivity.this, senha);

                            // exibe uma mensagem indicando que o login deu certo
                            Toast.makeText(LoginActivity.this, "Login realizado com sucesso", Toast.LENGTH_LONG).show();

                            // Navega para tela que chamou o login
                            switch (fragmentRetorno){
                                case "adicionarPonto":
                                    Intent i = new Intent(LoginActivity.this, AdicionarPontoFragment.class);
                                    startActivity(i);
                            }

                        }
                        else {

                            // Se o login não deu certo, apenas continuamos na tela de login e
                            // indicamos com uma mensagem ao usuário que o login não deu certo.
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