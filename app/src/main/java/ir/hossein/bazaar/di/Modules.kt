package ir.hossein.bazaar.di

import android.content.Context
import androidx.room.Room
import ir.hossein.bazaar.model.db.AppDatabase
import ir.hossein.bazaar.model.net.createApiService
import ir.hossein.bazaar.model.repository.cart.CartRepository
import ir.hossein.bazaar.model.repository.cart.CartRepositoryImpl
import ir.hossein.bazaar.model.repository.comment.CommentRepository
import ir.hossein.bazaar.model.repository.comment.CommentRepositoryImpl
import ir.hossein.bazaar.model.repository.product.ProductRepository
import ir.hossein.bazaar.model.repository.product.ProductRepositoryImpl
import ir.hossein.bazaar.model.repository.user.UserRepository
import ir.hossein.bazaar.model.repository.user.UserRepositoryImpl
import ir.hossein.bazaar.ui.features.cart.CartViewModel
import ir.hossein.bazaar.ui.features.category.CategoryViewModel
import ir.hossein.bazaar.ui.features.main.MainViewModel
import ir.hossein.bazaar.ui.features.product.ProductViewModel
import ir.hossein.bazaar.ui.features.profile.ProfileViewModel
import ir.hossein.bazaar.ui.features.signIn.SignInViewModel
import ir.hossein.bazaar.ui.features.signUp.SignUpViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModules = module {

    single { androidContext().getSharedPreferences("data", Context.MODE_PRIVATE) }
    single { createApiService() }

    single { Room.databaseBuilder(androidContext(), AppDatabase::class.java, "app_database.db").build() }

    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    single<ProductRepository> { ProductRepositoryImpl(get(), get<AppDatabase>().productDao()) }
    single<CommentRepository> { CommentRepositoryImpl(get()) }
    single<CartRepository> { CartRepositoryImpl(get(), get()) }

    viewModel { SignUpViewModel(get()) }
    viewModel { SignInViewModel(get()) }
    viewModel { (isInternetConnected: Boolean) -> MainViewModel(get(), get() ,isInternetConnected) }
    viewModel { CategoryViewModel(get()) }
    viewModel { ProductViewModel(get(), get(), get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { CartViewModel(get(), get()) }

}