package carlos.dara.kaua.raynan.reciclamais.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import carlos.dara.kaua.raynan.reciclamais.R;
import carlos.dara.kaua.raynan.reciclamais.viewModel.CadastroViewModel;

public class CadastroActivity extends AppCompatActivity {
    CadastroViewModel cadastroViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        cadastroViewModel = new ViewModelProvider(this).get(CadastroViewModel.class);


        EditText etDataNascimento = findViewById(R.id.etDataNascimento);

        ImageButton imbDataNascimento = findViewById(R.id.imbDataNascimento);
        imbDataNascimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog( CadastroActivity.this);
                datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                        String date = String.valueOf(dia) + " / " + String.valueOf(mes + 1) + " / "  + String.valueOf(ano);
                        etDataNascimento.setText(date);
                    }
                });
                datePickerDialog.show();
            }
        });

        EditText etNome = findViewById(R.id.editText_nome_cadastro);
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
                String dataNascimento = etDataNascimento.getText().toString();

                if (nome.isEmpty()){
                    etNome.setError("Campo nome é obrigatório");
                    return;
                }
                if (dataNascimento.isEmpty()){
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
                }

                // O ViewModel possui o método register, que envia as informações para o servidor web.
                // O servidor web recebe as infos e cadastra um novo usuário. Se o usuário foi cadastrado
                // com sucesso, a app recebe o valor true. Se não o servidor retorna o valor false.
                //
                // O método de register retorna um LiveData, que na prática é um container que avisa
                // quando o resultado do servidor chegou.
                LiveData<Boolean> resultLD = cadastroViewModel.signUp(nome, email, senha, telefone, dataNascimento);

                // Aqui nós observamos o LiveData. Quando o servidor responder, o resultado indicando
                // se o cadastro deu certo ou não será guardado dentro do LiveData. Neste momento o
                // LiveData avisa que o resultado chegou chamando o método onChanged abaixo.
                resultLD.observe(CadastroActivity.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        // aBoolean contém o resultado do cadastro. Se aBoolean for true, significa
                        // que o cadastro do usuário foi feito corretamente. Indicamos isso ao usuário
                        // através de uma mensagem do tipo toast e finalizamos a Activity. Quando
                        // finalizamos a Activity, voltamos para a tela de login.
                        if(aBoolean) {
                            Toast.makeText(CadastroActivity.this, "Novo usuario registrado com sucesso", Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else {
                            // Se o cadastro não deu certo, apenas continuamos na tela de cadastro e
                            // indicamos com uma mensagem ao usuário que o cadastro não deu certo.
                            Toast.makeText(CadastroActivity.this, "Erro ao registrar novo usuário", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        // FIM DO TESTE DA VALIDAÇÃO SIMPLES


    }
}