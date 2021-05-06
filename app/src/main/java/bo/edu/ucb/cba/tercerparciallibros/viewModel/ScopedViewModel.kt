package bo.edu.ucb.cba.tercerparciallibros.viewModel

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext


interface Scope: CoroutineScope {
    class Impl : Scope {
        override lateinit var job: Job
    }
    var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    fun initScope() {
        job = SupervisorJob()
    }
    fun destroyScope() {
        job.cancel() }
}

abstract class ScopedViewModel: ViewModel(), Scope by Scope.Impl() {
    init {
        initScope()
    }
    @CallSuper
    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}
