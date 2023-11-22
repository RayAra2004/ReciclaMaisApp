package carlos.dara.kaua.raynan.reciclamais.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import carlos.dara.kaua.raynan.reciclamais.R;
import carlos.dara.kaua.raynan.reciclamais.activity.LoginActivity;
import carlos.dara.kaua.raynan.reciclamais.activity.MainActivity;
import carlos.dara.kaua.raynan.reciclamais.entities.TipoMaterial;
import carlos.dara.kaua.raynan.reciclamais.util.Config;
import carlos.dara.kaua.raynan.reciclamais.viewModel.MainViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdicionarPontoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdicionarPontoFragment extends Fragment {

    private MainViewModel mViewModel;
    private View view;
    private ArrayList<String> materiaisSelecionados = new ArrayList<>();

    EditText etCidade, etBairro, etNumero, etLogradouro, etCep;
    Spinner spTipoLogradouro, spEstado;

    public AdicionarPontoFragment() {
        // Required empty public constructor
    }

    public static AdicionarPontoFragment newInstance() {
        return new AdicionarPontoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_adicionar_ponto, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(Config.getLogin(requireActivity()).isEmpty()){
            Intent i = new Intent(requireActivity(), LoginActivity.class);
            i.putExtra("fragment", "adicionarPonto");
            startActivity(i);
            requireActivity().finish();
        }

        ImageView imvFotoPonto = view.findViewById(R.id.imv_foto_ponto_adicionar_ponto);
        Button botaoAdicionarFoto = view.findViewById(R.id.btn_adicionar_foto_ponto_adicionar_ponto);
        EditText etNome = view.findViewById(R.id.editText_nome_adicionar_ponto);

        etCep = view.findViewById(R.id.editText_CEP_adicionar_ponto);
        spTipoLogradouro = view.findViewById(R.id.sp_tipo_logradouro_adicionar_ponto);
        etLogradouro = view.findViewById(R.id.editText_logradouro_adicionar_ponto);
        etNumero = view.findViewById(R.id.editText_numero_adicionar_ponto);
        spEstado = view.findViewById(R.id.sp_estado_adicionar_ponto);
        etCidade = view.findViewById(R.id.editText_cidade_adicionar_ponto);
        etBairro = view.findViewById(R.id.editText_bairro_adicionar_ponto);

        etCep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Nada a fazer aqui
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Nada a fazer aqui
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String cep = editable.toString();
                if (cep.length() == 8) {
                    new ConsultaCEPTask().execute(cep);
                }
            }
        });

        Button botaoVidro = view.findViewById(R.id.btn_vidro_adicionar_ponto);
        botaoVidro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(materiaisSelecionados.contains(botaoVidro.getText())){
                    materiaisSelecionados.remove(botaoVidro.getText());
                }else{
                    materiaisSelecionados.add(botaoVidro.getText().toString());
                }
            }
        });

        Button botaoPlastico = view.findViewById(R.id.btn_plastico_adicionar_ponto);
        botaoPlastico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(materiaisSelecionados.contains(botaoPlastico.getText())){
                    materiaisSelecionados.remove(botaoPlastico.getText());
                }else{
                    materiaisSelecionados.add(botaoPlastico.getText().toString());
                }
            }
        });

        Button botaoMadeira = view.findViewById(R.id.btn_madeira_adicionar_ponto);
        botaoMadeira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(materiaisSelecionados.contains(botaoMadeira.getText())){
                    materiaisSelecionados.remove(botaoMadeira.getText());
                }else{
                    materiaisSelecionados.add(botaoMadeira.getText().toString());
                }
            }
        });

        Button botaoMetal = view.findViewById(R.id.btn_metal_adicionar_ponto);
        botaoMetal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(materiaisSelecionados.contains(botaoMetal.getText())){
                    materiaisSelecionados.remove(botaoMetal.getText());
                }else{
                    materiaisSelecionados.add(botaoMetal.getText().toString());
                }
            }
        });

        Button botaoHospitalar = view.findViewById(R.id.btn_hospitalar_adicionar_ponto);
        botaoHospitalar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(materiaisSelecionados.contains(botaoHospitalar.getText())){
                    materiaisSelecionados.remove(botaoHospitalar.getText());
                }else{
                    materiaisSelecionados.add(botaoHospitalar.getText().toString());
                }
            }
        });

        Button botaoOrganico = view.findViewById(R.id.btn_organico_adicionar_ponto);
        botaoOrganico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(materiaisSelecionados.contains(botaoOrganico.getText())){
                    materiaisSelecionados.remove(botaoOrganico.getText());
                }else{
                    materiaisSelecionados.add(botaoOrganico.getText().toString());
                }
            }
        });

        Button botaoPapel = view.findViewById(R.id.btn_papel_adicionar_ponto);
        botaoPapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(materiaisSelecionados.contains(botaoPapel.getText())){
                    materiaisSelecionados.remove(botaoPapel.getText());
                }else{
                    materiaisSelecionados.add(botaoPapel.getText().toString());
                }
            }
        });

        Button botaoEletronico = view.findViewById(R.id.btn_eletronicos_adicionar_ponto);
        botaoEletronico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(materiaisSelecionados.contains(botaoEletronico.getText())){
                    materiaisSelecionados.remove(botaoEletronico.getText());
                }else{
                    materiaisSelecionados.add(botaoEletronico.getText().toString());
                }
            }
        });


        Button botaoAdicionarPontoColeta = view.findViewById(R.id.btn_adicionar_ponto_de_coleta_adicionar_ponto);

        botaoAdicionarPontoColeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = etNome.getText().toString();
                String cep = etCep.getText().toString();
                String tipoLogradouro = spTipoLogradouro.getSelectedItem().toString();
                String logradouro = etLogradouro.getText().toString();
                String numero = etNumero.getText().toString();
                String estado = spEstado.getSelectedItem().toString();
                String cidade = etCidade.getText().toString();
                String bairro = etBairro.getText().toString();

                System.out.println(materiaisSelecionados);

                if (TextUtils.isEmpty(nome)){
                    Toast.makeText(getContext(), "Por favor, preencha o campo nome", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(cep)){
                    Toast.makeText(getContext(), "Por favor, preencha o campo CEP", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(tipoLogradouro)){
                    Toast.makeText(getContext(), "Por favor, selecione o tipo de logradouro", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(logradouro)){
                    Toast.makeText(getContext(), "Por favor, preencha o campo logradouro", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(numero)){
                    Toast.makeText(getContext(), "Por favor, preencha o campo numero", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(estado)){
                    Toast.makeText(getContext(), "Por favor, selecione o estado", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(cidade)){
                    Toast.makeText(getContext(), "Por favor, preencha o campo cidade", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(bairro)){
                    Toast.makeText(getContext(), "Por favor, preencha o campo bairro", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }

    private class ConsultaCEPTask extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... strings) {
            String cep = strings[0];

            try {
                URL url = new URL("https://brasilapi.com.br/api/cep/v2/" + cep);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    reader.close();

                    return new JSONObject(response.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(JSONObject dados) {
            if (dados != null) {
                preencherCampos(dados);
            }
        }
    }

    private void preencherCampos(JSONObject dados) {
        try {
            etCidade.setText(dados.getString("city"));
            etLogradouro.setText(dados.getString("street"));
            // Preencher os outros campos conforme necessário
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
