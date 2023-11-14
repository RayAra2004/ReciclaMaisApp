package carlos.dara.kaua.raynan.reciclamais.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import carlos.dara.kaua.raynan.reciclamais.R;
import carlos.dara.kaua.raynan.reciclamais.fragments.AdicionarPontoFragment;
import carlos.dara.kaua.raynan.reciclamais.fragments.HomeFragment;
import carlos.dara.kaua.raynan.reciclamais.fragments.PerfilFragment;
import carlos.dara.kaua.raynan.reciclamais.fragments.PostagemMaterialFragment;
import carlos.dara.kaua.raynan.reciclamais.viewModel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragContainerMain, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.tbMain);
        setSupportActionBar(toolbar);

        final MainViewModel vm = new ViewModelProvider(this).get(MainViewModel.class);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                vm.setNavigationOpSelected(item.getItemId());
                switch (item.getItemId()){
                    case R.id.adicionarpontoOP:
                        AdicionarPontoFragment adicionarPontoFragment = AdicionarPontoFragment.newInstance();
                        setFragment(adicionarPontoFragment);

                    case R.id.homeOP:
                        HomeFragment homeFragment = HomeFragment.newInstance();
                        setFragment(homeFragment);

                    case R.id.perfilOP:
                        PerfilFragment perfilFragment = PerfilFragment.newInstance();
                        setFragment(perfilFragment);

                    case R.id.postarmaterialOP:
                        PostagemMaterialFragment postagemMaterialFragment = PostagemMaterialFragment.newInstance();
                        setFragment(postagemMaterialFragment);
                }
                return false;
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_bnv,menu);
        return true;
    };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.drawable.reciclar:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }//todo app galeria pg 15 (adaptado)

}