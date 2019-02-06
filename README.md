# Layout to Image Converting Android
This Is the Library for Converting View into Image View, It also Stores the file in your android Device

<h1>USAGE</h1>
Step 1- Simple Download the library folder </br>
Step 2- Import in your project as module </br>
<h1> For Gradle Project</h1>
 Step 1: Add it in your root build.gradle at the end of repositories:
	
 ```java
    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
    
```
Step 2: Add the dependency
   
```java
    dependencies {
	        implementation 'com.github.intsab:LayoutToImage:Tag'
	}
    
```
   in your gradle file of project. (if not exist*)</br>
<h1>Post Request</h1>

```java
public class MainActivity extends AppCompatActivity {
    LinearLayout idToConvert;
    Button convertMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idToConvert = (LinearLayout) findViewById(R.id.idToConvert);
        convertMe = (Button) findViewById(R.id.convertMe);
        convertMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ToDo you can use other Constructors as per your need Like
                //ToDo  public ViewToImage(Context context, View view)
                //ToDo   public ViewToImage(Context context, View view, ActionListeners listeners)
                //ToDo   public ViewToImage(Context context, View view, String folderName, String fileName, ActionListeners listeners)
                //ToDo   public ViewToImage(Context context, View view, String fileName, ActionListeners listeners)

                Boolean isPermited= false;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        Log.v("MAIN","Permission is granted");
                        isPermited= true;
                    }
                }


                // As this is Sample i am not Handling the permissions properly
                if(!isPermited){
                    Toast.makeText(MainActivity.this, "No Permission Granted" , Toast.LENGTH_SHORT).show();
                    return;
                }

                new ViewToImage(MainActivity.this, idToConvert, new ActionListeners() {
                    @Override
                    public void convertedWithSuccess(Bitmap bitmap, String filePath) {
                        Toast.makeText(MainActivity.this, "" + filePath, Toast.LENGTH_SHORT).show();
                        //ToDo  //With File Path you can Do Whatever You want
                        //ToDo  //Use Bitmap also
                    }

                    @Override
                    public void convertedWithError(String error) {
                        Toast.makeText(MainActivity.this, "" + error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}

```


 <h1>Developers</h1>
 M Intsab Haider (Mobile & Web Application Developer)</br>
