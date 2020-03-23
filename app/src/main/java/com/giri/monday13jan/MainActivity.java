package com.giri.monday13jan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
        TextView num9, num8, num7, num6, num5, num4, num3, num2, num1, num0, opadd, opsub, opmul, opmod, opdiv, opequal;
        TextInputEditText operand1,operand2,operator,result;
        boolean isstrOperatorSelected=false;
        String strOperator="";

        TextView clear;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getIds();
        setListeners();

        }

    private void setListeners() {
        num9.setOnClickListener(this);
        num8.setOnClickListener(this);
        num7.setOnClickListener(this);
        num6.setOnClickListener(this);
        num5.setOnClickListener(this);
        num4.setOnClickListener(this);
        num3.setOnClickListener(this);
        num2.setOnClickListener(this);
        num1.setOnClickListener(this);
        num0.setOnClickListener(this);

        opadd.setOnClickListener(this);
        opsub.setOnClickListener(this);
        opdiv.setOnClickListener(this);
        opmod.setOnClickListener(this);
        opmul.setOnClickListener(this);
        opequal.setOnClickListener(this);

        clear.setOnClickListener(this);

    }

    private void getIds() {
        num9 = findViewById(R.id.num9);
        num8 = findViewById(R.id.num8);
        num7 = findViewById(R.id.num7);
        num6 = findViewById(R.id.num6);
        num5 = findViewById(R.id.num5);
        num4 = findViewById(R.id.num4);
        num3 = findViewById(R.id.num3);
        num2 = findViewById(R.id.num2);
        num1 = findViewById(R.id.num1);
        num0 = findViewById(R.id.num0);

        operand1 = findViewById(R.id.operand1);
        operand2 = findViewById(R.id.operand2);
        operator = findViewById(R.id.operator);
        result = findViewById(R.id.result);

        opadd = findViewById(R.id.opadd);
        opsub = findViewById(R.id.opsub);
        opmul = findViewById(R.id.opmul);
        opdiv = findViewById(R.id.opdiv);
        opmod = findViewById(R.id.opmod);
        opequal = findViewById(R.id.opequal);

        clear = findViewById(R.id.clear);
    }

    @Override
    public void onClick(View v) {

        if (v==opsub || v==opadd || v==opmul || v==opdiv || v==opmod ){
            strOperator=((TextView) v).getText().toString();
            isstrOperatorSelected=true;
            operator.setText(strOperator);
        }

        if (v==opequal){
            if (operand1.getText().toString().isEmpty() || operator.getText().toString().isEmpty() ||
                    operand2.getText().toString().isEmpty()){

            } else {
                String expression=operand1.getText().toString()+operator.getText().toString()+operand2.getText().toString();
                ScriptEngineManager mgr = new ScriptEngineManager();
                ScriptEngine engine = mgr.getEngineByName("js");
                try {
                    result.setText(engine.eval(expression).toString());
                } catch (ScriptException e) {
                    e.printStackTrace();
                }
            }
        }

        if (v==num9|| v==num8|| v==num7|| v==num6|| v==num5|| v==num4|| v==num3|| v==num2|| v==num1|| v==num0){
            if (isstrOperatorSelected){
                operand2.append(((TextView) v ).getText().toString());
            } else {
                operand1.append(((TextView) v ).getText().toString());
            }
        }
            if(v == clear) {
                operand1.setText("");
                operator.setText("");
                operand2.setText("");
                result.setText("");
             strOperator="";
             isstrOperatorSelected=false;
            }
    }
}
