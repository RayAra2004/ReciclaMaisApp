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
import android.widget.Toast;

import carlos.dara.kaua.raynan.reciclamais.R;
import carlos.dara.kaua.raynan.reciclamais.activity.LoginActivity;
import carlos.dara.kaua.raynan.reciclamais.util.Config;
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
        view = inflater.inflate(R.layout.fragment_postagem_material, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(Config.getLogin(requireActivity()).isEmpty()){
            Intent i = new Intent(requireActivity(), LoginActivity.class);
            startActivity(i);
            requireActivity().finish();
        }

        ImageView imvFotoMaterial = view.findViewById(R.id.imv_foto_material_postagem_material);
        Button botaoAdicionarFotoMaterial = view.findViewById(R.id.btn_adicionar_foto_material_postagem_material);
        EditText etTitulo = view.findViewById(R.id.editText_titulo_anuncio_postagem_material);
        EditText etTelefone = view.findViewById(R.id.editText_telefone_postagem_material);
        EditText etPeso = view.findViewById(R.id.editText_peso_material_postagem_material);
        EditText etDescricaoMaterial = view.findViewById(R.id.editText_descricao_postagem_material);
        Button botaoSolicitarColeta = view.findViewById(R.id.btn_solicitar_coleta_postagem_material);

        botaoSolicitarColeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = etTitulo.getText().toString();
                String telefone = etTelefone.getText().toString();
                String peso = etPeso.getText().toString();
                String descricaoMaterial = etDescricaoMaterial.getText().toString();

                if (TextUtils.isEmpty(titulo)){
                    Toast.makeText(getContext(),"Por favor, preencha o campo de título", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(telefone)){
                    Toast.makeText(getContext(),"Por favor, preencha o campo de telefone", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(peso)){
                    Toast.makeText(getContext(),"Por favor, preencha o campo de peso", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(descricaoMaterial)){
                    Toast.makeText(getContext(),"Por favor, preencha o campo de descricao do material", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}