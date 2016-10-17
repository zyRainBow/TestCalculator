package com.example.testcalculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	
	    private TableLayout table1=null;
            private TableLayout table2=null;

            private Button[] btnNum = new Button[11];// 数值按钮  
	    private Button[] btnCommand = new Button[5];// 符号按钮  
	    private EditText editText = null;// 显示区域  
	    private Button btnClear = null; // clear按钮  
	    private Button btnSwitch1=null;//切换按钮
	    private Button btnSwitch2=null;//切换按钮
	    private String lastCommand; // 用于保存运算符  
	    private boolean clearFlag; // 用于判断是否清空显示区域的值,true需要,false不需要  
	    private boolean firstFlag; // 用于判断是否是首次输入,true首次,false不是首次  
	    private double result; // 计算结果  
	    
	    public MainActivity() {  
	        // 初始化各项值  
	        result = 0; // x的值  
	        firstFlag = true; // 是首次运算  
	        clearFlag = false; // 不需要清空  
	        lastCommand = "="; // 运算符  
	    }  
	    @Override  
	    public void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.activity_main);  
	        
	        table1=(TableLayout)findViewById(R.id.tableLayout1);
	        table2=(TableLayout)findViewById(R.id.tableLayout2);
	        
	        // 获取运算符  
	        btnCommand[0] = (Button) findViewById(R.id.add);  
	        btnCommand[1] = (Button) findViewById(R.id.subtract);  
	        btnCommand[2] = (Button) findViewById(R.id.multiply);  
	        btnCommand[3] = (Button) findViewById(R.id.divide);  
	        btnCommand[4] = (Button) findViewById(R.id.equal);  
	        // 获取数字  
	        btnNum[0] = (Button) findViewById(R.id.num0);  
	        btnNum[1] = (Button) findViewById(R.id.num1);  
	        btnNum[2] = (Button) findViewById(R.id.num2);  
	        btnNum[3] = (Button) findViewById(R.id.num3);  
	        btnNum[4] = (Button) findViewById(R.id.num4);  
	        btnNum[5] = (Button) findViewById(R.id.num5);  
	        btnNum[6] = (Button) findViewById(R.id.num6);  
	        btnNum[7] = (Button) findViewById(R.id.num7);  
	        btnNum[8] = (Button) findViewById(R.id.num8);  
	        btnNum[9] = (Button) findViewById(R.id.num9);  
	        btnNum[10] = (Button) findViewById(R.id.point);  
	        // 初始化显示结果区域  
	        editText = (EditText) findViewById(R.id.result);  
	        editText.setText("0.0");  
	        // 实例化监听器对象  
	        NumberAction na = new NumberAction();  
	        CommandAction ca = new CommandAction();  
	        for (Button bc : btnCommand) {  
	            bc.setOnClickListener(ca);  
	        }  
	        for (Button bc : btnNum) {  
	            bc.setOnClickListener(na);  
	        }  
	        
	        btnSwitch1=(Button)findViewById(R.id.switchmode);
	        btnSwitch1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
						table1.setVisibility(View.GONE);
						table2.setVisibility(View.VISIBLE);
						TranslateAnimation tran = new TranslateAnimation(840, 0, 0, 0);
						tran.setDuration(1000);
						table2.setAnimation(tran);
						
				}
					
			});
	        
	        btnSwitch2=(Button)findViewById(R.id.switchmode2);
	        btnSwitch2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					table1.setVisibility(View.VISIBLE);
					table2.setVisibility(View.GONE);
					TranslateAnimation tran1 = new TranslateAnimation(0, 840, 0, 0);
					tran1.setDuration(1000);
					table2.setAnimation(tran1);
				}
			});
	        
	        // clear按钮的动作  
	        btnClear = (Button) findViewById(R.id.clear);  
	        btnClear.setOnClickListener(new OnClickListener() {  
	            @Override  
	            public void onClick(View view) {  
	                editText.setText("0.0");  
	                // 初始化各项值  
	                result = 0; // x的值  
	                firstFlag = true; // 是首次运算  
	                clearFlag = false; // 不需要清空  
	                lastCommand = "="; // 运算符  
	            }  
	        });  
	    }  
	    // 数字按钮监听器  
	    private class NumberAction implements OnClickListener {  
	        @Override  
	        public void onClick(View view) {  
	            Button btn = (Button) view;  
	            String input = btn.getText().toString();  
	            if (firstFlag) { // 首次输入  
	                // 一上就".",就什么也不做  
	                if (input.equals(".")) {  
	                    return;  
	                }  
	                // 如果是"0.0"的话,就清空  
	                if (editText.getText().toString().equals("0.0")) {  
	                    editText.setText("");  
	                }  
	                firstFlag = false;// 改变是否首次输入的标记值  
	            } else {  
	                String editTextStr = editText.getText().toString();  
	                // 判断显示区域的值里面是否已经有".",如果有,输入的又是".",就什么都不做  
	                if (editTextStr.indexOf(".") != -1 && input.equals(".")) {  
	                    return;  
	                }  
	                // 判断显示区域的值里面只有"-",输入的又是".",就什么都不做  
	                if (editTextStr.equals("-") && input.equals(".")) {  
	                    return;  
	                }  
	                // 判断显示区域的值如果是"0",输入的不是".",就什么也不做  
	                if (editTextStr.equals("0") && !input.equals(".")) {  
	                    return;  
	                }  
	            }  
	            // 如果我点击了运算符以后,再输入数字的话,就要清空显示区域的值  
	            if (clearFlag) {  
	                editText.setText("");  
	                clearFlag = false;// 还原初始值,不需要清空  
	            }  
	            editText.setText(editText.getText().toString() + input);// 设置显示区域的值  
	            if(editText.getText().length()==12){
	            	Toast.makeText(getApplicationContext(), "已超过最大位数", Toast.LENGTH_LONG).show();
	            }
	        }  
	    }  
	    // 符号按钮监听器  
	    private class CommandAction implements OnClickListener {  

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Button btn = (Button) v;  
		            String inputCommand = (String) btn.getText();  
		            if (firstFlag) {// 首次输入"-"的情况  
		                if (inputCommand.equals("-")) {  
		                    editText.setText("-");// 显示区域的内容设置为"-"  
		                    firstFlag = false;// 改变首次输入的标记  
		                }  
		            } else {  
		                if (!clearFlag) {// 如果flag=false不需要清空显示区的值,就调用方法计算  
		                    calculate(Double.parseDouble(editText.getText().toString()));// 保存显示区域的值,并计算  
		                }  
		                // 保存你点击的运算符  
		                lastCommand = inputCommand;  
		                clearFlag = true;// 因为我这里已经输入过运算符,  
		            }  
			}  
	    }  
	    // 计算用的方法  
	    private void calculate(double x) {  
	        /*BigDecimal bdX = new BigDecimal(result);  
	        BigDecimal bdY = new BigDecimal(y); 
	        BigDecimal bdResult = null; 
	        if (lastCommand.equals("+")) { 
	            bdResult = bdX.add(bdY); 
	        } else if (lastCommand.equals("-")) { 
	            bdResult = bdX.subtract(bdY); 
	        } else if (lastCommand.equals("*")) { 
	            bdResult = bdX.multiply(bdY); 
	        } else if (lastCommand.equals("/")) { 
	            bdResult = bdX.divide(bdY); 
	        } else if (lastCommand.equals("=")) { 
	            bdResult = bdX; 
	        } 
	        editText.setText(bdResult.toString()); 
	        result = bdResult.doubleValue();*/  
	          
	        if (lastCommand.equals("+")) {  
	            result += x;  
	        } else if (lastCommand.equals("-")) {  
	            result -= x;  
	        } else if (lastCommand.equals("*")) {  
	            result *= x;  
	        } else if (lastCommand.equals("/")) {  
	            result /= x;  
	        } else if (lastCommand.equals("=")) {  
	            result = x;  
	        }  
	        editText.setText("" + result);  
	    } 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
