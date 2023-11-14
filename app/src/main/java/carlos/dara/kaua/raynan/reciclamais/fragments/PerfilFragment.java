package carlos.dara.kaua.raynan.reciclamais.fragments;

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
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imvFotoUsuario = view.findViewById(R.id.imv_foto_usuario_perfil);
        TextView tvNomeUsuario = view.findViewById(R.id.tv_nome_usuario_perfil);
        Button botaoMudarDados = view.findViewById(R.id.btn_mudar_dados_perfil);
        Button botaoAprendaReciclar = view.findViewById(R.id.btn_aprenda_a_reciclar_perfil);
        Button botaoMateriaisPostados = view.findViewById(R.id.btn_materiais_postados_perfil);
        Button botaoAjuda = view.findViewById(R.id.btn_ajuda_perfil);
    }
}
