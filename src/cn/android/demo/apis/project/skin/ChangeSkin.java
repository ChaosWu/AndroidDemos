package cn.android.demo.apis.project.skin;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import cn.android.demo.apis.R;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * 开启一个 app  皮肤资源的app，换肤通过开启这个 ，来实现换肤
 * 
 * 
 * @author Elroy
 *
 */
public class ChangeSkin extends Activity {
	private Context skinContext;
	private Map<String, Map<String, Object>> resMap;
	private Button change;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.project_skin_change_skin);

		try {
			skinContext = this.createPackageContext("com.skin",
					CONTEXT_IGNORE_SECURITY | CONTEXT_INCLUDE_CODE);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			skinContext = null;
			e.printStackTrace();
		}

		change = (Button) findViewById(R.id.change_skin_button1);
		change.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				loadSkinRes();
				View view = getLayoutFromSkin("skin_main");
				if (view != null)
					setContentView(view);
			}
		});

	}

	private void loadSkinRes() {
		if (skinContext != null) {
			resMap = getSkinResourcesId("com.skin");
		} else {
			resMap = null;
		}
	}

	/**
	 * 获取皮肤包中的layout 并转化为VIEW
	 * 
	 * @param layoutName
	 * @return
	 */
	private View getLayoutFromSkin(String layoutName) {
		View view;
		if (resMap == null)
			return null;
		Map<String, Object> temp = resMap.get("layout");
		int viewId = (Integer) temp.get(layoutName);
		if (viewId != 0) {
			// 引用皮肤包资源转化万恶哦View
			LayoutInflater inflater = LayoutInflater.from(skinContext);
			view = inflater.inflate(skinContext.getResources()
					.getLayout(viewId), null);
		} else {
			view = null;
		}
		return view;
	}

	/**
	 * 取得对应包的所有资源的ID 存在MAP中
	 * 
	 * @param packageName
	 * @return
	 */
	private Map<String, Map<String, Object>> getSkinResourcesId(
			String packageName) {
		Map<String, Object> temp = null;
		Map<String, Map<String, Object>> resMap = new HashMap<String, Map<String, Object>>();
		try {
			// 取得皮肤包中的R文件
			Class<?> rClass = skinContext.getClassLoader().loadClass(
					packageName + ".R");
			// 取得记录各种资源的ID的类
			Class<?>[] resClass = rClass.getClasses();
			String className, resourceName;
			int resourceId = 0;
			for (int i = 0; i < resClass.length; i++) {
				className = resClass[i].getName();
				// 取得该类的资源
				Field field[] = resClass[i].getFields();
				for (int j = 0; j < field.length; j++) {
					resourceName = field[j].getName();
					try {
						resourceId = field[j].getInt(resourceName);
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (resourceName != null && !resourceName.equals("")) {
						temp = new HashMap<String, Object>();
						temp.put(resourceName, resourceId);
						Log.i("DDDDD",
								"className:" + className + "  resourceName:"
										+ resourceName + "  " + "resourceId:"
										+ Integer.toHexString(resourceId));
					}
				}
				// 由于内部类的关系className应该是com.skin.R$layout的形式
				// 截掉前面的包名和.R$以方便使用
				className = className.substring(packageName.length() + 3);
				resMap.put(className, temp);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resMap;
	}

}

//其实这种反射机制的换皮肤思路android提供能更强大的api，这也是我说的第二种方法：
//[java] view plaincopy在CODE上查看代码片派生到我的代码片
///** 
//     *  换皮肤 获取各种资源 
//     * @param skinPackageContext 
//     * @param resourceType 
//     * @param resourceName 
//     * @param packgeName 
//     * @return 
//     */  
//    private int getResourcesFromSkin(Context skinPackageContext,String resourceType,  
//            String resourceName,String packgeName)  
//    {  
//        int id=0;  
//        try  
//        {  
//            id=skinPackageContext.getResources().getIdentifier(resourceName,  
//                    resourceType, packgeName);  
//        }  
//        catch(Exception e)  
//        {  
//            id=0;  
//        }  
//        return id;  
//    }  
//    /** 
//     *  取得Raw中的xml 
//     * @param skinPackageContext 
//     * @param resourceName 
//     * @return 
//     */  
//    public InputStream getXmlInRawFromSkin(Context skinPackageContext,String resourceName)  
//    {  
//          
//        int id=getResourcesFromSkin(skinPackageContext,"raw",  
//                resourceName,"com.flyaudio.skin");  
//        if(id==0)  
//        {  
//            id=getgetResourcesFromSkin(this,"raw",resourceName,"com.android.launcher");  
//            return this.getResources().openRawResource(id);  
//        }  
//        return skinPackageContext.getResources().openRawResource(id);  
//    }  
//    /** 
//     * 取得bool值 
//     * @param skinPackageContext 
//     * @param resourceName 
//     * @return 
//     */  
//    public boolean getBoolFromSkin(Context skinPackageContext,String resourceName)  
//    {  
//        int id=getResourcesFromSkin(skinPackageContext,"bool",  
//                resourceName,"com.flyaudio.skin");  
//        if(id==0)  
//        {  
//            id=getgetResourcesFromSkin(this,"bool",resourceName,"com.android.launcher");  
//            if(id!=0)  
//                return this.getResources().getBoolean(id);  
//        }  
//        else  
//            return skinPackageContext.getResources().getBoolean(id);  
//        return false;  
//    }  
//    /** 
//     *  取得皮肤包中的字符串 
//     * @param skinPackageContext 
//     * @param resourceName 
//     * @return 
//     */  
//    public String getStringFromSkin(Context skinPackageContext,String resourceName)  
//    {  
//        int id=getResourcesFromSkin(skinPackageContext,"string",  
//                resourceName,"com.flyaudio.skin");  
//        if(id==0)  
//        {  
//            id=getResourcesFromSkin(this,"string",resourceName,"com.android.launcher");  
//            if(id!=0)  
//                return this.getResources().getString(id);  
//        }  
//        else  
//            return skinPackageContext.getResources().getString(id);  
//        return null;  
//    }  
//    /** 
//     *  取得皮肤包中整型参数 
//     * @param skinPackageContext 
//     * @param resourceName 
//     * @return 
//     */  
//    public Integer getIntegerFromSkin(Context skinPackageContext,String resourceName)  
//    {  
//        int id=getResourcesFromSkin(skinPackageContext,"integer",  
//                resourceName,"com.flyaudio.skin");  
//        if(id==0)  
//        {  
//            id=getgetResourcesFromSkin(this,"integer",resourceName,"com.android.launcher");  
//            return this.getResources().getInteger(id);  
//        }  
//        return skinPackageContext.getResources().getInteger(id);  
//    }  
//    /** 
//     *  取得皮肤包中Drawable资源 
//     * @param skinPackageContext 
//     * @param resourceName 
//     * @return 
//     */  
//    public Drawable getDrawableFromSkin(Context skinPackageContext,String resourceName)  
//    {  
//        int id =getResourcesFromSkin(skinPackageContext,"drawable",  
//                resourceName,"com.flyaudio.skin");  
//        if(id==0)  
//        {  
//            id=getResourcesFromSkin(this,"drawable",resourceName,"com.android.launcher");  
//            return this.getResources().getDrawable(id);  
//        }  
//        return skinPackageContext.getResources().getDrawable(id);  
//    }  
//    /** 
//     *  取得皮肤包中color资源 
//     * @param skinPackageContext 
//     * @param resourceName 
//     * @return 
//     */  
//    public int getColorFromSkin(Context skinPackageContext,String resourceName)  
//    {  
//        int id =getResourcesFromSkin(skinPackageContext,"color",  
//                resourceName,"com.flyaudio.skin");  
//        if(id==0)  
//        {  
//            id=getResourcesFromSkin(this,"color",resourceName,"com.android.launcher");  
//            return this.getResources().getColor(id);  
//        }  
//        return skinPackageContext.getResources().getColor(id);  
//    }  
//      
//    /** 
//     *  取得皮肤包中layout资源 
//     * @param skinPackageContext 
//     * @param resourceName 
//     * @return 
//     */  
//    public View getLayoutFromSkin(Context skinPackageContext,String resourceName)  
//    {  
//        int viewId=getResourcesFromSkin(skinPackageContext,"layout",  
//                resourceName,"com.flyaudio.skin");  
//        LayoutInflater inflater=null;  
//        View view=null;  
//        if(viewId !=0)  
//        {  
//            inflater = LayoutInflater.from(skinPackageContext);  
//            view =inflater.inflate(skinPackageContext.getResources().getLayout(viewId), null);  
//        }  
//        else  
//        {  
//            viewId=getgetResourcesFromSkin(this,"layout",resourceName,"com.android.launcher");  
//            inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
//            view =inflater.inflate(getResources().getLayout(viewId), null);  
//        }  
//        return view;  
//    }  
//    /** 
//     * 取得皮肤包中ID 
//     * @param skinPackageContext 
//     * @param resourceName 
//     * @return 
//     */  
//    public int getIdFromSkin(Context skinPackageContext,String resourceName,String type)  
//    {  
//        int id=0;  
//        id=getResourcesFromSkin(skinPackageContext,type,  
//                resourceName,"com.flyaudio.skin");  
//        if(id==0)  
//        {  
//            id=getResourcesFromSkin(this,type,resourceName,"com.android.launcher");  
//        }  
//        return id;  
//    }  
//
//以上代码是我在做Launcher换皮肤的时候使用的一些方法，android提供的这个api其实在Launcher程序的壁纸部分也有用到。
//上面2中方法要求资源名字是一样的，资源数量可以不一样，第一种方法因为我们已经把整个资源包的资源ID都记录到Map里面了，而第二种的话，我没去详细了解getIdentifier()的处理机制，有知道的同学，可以留言讨论。
//第三种方法就是修改frameworks，一个程序运行用到的一般都会有2套资源，一是android 自带的，也就是访问的时候我们用的android:开头的资源，二当然是我们自己的程序的资源。既然我们可以访问android的资源，我们当然也可以访问除了android资源和程序本身的资源以外的资源。
//在 frameworks/base/core/java/android/app/ActivityThread.java中有对资源访问的描述。
//其实getResources方法返回的结果就是在这里定义的，mResources的赋值代码为：
//mResources = mResourcesManager.getTopLevelResources(mPackageInfo.getResDir(),
//                    Display.DEFAULT_DISPLAY, null, compatInfo, activityToken);
//我们可以在这里修改取得资源的优先级，让程序优先读取皮肤包的资源，这里就不多做解释。
//详细请见：http://blog.csdn.net/luoshengyang/article/details/8791064
