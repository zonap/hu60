//package com.hu60;
//public class MainActivity extends Activity implements OnClickListener{   
//	private EditText mEditText1;  
//	private EditText mEditText2;   
//	private Button mButton1;  
//	private Button mButton2;       
//	private String email; 
//	private String password;    
//	private myAsyncTast tast;  
//	private ProgressDialog dialog = null;    
//	@Override  
//	protected void onCreate(Bundle savedInstanceState) {     
//		super.onCreate(savedInstanceState); 
//		setContentView(R.layout.activity_main);  
//		init();//�Կؼ����г�ʼ�� 
//		}  
//	private void init() {     
//		mEditText1 = (EditText) findViewById(R.id.editText1);    
//		mEditText2 = (EditText) findViewById(R.id.editText2);     
//		mButton1 = (Button) findViewById(R.id.button1);     
//		mButton2 = (Button) findViewById(R.id.button2);      
//		mButton1.setOnClickListener(this);      
//		mButton2.setOnClickListener(this); 
//		} 
//	@Override   
//	public void onClick(View arg0) {      
//		switch (arg0.getId()) {     
//		case R.id.button1:         
//			getEditTextValue();//����û�������         
//			tast = new myAsyncTast();//����AsyncTask   
//			tast.execute();//����AsyncTask          
//			break;      
//			case R.id.button2:   
//				Toast.makeText(MainActivity.this, "ע��", Toast.LENGTH_SHORT).show();       
//				break;    
//				} 
//		} 
//	private void getEditTextValue() {      
//		email = mEditText1.getText().toString();   
//		password = mEditText2.getText().toString();   
//		}  
//	class myAsyncTast extends AsyncTask<Void, Integer, Void>{    
//		@Override     
//		protected void onPreExecute() {  
//			super.onPreExecute();           
//			dialog = ProgressDialog.show(MainActivity.this, "��¼��ʾ", "���ڵ�¼�����Ե�...", false);//����ProgressDialog    
//			}             
//		@Override   
//		protected Void doInBackground(Void... arg0) {         
//			Http http = new Http();        
//			int n = http.send(email, password);//���͸�������     
//			publishProgress(n);     
//			return null;          
//			}              
//		@Override     
//			protected void onProgressUpdate(Integer... values) {     
//			super.onProgressUpdate(values);       
//			dialog.dismiss();//�ر�ProgressDialog  
//			if(values[0]==1){         
//				Toast.makeText(MainActivity.this, "��¼�ɹ�", Toast.LENGTH_SHORT).show();      
//				}else{         
//					Toast.makeText(MainActivity.this, "��¼ʧ��", Toast.LENGTH_SHORT).show();    
//					}        
//			}        
//		}  
//	}
//	}
//	}
//}
//	