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

import carlos.dara.kaua.raynan.reciclamais.R;
import carlos.dara.kaua.raynan.reciclamais.viewModel.MainViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PostagemMaterialFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostagemMaterialFragment extends Fragment {

    private MainViewModel mViewModel;
    private View view;

    public PostagemMaterialFragment() {
        // Required empty public constructor
    }

    public static PostagemMaterialFragment newInstance() {
        return  new PostagemMaterialFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imvFotoMaterial = view.findViewById(R.id.imv_foto_material_postagem_material);
        Button botaoAdicionarFotoMaterial = view.findViewById(R.id.btn_adicionar_foto_material_postagem_material);
        EditText etTitulo = view.findViewById(R.id.editText_titulo_anuncio_postagem_material);
        EditText etTelefone = view.findViewById(R.id.editText_telefone_postagem_material);
        EditText etPeso = view.findViewById(R.id.editText_peso_material_postagem_material);
        EditText etDescricaoMaterial = view.findViewById(R.id.editText_descricao_postagem_material);
        Button botaoSolicitarColeta = view.findViewById(R.id.btn_solicitar_coleta_postagem_material);
    }
}