package carlos.dara.kaua.raynan.reciclamais.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagingData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import carlos.dara.kaua.raynan.reciclamais.R;
import carlos.dara.kaua.raynan.reciclamais.activity.MainActivity;
import carlos.dara.kaua.raynan.reciclamais.adapter.MyAdapter;
import carlos.dara.kaua.raynan.reciclamais.adapter.PontoColetaComparator;
import carlos.dara.kaua.raynan.reciclamais.entities.PontoColeta;
import carlos.dara.kaua.raynan.reciclamais.viewModel.MainViewModel;

public class HomeFragment extends Fragment {
    private MainViewModel mViewModel;
    private View view;
    MyAdapter myAdapter;
    static int ADD_HOME_ACTIVITY_RESULT = 1;
    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance() {
        return new HomeFragment();
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

        RecyclerView rvPontosDeColeta = view.findViewById(R.id.rv_pontos_de_coleta_home);
        rvPontosDeColeta.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvPontosDeColeta.setLayoutManager(layoutManager);

        myAdapter = new MyAdapter((MainActivity) getActivity(), new PontoColetaComparator());
        rvPontosDeColeta.setAdapter(myAdapter);

        MainViewModel mainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        LiveData<PagingData<PontoColeta>> pontosColetaLD = mainViewModel.getPontosColetaLd();

        pontosColetaLD.observe(getActivity(), new Observer<PagingData<PontoColeta>>() {
            /**
             * Esse método é chamado sempre que uma nova página de produtos é entregue à app pelo
             * servidor web.
             * @param pontoColetaPagingData contém uma página de produtos
             */
            @Override
            public void onChanged(PagingData<PontoColeta> pontoColetaPagingData) {

                // Adiciona a nova página de produtos ao Adapter do RecycleView. Isso faz com que
                // novos produtos apareçam no RecycleView.
                myAdapter.submitData(getLifecycle(),pontoColetaPagingData);
            }
        });

        /**
         * Quando o usuário adiciona um novo produto com sucesso, ele volta para a tela HomeActivity.
         * O método abaixo é chamado quando a tela de adição de novo produto finaliza. Neste momento,
         * verificamos se o o produto foi adicionado com sucesso. Se sim, atualizamos o Adapter, que por
         * sua vez irá recarregar a lista de produtos do servidor.
         */
        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            // Se estiver retornando da tela de adição de produtos
            if(requestCode == ADD_HOME_ACTIVITY_RESULT) {
                // Se a adição de produtos foi realizada com sucesso
                if(resultCode == Activity.RESULT_OK) {
                    // O adapter é atualizado. Isso faz com que os dados atuais sejam invalidados e
                    // sejam pedidas novas páginas de produtos para o servidor web.
                    myAdapter.refresh();
                }
            }
        }
    }
}