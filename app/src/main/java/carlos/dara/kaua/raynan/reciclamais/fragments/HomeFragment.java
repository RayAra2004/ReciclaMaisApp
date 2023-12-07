package carlos.dara.kaua.raynan.reciclamais.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.Button;


import java.util.ArrayList;

import carlos.dara.kaua.raynan.reciclamais.R;
import carlos.dara.kaua.raynan.reciclamais.activity.MainActivity;
import carlos.dara.kaua.raynan.reciclamais.adapter.MyAdapterPontoColeta;
import carlos.dara.kaua.raynan.reciclamais.adapter.PontoColetaComparator;
import carlos.dara.kaua.raynan.reciclamais.entities.PontoColeta;
import carlos.dara.kaua.raynan.reciclamais.viewModel.MainViewModel;

public class HomeFragment extends Fragment {
    private MainViewModel mViewModel;
    private View view;
    MyAdapterPontoColeta myAdapterPontoColeta;
    static int ADD_HOME_ACTIVITY_RESULT = 1;

    private ArrayList<String> materiaisSelecionados = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_home, container, false);

            RecyclerView rvPontosDeColeta = view.findViewById(R.id.rv_pontos_de_coleta_home);
            rvPontosDeColeta.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            rvPontosDeColeta.setLayoutManager(layoutManager);

            myAdapterPontoColeta = new MyAdapterPontoColeta((MainActivity) getActivity(), new PontoColetaComparator());
            rvPontosDeColeta.setAdapter(myAdapterPontoColeta);

            LiveData<PagingData<PontoColeta>> pontosColetaLD = mViewModel.getPontosColetaLd();

            pontosColetaLD.observe(getViewLifecycleOwner(), new Observer<PagingData<PontoColeta>>() {
                @Override
                public void onChanged(PagingData<PontoColeta> pontoColetaPagingData) {
                    myAdapterPontoColeta.submitData(getLifecycle(), pontoColetaPagingData);
                }
            });
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button botaoVidro = view.findViewById(R.id.btn_vidro_home);

        // Defina a cor de fundo original do botão
        final int originalColor = botaoVidro.getSolidColor();

        // Defina um ouvinte de clique para o botão
        botaoVidro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verifique se o botão já está selecionado
                if (materiaisSelecionados.contains("Vidro")) {
                    // Se o botão já estiver selecionado, volte à cor original
                    botaoVidro.setBackgroundColor(originalColor);
                    materiaisSelecionados.remove("Vidro");
                } else {
                    // Caso contrário, defina a cor de fundo para verde
                    botaoVidro.setBackgroundColor(Color.GREEN);
                    materiaisSelecionados.add("Vidro");
                }
            }
        });

        Button botaoPlastico = view.findViewById(R.id.btn_plastico_home);
        botaoPlastico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (materiaisSelecionados.contains("Plástico")) {
                    botaoPlastico.setBackgroundColor(originalColor);
                    materiaisSelecionados.remove("Plástico");
                } else {
                    botaoPlastico.setBackgroundColor(Color.RED);
                    materiaisSelecionados.add("Plástico");
                }
            }
        });

        Button botaoMadeira = view.findViewById(R.id.btn_madeira_home);
        botaoMadeira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(materiaisSelecionados.contains("Madeira")){
                    botaoMadeira.setBackgroundColor(originalColor);
                    materiaisSelecionados.remove("Madeira");
                }else{
                    botaoMadeira.setBackgroundColor(Color.BLACK);
                    materiaisSelecionados.add("Madeira");
                }
            }
        });

        Button botaoMetal = view.findViewById(R.id.btn_metal_home);
        botaoMetal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(materiaisSelecionados.contains("Metal")){
                    botaoMetal.setBackgroundColor(originalColor);
                    materiaisSelecionados.remove("Metal");
                }else{
                    botaoMetal.setBackgroundColor(Color.YELLOW);
                    materiaisSelecionados.add("Metal");
                }
            }
        });

        Button botaoHospitalar = view.findViewById(R.id.btn_hospitalar_home);

        final int cor_hospitalar = getResources().getColor(R.color.Cor_Hospitalar);
        botaoHospitalar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(materiaisSelecionados.contains("Hospitalar")){
                    botaoHospitalar.setBackgroundColor(originalColor);
                    materiaisSelecionados.remove("Hospitalar");
                }else{
                    botaoHospitalar.setBackgroundColor(cor_hospitalar);
                    materiaisSelecionados.add("Hospitalar");
                }
            }
        });

        Button botaoOrganico = view.findViewById(R.id.btn_organico_home);
        final int cor_organico = getResources().getColor(R.color.Cor_Organico);
        botaoOrganico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(materiaisSelecionados.contains("Orgânico")){
                    botaoOrganico.setBackgroundColor(originalColor);
                    materiaisSelecionados.remove("Orgânico");
                }else{
                    botaoOrganico.setBackgroundColor(cor_organico);
                    materiaisSelecionados.add("Orgânico");
                }
            }
        });

        Button botaoPapel = view.findViewById(R.id.btn_papel_home);
        botaoPapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(materiaisSelecionados.contains("Papel")){
                    botaoPapel.setBackgroundColor(originalColor);
                    materiaisSelecionados.remove("Papel");
                }else{
                    botaoPapel.setBackgroundColor(Color.BLUE);
                    materiaisSelecionados.add("Papel");
                }
            }
        });

        Button botaoEletronico = view.findViewById(R.id.btn_eletronico_home);
        final int cor_eletronicos = getResources().getColor(R.color.Cor_eletronicos);
        botaoEletronico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(materiaisSelecionados.contains("Eletrônico")){
                    botaoEletronico.setBackgroundColor(originalColor);
                    materiaisSelecionados.remove("Eletrônico");
                }else{
                    botaoEletronico.setBackgroundColor(cor_eletronicos);
                    materiaisSelecionados.add("Eletrônico");
                }
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_HOME_ACTIVITY_RESULT && resultCode == Activity.RESULT_OK) {
            MainActivity mainActivity = (MainActivity) getActivity();
            if (mainActivity != null && mainActivity.myAdapterPontoColeta != null) {
                mainActivity.myAdapterPontoColeta.refresh();
            }
        }
    }
}
