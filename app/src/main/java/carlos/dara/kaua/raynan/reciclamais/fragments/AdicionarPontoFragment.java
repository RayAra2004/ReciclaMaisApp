package carlos.dara.kaua.raynan.reciclamais.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import carlos.dara.kaua.raynan.reciclamais.R;
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

    }
}