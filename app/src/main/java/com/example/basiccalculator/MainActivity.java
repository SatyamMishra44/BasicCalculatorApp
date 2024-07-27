package com.example.basiccalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView ResultTxt, SolutionTxt;
    MaterialButton btnC, btnOpenBracket, btnCloseBracket;
    MaterialButton btnAC, btnDot, btnAdd, btnSub, btnMul, btnDiv, btnEquals;
    MaterialButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ResultTxt = findViewById(R.id.result);
        SolutionTxt = findViewById(R.id.solutiontxt);
        btnId(btnC, R.id._button_C);
        btnId(btnOpenBracket, R.id._button_openbracket);
        btnId(btnCloseBracket, R.id._button_closebracket);
        btnId(btn0, R.id._button_0);
        btnId(btn1, R.id._button_1);
        btnId(btn2, R.id._button_2);
        btnId(btn3, R.id._button_3);
        btnId(btn4, R.id._button_4);
        btnId(btn5, R.id._button_5);
        btnId(btn6, R.id._button_6);
        btnId(btn7, R.id._button_7);
        btnId(btn8, R.id._button_8);
        btnId(btn9, R.id._button_9);
        btnId(btnAC, R.id._button_Clear);
        btnId(btnDot, R.id._button_dot);
        btnId(btnAdd, R.id.add);
        btnId(btnSub, R.id.substract);
        btnId(btnMul, R.id.multiply);
        btnId(btnDiv, R.id.division);
        btnId(btnEquals, R.id.equals);
    }

    private void btnId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonTxt = button.getText().toString();
        String dataConcat = SolutionTxt.getText().toString();

        if (buttonTxt.equals("AC")) {
            SolutionTxt.setText("");
            ResultTxt.setText("0");
            return;
        }
        if (buttonTxt.equals("=")) {
            SolutionTxt.setText(ResultTxt.getText());
            return;
        }
        if (buttonTxt.equals("C")) {
            dataConcat = dataConcat.substring(0, dataConcat.length() - 1);
            SolutionTxt.setText(dataConcat);
            return;
        } else {
            dataConcat = dataConcat + buttonTxt;
        }
        SolutionTxt.setText(dataConcat);
        String findResult = getResult(dataConcat);
        if (!findResult.equals("Error")) {
            ResultTxt.setText(findResult);
        }
    }

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String findResult = context.evaluateString(scriptable, data, "JavaScript", 1, null).toString();
            if (findResult.endsWith(".0")) {
                findResult = findResult.replace(".0", "");
            }
            return findResult;
        } catch (Exception e) {
            return "Error";
        } finally {
            Context.exit();
        }
    }
}
