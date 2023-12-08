package carlos.dara.kaua.raynan.reciclamais.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import carlos.dara.kaua.raynan.reciclamais.R;

public class MudarDadosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mudar_dados);

        EditText editTextNome = findViewById(R.id.editText_nome_mudar_dados);
        EditText editTextTelefone = findViewById(R.id.editText_telefone_mudar_dados);
        EditText editTextSenha = findViewById(R.id.editText_senha_mudar_dados);
        EditText editTextConfirmeSenha = findViewById(R.id.editText_confirme_sua_senha_mudar_dados);
        Button botaoAtualizarDados = findViewById(R.id.btn_atualizar_mudar_dados);
        EditText etDataNascimento = findViewById(R.id.etDataNascimento2);
        ImageButton imbDataNascimento = findViewById(R.id.imbDataNascimento2);

        imbDataNascimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog( MudarDadosActivity.this);
                datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                        String diaStr = "", mesStr = "";
                        if(dia < 10){
                            diaStr = "0" + dia;
                        }else{
                            diaStr = String.valueOf(dia);
                        }
                        if(mes < 10){
                            mesStr = "0" + (mes + 1);
                        }else{
                            mesStr = String.valueOf(mes + 1);
                        }

                        String date = diaStr + "/" + mesStr + "/"  + ano;
                        etDataNascimento.setText(date);
                    }
                });
                datePickerDialog.show();
            }
        });

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