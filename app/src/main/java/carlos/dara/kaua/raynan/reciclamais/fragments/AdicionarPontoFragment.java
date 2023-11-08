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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdicionarPontoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdicionarPontoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdicionarPontoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdicionarPontoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdicionarPontoFragment newInstance(String param1, String param2) {
        AdicionarPontoFragment fragment = new AdicionarPontoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adicionar_ponto, container, false);
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