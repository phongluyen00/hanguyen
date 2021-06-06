package com.example.duanmot;

import android.content.Context;
import android.widget.EditText;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.duanmot.Activity.DangNhapActivity;
import com.example.duanmot.Activity.HomeActivity;
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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DangNhapTests {

    @Rule
    public ActivityTestRule<DangNhapActivity> activityTestRule = new ActivityTestRule<>(DangNhapActivity.class);

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
    public void testCaseInputEmpty1() {
        solo.clearEditText(0);
        solo.clearEditText(1);

        solo.enterText(0, "ha");

        solo.clickOnView(solo.getView(R.id.btnDangNhap));

        EditText textResult = (EditText) solo.getView(R.id.edtMatKhau);

        assertEquals("toast is not showing", textResult.getText().toString(), "");

        assertTrue("Sai Mat Khau Hoac Ten Dang Nhap", true);
    }

    @Test
    public void testCaseInputEmpty2() {
        solo.clearEditText(0);
        solo.clearEditText(1);

        solo.enterText(1, "123456aA@");

        solo.clickOnView(solo.getView(R.id.btnDangNhap));

        EditText textResult = (EditText) solo.getView(R.id.edtTenDn);

        assertEquals("toast is not showing", textResult.getText().toString(), "");

        assertTrue("Sai Mat Khau Hoac Ten Dang Nhap", true);
    }

    @Test
    public void testCasePass1() {
        String username = "nguyenha";
        String password = "123456";

        solo.clearEditText(0);
        solo.clearEditText(1);

        solo.enterText(0, username);
        solo.enterText(1, password);

        solo.clickOnView(solo.getView(R.id.btnDangNhap));

        DatabaseTaiKhoan databaseTaiKhoan = DatabaseTaiKhoan.getInstance(solo.getCurrentActivity());
        DAOTaiKhoan daoTaiKhoan = databaseTaiKhoan.daoTaiKhoan();
        TaiKhoan taiKhoan = daoTaiKhoan.dangnhap(username, password);

        assertEquals("toast is not showing", "sontn16", taiKhoan.getTenTaiKhoan());
        assertEquals("toast is not showing", "sontn16", taiKhoan.getMatKhau());
        assertTrue("Dang Nhap Thanh Cong", true);
    }

    @Test
    public void testCasePass() {
        String username = "nguyenha";
        String password = "123456";

        solo.clearEditText(0);
        solo.clearEditText(1);

        solo.enterText(0, username);
        solo.enterText(1, password);

        solo.clickOnView(solo.getView(R.id.btnDangNhap));

        DatabaseTaiKhoan databaseTaiKhoan = DatabaseTaiKhoan.getInstance(solo.getCurrentActivity());
        DAOTaiKhoan daoTaiKhoan = databaseTaiKhoan.daoTaiKhoan();
        TaiKhoan taiKhoan = daoTaiKhoan.dangnhap(username, password);

        assertEquals("toast is not showing", username, taiKhoan.getTenTaiKhoan());
        assertEquals("toast is not showing", password, taiKhoan.getMatKhau());
        assertTrue("Dang Nhap Thanh Cong", true);
    }


    @Test
    public void testCaseFail1() {
        String username = "hanguyen";
        String password = "hanguyen";

        solo.clearEditText(0);
        solo.clearEditText(1);

        solo.enterText(0, username);
        solo.enterText(1, password);

        solo.clickOnView(solo.getView(R.id.btnDangNhap));

        DatabaseTaiKhoan databaseTaiKhoan = DatabaseTaiKhoan.getInstance(solo.getCurrentActivity());
        DAOTaiKhoan daoTaiKhoan = databaseTaiKhoan.daoTaiKhoan();
        TaiKhoan taiKhoan = daoTaiKhoan.dangnhap(username, password);

        assertEquals("toast is not showing", username, taiKhoan.getTenTaiKhoan());
        assertEquals("toast is not showing", password, taiKhoan.getMatKhau());
        assertTrue("Dang Nhap Thanh Cong", true);
    }

    @Test
    public void testCasePass2() {
        String username = "haic";
        String password = "haic";


        solo.clearEditText(0);
        solo.clearEditText(1);

        solo.enterText(0, username);
        solo.enterText(1, password);

        solo.clickOnView(solo.getView(R.id.btnDangNhap));

        DatabaseTaiKhoan databaseTaiKhoan = DatabaseTaiKhoan.getInstance(solo.getCurrentActivity());
        DAOTaiKhoan daoTaiKhoan = databaseTaiKhoan.daoTaiKhoan();
        TaiKhoan taiKhoan = daoTaiKhoan.dangnhap(username, password);

        assertNull("toast is not showing", taiKhoan);

        assertTrue("TTTTTT...", true);
    }

    @Test
    public void testCasePass3() {
        String username = "ha";
        String password = "ha";

        solo.clearEditText(0);
        solo.clearEditText(1);

        solo.enterText(0, username);
        solo.enterText(1, password);

        solo.clickOnView(solo.getView(R.id.btnDangNhap));

        EditText textResultUser = (EditText) solo.getView(R.id.edtTenDn);
        EditText textResultPassword = (EditText) solo.getView(R.id.edtMatKhau);

        assertEquals("toast is not showing", username, textResultUser.getText().toString());
        assertEquals("toast is not showing", password, textResultPassword.getText().toString());

        DatabaseTaiKhoan databaseTaiKhoan = DatabaseTaiKhoan.getInstance(solo.getCurrentActivity());
        DAOTaiKhoan daoTaiKhoan = databaseTaiKhoan.daoTaiKhoan();
        TaiKhoan taiKhoan = daoTaiKhoan.dangnhap(username, password);

        assertEquals("toast is not showing", username, taiKhoan.getTenTaiKhoan());
        assertEquals("toast is not showing", username, taiKhoan.getTenTaiKhoan());
        assertEquals("toast is not showing", password, taiKhoan.getMatKhau());


        assertTrue("Dang Nhap Thanh Cong", true);

        solo.waitForActivity(HomeActivity.class);
    }

    @Test
    public void testDangNhapButton() {
        boolean found = solo.searchButton("Đăng Nhập");
        solo.clickOnButton("Đăng Nhập");
        assertTrue(found);
    }

    @Test
    public void testCurrentActivity() {
        solo.assertCurrentActivity("wrong activity", DangNhapActivity.class);
    }

}
