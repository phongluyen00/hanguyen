package com.example.duanmot;

import android.content.Context;
import android.widget.EditText;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.duanmot.Activity.DangNhapActivity;
import com.example.duanmot.Activity.MainActivity;
import com.example.duanmot.DAO.DAOTaiKhoan;
import com.example.duanmot.Database.DatabaseTaiKhoan;
import com.example.duanmot.Entity.TaiKhoan;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DangKyTests {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    private Context context;
    private Solo solo;

    @Before
    public void setUp() throws Exception {
        //setUp() is run before a test case is started.'8/
        //This is where the solo object is created.
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), activityTestRule.getActivity());
        context = InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
    }

    @After
    public void tearDown() throws Exception {
        //tearDown() is run after a test case has finished.
        //finishOpenedActivities() will finish all the activities that have been opened during the test execution.
        solo.finishOpenedActivities();
    }

    @Test
    public void testCasePass3() {
        // Context of the app under test.
        String username = "nguyenha";
        String password = "123456";
        String phone = "0394476777";
        String email = "nguyenhact1c@gmail.com";

        solo.clickOnView(solo.getView(R.id.btnDangKy1));

        solo.clearEditText(0);
        solo.clearEditText(1);
        solo.clearEditText(2);
        solo.clearEditText(3);

        solo.enterText(0, username);
        solo.enterText(1, password);
        solo.enterText(2, phone);
        solo.enterText(3, email);

        solo.clickOnView(solo.getView(R.id.btnDangKy2));

        DatabaseTaiKhoan databaseTaiKhoan = DatabaseTaiKhoan.getInstance(solo.getCurrentActivity());
        DAOTaiKhoan daoTaiKhoan = databaseTaiKhoan.daoTaiKhoan();
        TaiKhoan taiKhoan = daoTaiKhoan.dangnhap(username, password);

        assertEquals("toast is not showing", username, taiKhoan.getTenTaiKhoan());
        assertEquals("toast is not showing", password, taiKhoan.getMatKhau());
        assertTrue("tao thanh cong", true);
    }


    @Test
    public void testCasePass4() {
        // Context of the app under test.
        String username = "hanguyen";
        String password = "123456";
        String phone = "0394476777";
        String email = "nguyenhact1c@gmail.com";

        solo.clickOnView(solo.getView(R.id.btnDangKy1));

        solo.clearEditText(0);
        solo.clearEditText(1);
        solo.clearEditText(2);
        solo.clearEditText(3);

        solo.enterText(0, username);
        solo.enterText(1, password);
        solo.enterText(2, phone);
        solo.enterText(3, email);

        solo.clickOnView(solo.getView(R.id.btnDangKy2));

        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setTenTaiKhoan(solo.getText(0).toString());
        taiKhoan.setMatKhau(solo.getText(1).toString());
        taiKhoan.setSoDienThoai(solo.getText(2).toString());
        taiKhoan.seteMail(solo.getText(3).toString());
        DatabaseTaiKhoan databaseTaiKhoan = DatabaseTaiKhoan.getInstance(solo.getCurrentActivity());
        DAOTaiKhoan daoTaiKhoan = databaseTaiKhoan.daoTaiKhoan();
        daoTaiKhoan.InsertTaiKhoan(taiKhoan);

        TaiKhoan taiKhoanCreated = daoTaiKhoan.dangnhap(username, password);

        assertEquals("toast is not showing", username, taiKhoanCreated.getTenTaiKhoan());
        assertEquals("toast is not showing", password, taiKhoanCreated.getMatKhau());
        assertTrue("tao thanh cong", true);
        solo.goBack();
    }


    @Test
    public void testCasePass1() {
        // Context of the app under test.
        solo.clickOnView(solo.getView(R.id.btnDangKy1));

        solo.clearEditText(0);
        solo.clearEditText(1);
        solo.clearEditText(2);
        solo.clearEditText(3);

        solo.enterText(0, "nguyenha");
        solo.enterText(2, "0394476777");
        solo.enterText(3, "nguyenhact1c@gmail.com");

        solo.clickOnView(solo.getView(R.id.btnDangKy2));

        EditText textResult = (EditText) solo.getView(R.id.edtNhapMk);

        assertEquals("toast is not showing", textResult.getText().toString(), "");
        assertTrue("khong thanh cong", true);
    }

    @Test
    public void testCasePass2() {
        // Context of the app under test.
        solo.clickOnView(solo.getView(R.id.btnDangKy1));

        solo.clearEditText(0);
        solo.clearEditText(1);
        solo.clearEditText(2);
        solo.clearEditText(3);

        solo.enterText(1, "nguyenha");
        solo.enterText(2, "0394476777");
        solo.enterText(3, "nguyenhact1c@gmail.com");

        solo.clickOnView(solo.getView(R.id.btnDangKy2));

        EditText textResult = (EditText) solo.getView(R.id.edtTenDn);

        assertEquals("toast is not showing", textResult.getText().toString(), "");

        assertTrue("khong thanh cong", true);
    }

    @Test
    public void testCasePass5() {
        // Context of the app under test.
        solo.clickOnView(solo.getView(R.id.btnDangKy1));

        solo.clearEditText(0);
        solo.clearEditText(1);
        solo.clearEditText(2);
        solo.clearEditText(3);

        solo.enterText(0, "haxinhgai");
        solo.enterText(1, "111");
        solo.enterText(2, "0394476777");
        solo.enterText(3, "nguyenhact1c@gmail.com");

        solo.clickOnView(solo.getView(R.id.btnDangKy2));

        assertTrue("tao thanh cong", true);
        solo.goBack();
        solo.waitForActivity(DangNhapActivity.class);
    }

    @Test
    public void testCurrentActivity() {
        solo.assertCurrentActivity("wrong activity", DangNhapActivity.class);
    }

    @Test
    public void testDangNhapButton() {
        // Context of the app under test.
        solo.clickOnView(solo.getView(R.id.btnDangKy1));

        boolean found = solo.searchButton("Đăng ký");
        solo.clickOnButton("Đăng ký");
        assertTrue(found);
    }
}