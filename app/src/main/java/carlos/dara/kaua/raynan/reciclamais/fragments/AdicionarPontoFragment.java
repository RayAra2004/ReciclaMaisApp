package carlos.dara.kaua.raynan.reciclamais.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import carlos.dara.kaua.raynan.reciclamais.R;
import carlos.dara.kaua.raynan.reciclamais.activity.LoginActivity;
import carlos.dara.kaua.raynan.reciclamais.activity.MainActivity;
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_adicionar_ponto, container, false);
        return view;
    }

    // LEMBRAR DE IMPORTAR FUNÇÃO ONVIEWCREATED EM TODOS OS FRAGMENTS
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
        EditText etCep = view.findViewById(R.id.editText_CEP_adicionar_ponto);
        Spinner spTipoLogradouro = view.findViewById(R.id.sp_tipo_logradouro_adicionar_ponto);
        EditText etLogradouro = view.findViewById(R.id.editText_logradouro_adicionar_ponto);
        EditText etNumero = view.findViewById(R.id.editText_numero_adicionar_ponto);
        Spinner spEstado = view.findViewById(R.id.sp_estado_adicionar_ponto);
        EditText etCidade = view.findViewById(R.id.editText_cidade_adicionar_ponto);
        EditText etBairro = view.findViewById(R.id.editText_bairro_adicionar_ponto);
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
}