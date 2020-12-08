import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.PerformException
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.ageverifier.constants.AppUIConstants
import com.example.ageverifier.MainActivity
import com.example.ageverifier.R
import kotlinx.android.synthetic.main.activity_main.view.*
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class AppUITest {

    @Rule
    @JvmField
    val rule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.example.ageverifier", appContext.packageName)
    }

    /*************************** UI Test Caases : Start ***************************/

    @Test
    fun lable_text_displayed(){
        val view = rule.activity.findViewById<View>(R.id.statusTextView)
        Assert.assertThat(view, CoreMatchers.notNullValue())
        Assert.assertEquals(AppUIConstants.APP_LABLE_TEXT, view.statusTextView.text.toString())
    }

    @Test
    fun user_can_enter_age_value_into_text_field(){
        val validAge = "12"
        Espresso.onView(ViewMatchers.withId(R.id.editTextNumber)).perform(ViewActions.typeText(validAge))

        val view = rule.activity.findViewById<View>(R.id.editTextNumber)
        Assert.assertThat(view, CoreMatchers.notNullValue())
        Assert.assertEquals(validAge, view.editTextNumber.text.toString())
    }

    @Test
    fun button_is_clickable(){
        Espresso.onView(ViewMatchers.withId(R.id.button)).check(ViewAssertions.matches(ViewMatchers.isClickable()))
    }

    /*************************** UI Test Caases : End ***************************/

    /*************************** UI + API Integration Test Caases : Start ***************************/
    @Test
    fun user_aged_above_18_is_allowed_to_drink(){
        val validAge = "20"
        Espresso.onView(ViewMatchers.withId(R.id.editTextNumber)).perform(ViewActions.typeText(validAge))
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click())

        Thread.sleep(AppUIConstants.SLEEP_TIME)

        val view = rule.activity.findViewById<View>(R.id.statusTextView)
        Assert.assertEquals(AppUIConstants.USER_CAN_DRINK_TEXT, view.statusTextView.text.toString())
    }

    @Test
    fun user_aged_below_18_is_not_allowed_to_drink(){
        val validAge = "10"
        Espresso.onView(ViewMatchers.withId(R.id.editTextNumber)).perform(ViewActions.typeText(validAge))
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click())

        Thread.sleep(AppUIConstants.SLEEP_TIME)

        val view = rule.activity.findViewById<View>(R.id.statusTextView)
        Assert.assertEquals(AppUIConstants.USER_CANNOT_DRINK_TEXT, view.statusTextView.text.toString())
    }

    @Test
    fun throws_server_error_when_user_enters_nagative_age(){
        val negativeAge = "-200"
        Espresso.onView(ViewMatchers.withId(R.id.editTextNumber)).perform(ViewActions.typeText(negativeAge))
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click())

        Thread.sleep(AppUIConstants.SLEEP_TIME)

        val view = rule.activity.findViewById<View>(R.id.statusTextView)
        Assert.assertEquals(AppUIConstants.USER_NAGATIVE_AGE_ERROR_TEXT, view.statusTextView.text.toString())
    }

    @Test(expected = PerformException::class)
    fun throws_error_when_user_user_enters_non_numeric_age_value(){
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.typeText("abc"))
    }
    /*************************** UI + API Integration Test Caases: End ***************************/

}
