package carlos.dara.kaua.raynan.reciclamais.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import carlos.dara.kaua.raynan.reciclamais.entities.Material;
import carlos.dara.kaua.raynan.reciclamais.repository.UserRepository;
import kotlinx.coroutines.CoroutineScope;

public class SeusMateriaisViewModel extends AndroidViewModel {

    LiveData<PagingData<Material>> materialLD;
    public SeusMateriaisViewModel(@NonNull Application application){
        super(application);

        UserRepository userRepository = new UserRepository(getApplication());
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);
        Pager<Integer, Material> pager = new Pager(new PagingConfig(10), () -> new MaterialPagingSource(userRepository));
        materialLD = PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager), viewModelScope);
    }

    public LiveData<PagingData<Material>> getMateriaisLD(){
        return  materialLD;
    }
}
