package leafmen.tsz.com.josnexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends AppCompatActivity {

    private String jsonString;
    private TextView tv_jsondata;
    private Button btn_self;
    private Button btn_gson;
    private Button btn_fastjosn;
    private Button btn_jackson;
    private Button btn_cleardata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jsonString = initLoadData();

        tv_jsondata = (TextView) findViewById(R.id.tv_jsondata);
        btn_cleardata = (Button) findViewById(R.id.btn_cleardata);
        btn_cleardata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_jsondata.setText("");
            }
        });

        btn_self = (Button) findViewById(R.id.btn_self);
        btn_self.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject resultObj = new JSONObject(jsonString);
                    Students student = new Students();
                    student.setId(resultObj.getLong("id"));
                    student.setName(resultObj.getString("name"));
                    student.setSex(resultObj.getString("sex"));
                    student.setAge((short) resultObj.getDouble("age"));
                    student.setGrade(resultObj.getString("grade"));
                    disData("Org.Json", student);
//                    Toast.makeText(MainActivity.this,"这个人的姓名是:"+resultObj.getString("name"),Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        btn_gson = (Button) findViewById(R.id.btn_gson);
        btn_gson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                Students student = new Students();
                student = gson.fromJson(jsonString, Students.class);
                disData("Gson", student);
            }
        });

        btn_fastjosn = (Button) findViewById(R.id.btn_fastjosn);
        btn_fastjosn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Students student = JSON.parseObject(jsonString, Students.class);
                disData("Fastjosn", student);
            }
        });

        btn_jackson = (Button) findViewById(R.id.btn_jackson);
        btn_jackson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    Students student = objectMapper.readValue(jsonString, Students.class);
                    disData("Jackson", student);
                } catch (JsonMappingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private String initLoadData() {
        try {
            InputStream inputStream = getResources().getAssets().open("students.txt");
            int size = inputStream.available();
            byte[] bytes = new byte[size];
            inputStream.read(bytes);
            inputStream.close();
            return new String(bytes, "UTF-8");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void disData(String tab, Students student) {
        tv_jsondata.append("当前使用的数据解析方式:" + tab);
        tv_jsondata.append("\n");
        tv_jsondata.append("Id:" + student.getId());
        tv_jsondata.append("\n");
        tv_jsondata.append("Name:" + student.getName());
        tv_jsondata.append("\n");
        tv_jsondata.append("Age:" + student.getAge());
        tv_jsondata.append("\n");
        tv_jsondata.append("Sex:" + student.getSex());
        tv_jsondata.append("\n");
        tv_jsondata.append("Grade:" + student.getGrade());
        tv_jsondata.append("\n");
    }

}
