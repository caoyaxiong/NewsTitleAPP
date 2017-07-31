package com.tongchengtianqi.activity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.tongchengtianqi.R;
import com.tongchengtianqi.dao.RecordSQLiteOpenHelper;
import com.tongchengtianqi.utils.ToastUtils;
import com.tongchengtianqi.view.MyListView;

import java.util.Date;


/**
 * 跳转到搜索页面
 */

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mSearchBack;
    private EditText mEditText;
    private ListView mCustomListView;
    private TextView mClearRecord;
    private TextView mSearchHistories;
    private SQLiteDatabase db;
    private BaseAdapter adapter;
    private RecordSQLiteOpenHelper helper = new RecordSQLiteOpenHelper(this);
    private TextView search_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();
        initListener();
    }


    private void initView() {
        mSearchBack = (ImageView) findViewById(R.id.search_back);
        mEditText = (EditText) findViewById(R.id.editText);
        mCustomListView = (MyListView) findViewById(R.id.search_lv);
        search_button = (TextView) findViewById(R.id.search_button);
        mClearRecord = (TextView) findViewById(R.id.tv_clear);
        mSearchHistories = (TextView) findViewById(R.id.tv_search_histories);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_back:
                finish();
                break;
            case R.id.search_button:
                // 先隐藏键盘
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                        getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                // 按完搜索键后将当前查询的关键字保存起来,如果该关键字已经存在就不执行保存
                if (mEditText.getText().toString().trim().equals("")) {
                    ToastUtils.show(this, "输入的内容不能为空,请重新输入", 2000);
                } else {
                    boolean hasData = hasData(mEditText.getText().toString().trim());
                    //如果数据库中没有该数据，进行模糊查询
                    if (!hasData) {
                        insertData(mEditText.getText().toString().trim());
                        queryData("");
                    }
                    // TODO 根据输入的内容模糊查询内容，并跳转到另一个界面，由你自己去实现
                    ToastUtils.show(this, "clicked!", 2000);
                }
                break;
            case R.id.tv_clear:
                deleteData();
                queryData("");
            default:
                break;
        }
    }


    protected void initListener() {
        mSearchBack.setOnClickListener(this);
        mEditText.setOnClickListener(this);
        search_button.setOnClickListener(this);
        mClearRecord.setOnClickListener(this);
        // 输入完后按键盘上的搜索键
        mEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // 修改回车键功能
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    // 先隐藏键盘
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                            getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    // 按完搜索键后将当前查询的关键字保存起来,如果该关键字已经存在就不执行保存
                    ToastUtils.show(SearchActivity.this, "clicked!", 2000);
                    boolean hasData = hasData(mEditText.getText().toString().trim());
                    if (!hasData) {
                        insertData(mEditText.getText().toString().trim());
                        queryData("");
                    }
                    // TODO 根据输入的内容模糊查询内容，并跳转到另一个界面，由你自己去实现
                    ToastUtils.show(SearchActivity.this, "clicked!", 2000);
                }
                return false;
            }
        });

        // 搜索框的文本变化实时监听
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() == 0) {
                    mSearchHistories.setText("搜索历史");
                } else {
                    mSearchHistories.setText("搜索结果");
                }
                String tempName = mEditText.getText().toString();
                // 根据tempName去模糊查询数据库中有没有数据
                queryData(tempName);

            }
        });

        mCustomListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                String name = textView.getText().toString();
                mEditText.setText(name);
                ToastUtils.show(SearchActivity.this, name, 2000);
                // TODO 获取到item上面的文字，根据该关键字跳转到另一个页面查询，由你自己去实现
            }
        });

        // 插入数据，便于测试，否则第一次进入没有数据怎么测试呀？
        Date date = new Date();
        long time = date.getTime();
        insertData("Leo" + time);

        // 第一次进入查询所有的历史记录
        queryData("");
    }

    /*插入数据*/
    private void insertData(String tempName) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + tempName + "')");
        db.close();
    }

    /*模糊查询数据*/
    private void queryData(String tempName) {
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name like '% tempName %' order by id desc ", null);
        // 创建adapter适配器对象
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[]{"name"},
                new int[]{android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        // 设置适配器
        mCustomListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    /*检查数据库中是否已经有该条记录*/
    private boolean hasData(String tempName) {
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name =?", new String[]{tempName});
        //判断是否有下一个
        return cursor.moveToNext();
    }

    /*清空数据*/
    private void deleteData() {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();
    }
}
