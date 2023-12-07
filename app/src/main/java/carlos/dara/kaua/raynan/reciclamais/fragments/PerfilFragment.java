package carlos.dara.kaua.raynan.reciclamais.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import carlos.dara.kaua.raynan.reciclamais.R;
import carlos.dara.kaua.raynan.reciclamais.activity.AjudaActivity;
import carlos.dara.kaua.raynan.reciclamais.activity.AprendaReciclarActivity;
import carlos.dara.kaua.raynan.reciclamais.activity.LoginActivity;
import carlos.dara.kaua.raynan.reciclamais.activity.MudarDadosActivity;
import carlos.dara.kaua.raynan.reciclamais.activity.SeusMateriaisActivity;
import carlos.dara.kaua.raynan.reciclamais.util.Config;
import carlos.dara.kaua.raynan.reciclamais.viewModel.MainViewModel;

public class PerfilFragment extends Fragment {

    private MainViewModel mViewModel;
    private View view;

    public PerfilFragment() {

    }

    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_perfil, container, false);
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

        ImageView imvFotoUsuario = view.findViewById(R.id.imv_foto_usuario_perfil);
        TextView tvNomeUsuario = view.findViewById(R.id.tv_nome_usuario_perfil);
        Button botaoMudarDados = view.findViewById(R.id.btn_mudar_dados_perfil);
        Button botaoAprendaReciclar = view.findViewById(R.id.btn_aprenda_a_reciclar_perfil);
        Button botaoMateriaisPostados = view.findViewById(R.id.btn_materiais_postados_perfil);
        Button botaoAjuda = view.findViewById(R.id.btn_ajuda_perfil);

        botaoMudarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireActivity(), MudarDadosActivity.class));
            }
        });

        botaoAprendaReciclar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireActivity(), AprendaReciclarActivity.class));
            }
        });

        botaoMateriaisPostados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireActivity(), SeusMateriaisActivity.class));
            }
        });

        botaoAjuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireActivity(), AjudaActivity.class));
            }
        });
    }
}
