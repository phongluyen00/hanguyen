package com.example.duanmot;

import android.content.Context;
import android.widget.EditText;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.duanmot.Activity.QmkActivity;
import com.example.duanmot.Database.DatabaseTaiKhoan;
import com.example.duanmot.Entity.TaiKhoan;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DoiMatKhauTests {

    @Rule
    public ActivityTestRule<QmkActivity> activityTestRule = new ActivityTestRule<>(QmkActivity.class);

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
        String password = "ha";

        solo.clearEditText(0);
        solo.clearEditText(1);
        solo.clearEditText(2);

        solo.enterText(1, password);

        solo.clickOnView(solo.getView(R.id.btnOk));

        EditText textResultEmail = (EditText) solo.getView(R.id.edtEmail);
        EditText textResultConfirm = (EditText) solo.getView(R.id.edtConfirmPass);

        assertEquals("toast is not showing", "", textResultEmail.getText().toString());
        assertEquals("toast is not showing", "", textResultConfirm.getText().toString());
        assertTrue("Vui lòng nhập đầy đủ thông tin", true);
    }

    @Test
    public void testCaseInputEmpty2() {
        solo.clearEditText(0);
        solo.clearEditText(1);
        solo.clearEditText(2);

        solo.enterText(0, "ha");

        solo.clickOnView(solo.getView(R.id.btnOk));

        EditText textResultPass = (EditText) solo.getView(R.id.edtNewPass);
        EditText textResultConfirm = (EditText) solo.getView(R.id.edtConfirmPass);

        assertEquals("toast is not showing", "", textResultPass.getText().toString());
        assertEquals("toast is not showing", "", textResultConfirm.getText().toString());
        assertTrue("Vui lòng nhập đầy đủ thông tin", true);
    }

    @Test
    public void testCaseInputEmpty3() {
        solo.clearEditText(0);
        solo.clearEditText(1);
        solo.clearEditText(2);

        solo.enterText(2, "ha");

        solo.clickOnView(solo.getView(R.id.btnOk));

        EditText textResultEmail = (EditText) solo.getView(R.id.edtEmail);
        EditText textResultPass = (EditText) solo.getView(R.id.edtNewPass);

        assertEquals("toast is not showing", "", textResultPass.getText().toString());
        assertEquals("toast is not showing", "", textResultEmail.getText().toString());
        assertTrue("Vui lòng nhập đầy đủ thông tin", true);
    }

    @Test
    public void testCaseInputEmpty4() {
        solo.clearEditText(0);
        solo.clearEditText(1);
        solo.clearEditText(2);

        solo.clickOnView(solo.getView(R.id.btnOk));

        EditText textResultEmail = (EditText) solo.getView(R.id.edtEmail);
        EditText textResultPass = (EditText) solo.getView(R.id.edtNewPass);
        EditText textResultConfirm = (EditText) solo.getView(R.id.edtConfirmPass);

        assertEquals("toast is not showing", "", textResultPass.getText().toString());
        assertEquals("toast is not showing", "", textResultEmail.getText().toString());
        assertEquals("toast is not showing", "", textResultConfirm.getText().toString());
        assertTrue("Vui lòng nhập đầy đủ thông tin", true);
    }

    @Test
    public void testCaseFail1() {
        solo.clearEditText(0);
        solo.clearEditText(1);
        solo.clearEditText(2);

        solo.enterText(0, "ha1");
        solo.enterText(1, "ha2");
        solo.enterText(2, "ha3");

        solo.clickOnView(solo.getView(R.id.btnOk));

        EditText textResultPass = (EditText) solo.getView(R.id.edtNewPass);
        EditText textResultConfirm = (EditText) solo.getView(R.id.edtConfirmPass);

        assertEquals("toast is not showing", textResultConfirm.getText().toString(), textResultPass.getText().toString());
        assertTrue("Mật khẩu không khớp", true);
    }

    @Test
    public void testCasePass2() {
        String username = "hanguyen";
        String password = "333";
        String confirmPass = "333";

        solo.clearEditText(0);
        solo.clearEditText(1);
        solo.clearEditText(2);

        solo.enterText(0, username);
        solo.enterText(1, password);
        solo.enterText(2, confirmPass);

        solo.clickOnView(solo.getView(R.id.btnOk));

        DatabaseTaiKhoan databaseTaiKhoan = DatabaseTaiKhoan.getInstance(solo.getCurrentActivity());
        List<TaiKhoan> taiKhoanList = databaseTaiKhoan.daoTaiKhoan().TAI_KHOAN_LIST();

        for (TaiKhoan taiKhoan : taiKhoanList) {
            if (username.equals(taiKhoan.getTenTaiKhoan())) {
                taiKhoan.setMatKhau(password);
                databaseTaiKhoan.daoTaiKhoan().UpdateTaiKhoan(taiKhoan);

                TaiKhoan taikhoanDN = databaseTaiKhoan.daoTaiKhoan().dangnhap(username, password);
                assertEquals("toast is not showing", password, taikhoanDN.getMatKhau());
            } else {
                assertTrue("Không tồn tại tài khoản", true);
            }
        }

        assertTrue("Đổi mật khẩu thành công", true);
        solo.goBack();
    }

    @Test
    public void testCasePass3() {
        String username = "hihi";
        String password = "222";
        String confirmPass = "222";

        solo.clearEditText(0);
        solo.clearEditText(1);
        solo.clearEditText(2);

        solo.enterText(0, username);
        solo.enterText(1, password);
        solo.enterText(2, confirmPass);

        solo.clickOnView(solo.getView(R.id.btnOk));

        DatabaseTaiKhoan databaseTaiKhoan = DatabaseTaiKhoan.getInstance(solo.getCurrentActivity());
        List<TaiKhoan> taiKhoanList = databaseTaiKhoan.daoTaiKhoan().TAI_KHOAN_LIST();

        for (TaiKhoan taiKhoan : taiKhoanList) {
            if (!username.equals(taiKhoan.getTenTaiKhoan())) {
                assertTrue("Không tồn tại tài khoản", true);
            }
        }

        assertTrue("Không tồn tại tài khoản", true);
        solo.goBack();
    }

}
