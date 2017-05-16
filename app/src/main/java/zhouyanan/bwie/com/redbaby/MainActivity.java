package zhouyanan.bwie.com.redbaby;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radiogroup;
    private ViewPager viewpager;
    private int[] buttonid;
    private Fragment currentf;
    private HomeFragmentActivity homeFragment;
    private ClassifyFragmentActivity  classifyFragment;
    private ShopFragmentActivity  shopFragment;
    private MeFragmentActivity meFragment;
    private GroupFragmentActivity groupFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        去掉状态栏
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initview();
        lisenter();
        homeFragment = new HomeFragmentActivity();

        addFragments(homeFragment);

    }

    private void addFragments(Fragment f) {
        // 第一步：得到fragment管理类
        FragmentManager manager = getSupportFragmentManager();
        // 第二步：开启一个事务
        FragmentTransaction transaction = manager.beginTransaction();

        if (currentf != null) {
            //每次把前一个fragment给隐藏了
            transaction.hide(currentf);
        }

        //isAdded:判断当前的fragment对象是否被加载过
        if (!f.isAdded()) {
            // 第三步：调用添加fragment的方法 第一个参数：容器的id 第二个参数：要放置的fragment的一个实例对象
            transaction.add(R.id.fcontent, f);
        }
        //显示当前的fragment
        transaction.show(f);

        // 第四步：提交
        transaction.commit();

        currentf = f;
    }

    private void lisenter() {

        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.button1:
                        if (homeFragment == null) {
                            homeFragment = new HomeFragmentActivity();
                        }
                        addFragments(homeFragment);
                        break;
                    case R.id.button2:
                        if (classifyFragment == null) {
                            classifyFragment = new ClassifyFragmentActivity();
                        }
                        addFragments(classifyFragment);
                        break;
                    case R.id.button3:
                        if (groupFragment == null) {
                            groupFragment = new GroupFragmentActivity();
                        }
                        addFragments(groupFragment);
                        break;
                    case R.id.button4:
                        if (shopFragment == null) {
                            shopFragment = new ShopFragmentActivity();
                        }
                        addFragments(shopFragment);
                        break;
                    case R.id.button5:
                        if (meFragment == null) {
                            meFragment = new MeFragmentActivity();
                        }
                        addFragments(meFragment);
                        break;
                }
            }

        });
    }

    private void initview() {
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        buttonid = new int[]{R.id.button1, R.id.button2, R.id.button3, R.id.button4};

    }
}
