package com.example.duanmot;

import android.content.Context;
import android.widget.EditText;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.duanmot.Activity.HomeActivity;
import com.example.duanmot.Database.DatabaseLoaiSP;
import com.example.duanmot.Database.DatabaseSanPham;
import com.example.duanmot.Entity.LoaiSanPham;
import com.example.duanmot.Entity.SanPham;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class HomeTests {

    @Rule
    public ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<>(HomeActivity.class);

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
    public void testCase1() {
        solo.clickOnView(solo.getView(R.id.flThemSp));
        assertTrue("Could not find the dialog!", solo.searchText("Bạn muốn thêm ? "));
    }

    @Test
    public void testCase2() {
        solo.clickOnView(solo.getView(R.id.flThemSp));
        solo.waitForDialogToOpen();

        solo.clickOnText("Loại Sản Phẩm ");
        solo.waitForDialogToOpen();

        solo.enterText((EditText) solo.getView(R.id.edtLoaiSp), "");
        solo.clickOnView(solo.getView(R.id.btnThemLoai));

        EditText textResult = (EditText) solo.getView(R.id.edtLoaiSp);

        assertEquals("toast is not showing", textResult.getText().toString(), "");
        assertTrue("Nhap day du thong tin", true);
    }

    @Test
    public void testCaseFail() {
        String textTest = "Loại sản phẩm 1";

        solo.clickOnView(solo.getView(R.id.flThemSp));
        solo.waitForDialogToOpen();

        solo.clickOnText("Loại Sản Phẩm ");
        solo.waitForDialogToOpen();

        solo.enterText((EditText) solo.getView(R.id.edtLoaiSp), "");
        solo.clickOnView(solo.getView(R.id.btnThemLoai));

        EditText textResult = (EditText) solo.getView(R.id.edtLoaiSp);
        assertEquals("toast is not showing", textResult.getText().toString(), textTest);
        fail("Nhap day du thong tin");
    }

    @Test
    public void testCase3() {
        String name = "Loai San Pham 1";

        solo.clickOnView(solo.getView(R.id.flThemSp));
        solo.waitForDialogToOpen();

        solo.clickOnText("Loại Sản Phẩm ");
        solo.waitForDialogToOpen();

        solo.enterText((EditText) solo.getView(R.id.edtLoaiSp), name);
        solo.clickOnView(solo.getView(R.id.btnThemLoai));

        EditText textResult = (EditText) solo.getView(R.id.edtLoaiSp);
        assertEquals("toast is not showing", textResult.getText().toString(), name);

        DatabaseLoaiSP databaseLoaiSP = DatabaseLoaiSP.getInstance(solo.getCurrentActivity());
        LoaiSanPham loaiSanPham = new LoaiSanPham();
        loaiSanPham.setLoaiSanPham(name);
        databaseLoaiSP.daoLoaiSanPham().InsertLoaiSanpham(loaiSanPham);

        LoaiSanPham loaiSanPhamDB = databaseLoaiSP.daoLoaiSanPham().getLoaiSanPham(name);
        assertEquals("toast is not showing", loaiSanPhamDB.getLoaiSanPham(), name);
        assertTrue("Thêm thành công !", true);
    }

    @Test
    public void testCase4() {
        solo.clickOnView(solo.getView(R.id.flThemSp));
        solo.waitForDialogToOpen();

        solo.clickOnText("Sản Phẩm");
        solo.waitForDialogToOpen();

        solo.enterText((EditText) solo.getView(R.id.edtTenSp), "");
        solo.enterText((EditText) solo.getView(R.id.edtGiaSp), "19000");
        solo.enterText((EditText) solo.getView(R.id.edtSoluong), "100");

        solo.clickOnView(solo.getView(R.id.btnThemSP));

        EditText textResult = (EditText) solo.getView(R.id.edtTenSp);

        assertEquals("toast is not showing", textResult.getText().toString(), "");
        assertTrue("Nhập tên sản phẩm", true);
    }

    @Test
    public void testCase5() {
        solo.clickOnView(solo.getView(R.id.flThemSp));
        solo.waitForDialogToOpen();

        solo.clickOnText("Sản Phẩm");
        solo.waitForDialogToOpen();

        solo.enterText((EditText) solo.getView(R.id.edtTenSp), "San Pham 1");
        solo.enterText((EditText) solo.getView(R.id.edtGiaSp), "");
        solo.enterText((EditText) solo.getView(R.id.edtSoluong), "100");

        solo.clickOnView(solo.getView(R.id.btnThemSP));
        EditText textResult = (EditText) solo.getView(R.id.edtGiaSp);

        assertEquals("toast is not showing", textResult.getText().toString(), "");
        assertTrue("Nhập giá sản phẩm", true);
    }

    @Test
    public void testCase6() {
        solo.clickOnView(solo.getView(R.id.flThemSp));
        solo.waitForDialogToOpen();

        solo.clickOnText("Sản Phẩm");
        solo.waitForDialogToOpen();

        solo.enterText((EditText) solo.getView(R.id.edtTenSp), "San pham 1");
        solo.enterText((EditText) solo.getView(R.id.edtGiaSp), "100000");
        solo.enterText((EditText) solo.getView(R.id.edtSoluong), "");

        solo.clickOnView(solo.getView(R.id.btnThemSP));

        EditText textResult = (EditText) solo.getView(R.id.edtSoluong);

        assertEquals("toast is not showing", textResult.getText().toString(), "");
        assertTrue("Nhập số lượng sản phẩm", true);
    }


    @Test
    public void testCaseFail2() {
        String soLuong = "123";

        solo.clickOnView(solo.getView(R.id.flThemSp));
        solo.waitForDialogToOpen();

        solo.clickOnText("Sản Phẩm");
        solo.waitForDialogToOpen();

        solo.enterText((EditText) solo.getView(R.id.edtTenSp), "San pham 1");
        solo.enterText((EditText) solo.getView(R.id.edtGiaSp), "100000");
        solo.enterText((EditText) solo.getView(R.id.edtSoluong), "");

        solo.clickOnView(solo.getView(R.id.btnThemSP));

        EditText textResult = (EditText) solo.getView(R.id.edtSoluong);

        assertEquals("toast is not showing", textResult.getText().toString(), soLuong);
        fail("Nhập số lượng sản phẩm");
    }


    @Test
    public void testCase7() {
        solo.clickOnView(solo.getView(R.id.flThemSp));
        solo.waitForDialogToOpen();

        solo.clickOnText("Sản Phẩm");
        solo.waitForDialogToOpen();

        solo.enterText((EditText) solo.getView(R.id.edtTenSp), "San pham 1");
        solo.enterText((EditText) solo.getView(R.id.edtGiaSp), "100000");
        solo.enterText((EditText) solo.getView(R.id.edtSoluong), "100");
        solo.pressSpinnerItem(0, 0);

        solo.clickOnView(solo.getView(R.id.btnThemSP));

        EditText textResultTen = (EditText) solo.getView(R.id.edtTenSp);
        EditText textResultGia = (EditText) solo.getView(R.id.edtGiaSp);
        EditText textResultSoLuong = (EditText) solo.getView(R.id.edtSoluong);

        String tenSP = textResultTen.getText().toString();
        String giaSp = textResultGia.getText().toString();
        String soLuongSP = textResultSoLuong.getText().toString();

        DatabaseLoaiSP databaseLoaiSP = DatabaseLoaiSP.getInstance(solo.getCurrentActivity());
        List<LoaiSanPham> loaiSanPhams = databaseLoaiSP.daoLoaiSanPham().loaiSanPham_LIST();
        LoaiSanPham loaiSanPham = loaiSanPhams.get(0);

        SanPham sanPham = new SanPham(tenSP, loaiSanPham.getLoaiSanPham(), Double.parseDouble(giaSp), Integer.parseInt(soLuongSP));
        DatabaseSanPham databaseSanPham = DatabaseSanPham.getInstance(solo.getCurrentActivity());
        databaseSanPham.daoSanPham().InsertSanPham(sanPham);

        SanPham sanPhamDB = databaseSanPham.daoSanPham().getSanPham(tenSP);

        assertEquals("toast is not showing", tenSP, sanPhamDB.getTenSanPham());
        assertEquals("toast is not showing", soLuongSP, sanPhamDB.getSoLuongSanPham() + "");
        assertTrue("Thêm thành công !", true);
    }


    @Test
    public void testFail3() {
        solo.clickOnView(solo.getView(R.id.flThemSp));
        solo.waitForDialogToOpen();

        solo.clickOnText("Sản Phẩm");
        solo.waitForDialogToOpen();

        solo.enterText((EditText) solo.getView(R.id.edtTenSp), "San pham 1");
        solo.enterText((EditText) solo.getView(R.id.edtGiaSp), "100000");
        solo.enterText((EditText) solo.getView(R.id.edtSoluong), "100");
        solo.pressSpinnerItem(0, 0);

        solo.clickOnView(solo.getView(R.id.btnThemSP));

        EditText textResultTen = (EditText) solo.getView(R.id.edtTenSp);
        EditText textResultGia = (EditText) solo.getView(R.id.edtGiaSp);
        EditText textResultSoLuong = (EditText) solo.getView(R.id.edtSoluong);

        String tenSP = textResultTen.getText().toString();
        String giaSp = textResultGia.getText().toString();
        String soLuongSP = textResultSoLuong.getText().toString();

        DatabaseLoaiSP databaseLoaiSP = DatabaseLoaiSP.getInstance(solo.getCurrentActivity());
        List<LoaiSanPham> loaiSanPhams = databaseLoaiSP.daoLoaiSanPham().loaiSanPham_LIST();
        LoaiSanPham loaiSanPham = loaiSanPhams.get(0);

        SanPham sanPham = new SanPham(tenSP, loaiSanPham.getLoaiSanPham(), Double.parseDouble(giaSp), Integer.parseInt(soLuongSP));
        DatabaseSanPham databaseSanPham = DatabaseSanPham.getInstance(solo.getCurrentActivity());
        databaseSanPham.daoSanPham().InsertSanPham(sanPham);

        SanPham sanPhamDB = databaseSanPham.daoSanPham().getSanPham(tenSP);

        assertEquals("toast is not showing", "San pham 2", sanPhamDB.getTenSanPham());
        assertEquals("toast is not showing", "100", sanPhamDB.getSoLuongSanPham() + "");
        fail("Thêm thành công !");
    }

}
