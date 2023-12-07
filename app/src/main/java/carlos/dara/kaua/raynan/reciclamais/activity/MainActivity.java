package carlos.dara.kaua.raynan.reciclamais.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import carlos.dara.kaua.raynan.reciclamais.R;
import carlos.dara.kaua.raynan.reciclamais.adapter.MyAdapterPontoColeta;
import carlos.dara.kaua.raynan.reciclamais.fragments.AdicionarPontoFragment;
import carlos.dara.kaua.raynan.reciclamais.fragments.HomeFragment;
import carlos.dara.kaua.raynan.reciclamais.fragments.PerfilFragment;
import carlos.dara.kaua.raynan.reciclamais.fragments.PostagemMaterialFragment;
import carlos.dara.kaua.raynan.reciclamais.viewModel.MainViewModel;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    static int RESULT_REQUEST_PERMISSION = 3;
    public MyAdapterPontoColeta myAdapterPontoColeta;

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

        //Pedindo permissões para uso DA CÂMERA, ACESSO A GALERIA e ACESSO A LOCALIZAÇÃO
        List<String> permissions = new ArrayList<>();
        permissions.add(Manifest.permission.CAMERA);
        permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);

        checkForPermissions(permissions);

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
                        break;

                    case R.id.homeOP:
                        HomeFragment homeFragment = HomeFragment.newInstance();
                        setFragment(homeFragment);
                        break;

                    case R.id.perfilOP:
                        PerfilFragment perfilFragment = PerfilFragment.newInstance();
                        setFragment(perfilFragment);
                        break;

                    case R.id.postarmaterialOP:
                        PostagemMaterialFragment postagemMaterialFragment = PostagemMaterialFragment.newInstance();
                        setFragment(postagemMaterialFragment);
                        break;
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

    /**
     * Verifica se as permissões necessárias já foram concedidas. Caso contrário, o usuário recebe
     * uma janela pedindo para conceder as permissões
     * @param permissions lista de permissões que se quer verificar
     */
    private void checkForPermissions(List<String> permissions) {
        List<String> permissionsNotGranted = new ArrayList<>();

        for(String permission : permissions) {
            if( !hasPermission(permission)) {
                permissionsNotGranted.add(permission);
            }
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(permissionsNotGranted.size() > 0) {
                requestPermissions(permissionsNotGranted.toArray(new String[permissionsNotGranted.size()]),RESULT_REQUEST_PERMISSION);
            }
        }
    }

    /**
     * Verifica se uma permissão já foi concedida
     * @param permission
     * @return true caso sim, false caso não.
     */
    private boolean hasPermission(String permission) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return ActivityCompat.checkSelfPermission(MainActivity.this, permission) == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }

    /**
     * Método chamado depois que o usuário já escolheu as permissões que quer conceder. Esse método
     * indica o resultado das escolhas do usuário.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        final List<String> permissionsRejected = new ArrayList<>();
        if(requestCode == RESULT_REQUEST_PERMISSION) {

            for(String permission : permissions) {
                if(!hasPermission(permission)) {
                    permissionsRejected.add(permission);
                }
            }
        }

        if(permissionsRejected.size() > 0) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
                    new AlertDialog.Builder(MainActivity.this).
                            setMessage("Para usar essa app é preciso conceder essas permissões").
                            setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    requestPermissions(permissionsRejected.toArray(new String[permissionsRejected.size()]), RESULT_REQUEST_PERMISSION);
                                }
                            }).create().show();
                }
            }
        }
    }

    /**
     * Navega para a tela que mostra os detalhes de um produto cujo id é pid
     * @param id id do produto que se quer mostrar em detalhes
     */
    public void startViewPontoColetaAcitivity(String id) {
        Intent i = new Intent(this, DescricaoPontoActivity.class);
        i.putExtra("id", id);
        startActivity(i);
    }
}