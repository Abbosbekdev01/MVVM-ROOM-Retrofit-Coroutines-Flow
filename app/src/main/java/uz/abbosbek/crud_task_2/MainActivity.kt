package uz.abbosbek.crud_task_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.abbosbek.crud_task_2.adapters.RvAdapter
import uz.abbosbek.crud_task_2.adapters.RvClick
import uz.abbosbek.crud_task_2.database.AppDatabase
import uz.abbosbek.crud_task_2.database.entity.CountryEntity
import uz.abbosbek.crud_task_2.databinding.ActivityMainBinding
import uz.abbosbek.crud_task_2.databinding.ItemDialogBinding
import uz.abbosbek.crud_task_2.networking.ApiClient
import uz.abbosbek.crud_task_2.utils.NetworkHelper
import uz.abbosbek.crud_task_2.vm.Resource
import uz.abbosbek.crud_task_2.vm.UserViewModel
import uz.abbosbek.crud_task_2.vm.UserViewModelFactory
import kotlin.coroutines.CoroutineContext

private const val TAG = "MainActivity"

// todo:View
class MainActivity : AppCompatActivity(), RvClick, CoroutineScope {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var viewModel: UserViewModel
    private lateinit var rvAdapter: RvAdapter

    @Suppress("UNUSED_EXPRESSION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        rvAdapter = RvAdapter(list = ArrayList(), this)

        viewModel = ViewModelProvider(
            this,
            UserViewModelFactory(
                AppDatabase.getInstance(this),
                ApiClient.apiService,
                NetworkHelper(this)
            )
        )[UserViewModel::class.java]

        launch {
            viewModel.getStateFlow().collect {
                when (it) {
                    is Resource.Error -> {
                        Toast.makeText(this@MainActivity, it.e.message, Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Loading -> {
                        binding.progressBarr.isVisible = true
                    }

                    is Resource.Success -> {
                        it
                        Log.d(TAG, "onCreate: ${it.data}")
                        it.data.forEach {
                            rvAdapter.list.add(it)
                        }
                        binding.rv.adapter = rvAdapter
                        binding.progressBarr.isVisible = false
                    }
                }
            }
        }
    }

    override fun itemClick(userEntity: CountryEntity, position: Int) {
        val dialog = AlertDialog.Builder(this)
        val itemDialog = ItemDialogBinding.inflate(layoutInflater)
        dialog.setView(itemDialog.root)
        itemDialog.apply {
            tvArea.text = userEntity.area
            tvRegion.text = userEntity.region
            tvPopulation.text = userEntity.population
        }
        dialog.setNegativeButton("Yopish", null)
        dialog.create()
        dialog.show()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}