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

            myAdapter = new MyAdapter((MainActivity) getActivity(), new PontoColetaComparator());
            rvPontosDeColeta.setAdapter(myAdapter);

            LiveData<PagingData<PontoColeta>> pontosColetaLD = mViewModel.getPontosColetaLd();

            pontosColetaLD.observe(getViewLifecycleOwner(), new Observer<PagingData<PontoColeta>>() {
                @Override
                public void onChanged(PagingData<PontoColeta> pontoColetaPagingData) {
                    myAdapter.submitData(getLifecycle(), pontoColetaPagingData);
                }
            });
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_HOME_ACTIVITY_RESULT && resultCode == Activity.RESULT_OK) {
            MainActivity mainActivity = (MainActivity) getActivity();
            if (mainActivity != null && mainActivity.myAdapter != null) {
                mainActivity.myAdapter.refresh();
            }
        }
    }
}
