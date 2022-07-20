package com.todo.app.ui.main.home

import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.todo.app.BaseFragment
import com.todo.app.R
import com.todo.app.data.Task
import com.todo.app.di.AppComponent
import com.todo.app.di.main.MainViewModelsModule
import com.todo.app.ui.UIFragmentWindowEvents
import com.todo.app.ui.main.EditTaskHandler
import dagger.Component
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Singleton

@Singleton
@Component(modules = [MainViewModelsModule::class])
interface TestComponent : AppComponent

@RunWith(AndroidJUnit4::class)
internal class HomeFragmentTest : TestCase() {

    private lateinit var scenario: FragmentScenario<HomeFragment>

    private fun launchFragment(): FragmentScenario<HomeFragment> {

//        var viewModels = mutableMapOf<Class<out ViewModel>, Provider<ViewModel>>()
//        viewModels.put(HomeViewModel::class.java, Provider<ViewModel>(ViewModel()))
//
//        var vmFactory = ViewModelFactory(viewModels)

//        vmFactory.create(HomeViewModel::class.java)

        val editTaskHandler = object : EditTaskHandler {
            override fun openEditFragment(task: Task?) {

            }
        }

        val windowsListener = object : UIFragmentWindowEvents {
            override fun onWindowOpen() {
            }

            override fun onWindowClosed() {
            }
        }

        return launchFragmentInContainer(factory = object : FragmentFactory() {
            override fun instantiate(classLoader: ClassLoader, className: String): BaseFragment {
                return HomeFragment(editTaskHandler, windowsListener)
//                return HomeFragment()
//                    .apply {
//                    viewModelFactory = vmFactory
//                    // assign other deps here as per your needs
//                }
            }
        }, themeResId = R.style.Theme_Todo)
    }

    @Before
    fun setup() {
        scenario = launchFragment()
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun testDeleteTask() {
        val text = "Task Task 3"
        onView(withText(text)).perform(ViewActions.swipeLeft())
        onView(withText("Deleted Item Task Task 3")).inRoot(ToastMatcher())
            .check(matches(isDisplayed()))
    }
}